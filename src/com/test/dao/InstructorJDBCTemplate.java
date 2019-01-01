package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.test.domain.Instructor;

public class InstructorJDBCTemplate implements InstructorDAO {

	private JdbcTemplate jdbcTemplate;

	// setter를 통한 의존 주입(DI)
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	// 강사 개인정보 출력 메소드
	public List<Instructor> instructorList(String instructor_id) {

		/*
		 * CREATE OR REPLACE VIEW instructor_available_view AS SELECT i.instructor_id,
		 * REPLACE(LISTAGG(bs_name,'/') WITHIN GROUP(ORDER BY i.instructor_id),' ', '')
		 * available_subjects FROM basic_subjects b, instructor_detail_view i WHERE
		 * b.bs_id=i.bs_id GROUP BY i.instructor_id;
		 */

		// 강사 개인정보 - 이름, 주민번호뒷자리, 전화번호, 강의 가능 과목, 등록일
		String sql = "SELECT name_, ssn, phone, available_subjects, initial_reg_date FROM  instructor_available_view i1, login l WHERE i1.instructor_id=l.login_id AND i1.instructor_id=?";

		List<Instructor> result = this.jdbcTemplate.query(sql, new RowMapper<Instructor>() {

			@Override
			public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {

				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String available_subjects = rs.getString("available_subjects");
				String initial_reg_date = rs.getString("initial_reg_date");

				Instructor i = new Instructor();
				i.setName_(name_);
				i.setSsn(ssn);
				i.setPhone(phone);
				i.setAvailable_subjects(available_subjects);
				i.setInitial_reg_date(initial_reg_date);

				return i;
			}
		}, instructor_id);
		return result;
	}

	// 강의 스케줄 출력 메소드
	public List<Instructor> courseScheduleList(String instructor_id) {

		/*
		 * CREATE OR REPLACE VIEW os_detail_view AS SELECT os.os_id, os_start_date,
		 * os_end_date, os.bs_id, bs_name, os.textbook_id, textbook_title,  publisher, isbn,
		 * os.instructor_id, os.oc_id, oc_start_date, oc_end_date, classroom_id, bc_id
		 * FROM offered_subjects os, basic_subjects bs, textbooks t, instructors i,
		 * offered_courses oc WHERE os.bs_id = bs.bs_id AND os.textbook_id=t.textbook_id
		 * AND os.instructor_id=i.instructor_id AND os.oc_id=oc.oc_id ORDER BY os_id;
		 */
		/*
		 * CREATE OR REPLACE VIEW instructor_2_1_view AS SELECT osv.os_id, bs_name,
		 * os_start_date, os_end_date, bc_title, oc_start_date, oc_end_date,
		 * classroom_name, textbook_title, publisher, isbn, instructor_id, (SELECT COUNT(*) FROM
		 * course_history WHERE oc_id=osv.oc_id) registerred_number, CASE WHEN
		 * (SYSDATE>os_start_date AND SYSDATE<os_end_date) THEN '강의중' WHEN
		 * (SYSDATE<os_start_date) THEN '강의예정' WHEN (SYSDATE>os_end_date) THEN '강의종료'
		 * END course_status FROM os_detail_view osv, basic_courses bc, classrooms cl
		 * WHERE osv.bc_id=bc.bc_id AND osv.classroom_id=cl.classroom_id;
		 */

		// 개설된 과목 목록 - 개설 과목 id, 개설 과목명, 개설 과목기간, 개설 과정명, 개설 과정기간, 강의실, 교재명, 출결배점, 필기배점,
		// 실기배점, 시험날짜, 성적 등록 인원수
		String sql = "SELECT os_id, bs_name AS os_name, os_start_date, os_end_date, bc_title AS oc_title, oc_start_date, oc_end_date, classroom_name, textbook_title, publisher, isbn, registerred_number, course_status FROM instructor_2_1_view WHERE instructor_id=? ORDER BY os_id";

		List<Instructor> result = this.jdbcTemplate.query(sql, new RowMapper<Instructor>() {

			@Override
			public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {

				String os_id = rs.getString("os_id");
				String os_name = rs.getString("os_name");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				String textbook_title = rs.getString("textbook_title");
				String publisher = rs.getString("publisher");
				String isbn = rs.getString("isbn");
				int registerred_number = rs.getInt("registerred_number");
				String course_status = rs.getString("course_status");

				Instructor i = new Instructor();
				i.setOs_id(os_id);
				i.setOs_name(os_name);
				i.setOs_start_date(os_start_date);
				i.setOs_end_date(os_end_date);
				i.setOc_title(oc_title);
				i.setOc_start_date(oc_start_date);
				i.setOc_end_date(oc_end_date);
				i.setClassroom_name(classroom_name);
				i.setTextbook_title(textbook_title);
				i.setPublisher(publisher);
				i.setIsbn(isbn);
				i.setRegisterred_number(registerred_number);
				i.setCourse_status(course_status);

				return i;
			}
		}, instructor_id);
		return result;
	}

	// os_id로 특정 개설 과목의 수강생 목록 검색 메소드
	public List<Instructor> studentSearch(String instructor_id, String os_id) {

		/*
		 * CREATE OR REPLACE VIEW student_detail_view AS SELECT student_id, name_, ssn,
		 * phone, initial_reg_date , (SELECT COUNT(*) FROM course_history c WHERE
		 * c.student_id = s.student_id) AS registration_count FROM students s, login l
		 * WHERE s.student_id=l.login_id;
		 */

		/*
		 * CREATE OR REPLACE VIEW course_history_dropouts_view AS SELECT ch.oc_id,
		 * ch.student_id, dropout_date FROM course_history ch, dropouts d WHERE
		 * ch.oc_id=d.oc_id(+) AND ch.student_id=d.student_id(+);
		 */
		/*
		 * CREATE OR REPLACE VIEW instructor_2_2_view AS SELECT oc.oc_id, os_id,
		 * sv.student_id, name_, phone, initial_reg_date, CASE WHEN (dropout_date IS NOT
		 * NULL) THEN '중도탈락' WHEN (dropout_date IS NULL) AND (oc_end_date>SYSDATE) THEN
		 * '수료 예정' WHEN (dropout_date IS NULL) AND (oc_end_date<SYSDATE) THEN '수료' END
		 * learning_status, NVL(dropout_date, oc_end_date)course_end_date, instructor_id
		 * FROM student_detail_view sv, course_history_dropouts_view hv, offered_courses
		 * oc, offered_subjects os WHERE sv.student_id=hv.student_id AND
		 * hv.oc_id=oc.oc_id AND oc.oc_id=os.oc_id;
		 */

		// 개설 과정의 수강생 목록 - 수강생 id, 수강생 이름, 전화번호, 수료 여부, 수료예정(중도탈락) 날짜
		String sql = "SELECT student_id, name_, phone, initial_reg_date, learning_status, course_end_date FROM instructor_2_2_view WHERE instructor_id=? AND os_id=? ORDER BY student_id";

		List<Instructor> result = this.jdbcTemplate.query(sql, new RowMapper<Instructor>() {

			@Override
			public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {

				String student_id = rs.getString("student_id");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				String initial_reg_date = rs.getString("initial_reg_date");
				String learning_status = rs.getString("learning_status");
				String course_end_date = rs.getString("course_end_date");

				Instructor i = new Instructor();
				i.setStudent_id(student_id);
				i.setName_(name_);
				i.setPhone(phone);
				i.setInitial_reg_date(initial_reg_date);
				i.setLearning_status(learning_status);
				i.setCourse_end_date(course_end_date);

				return i;
			}
		}, instructor_id, os_id);
		return result;
	}

	// 배점 입력 가능한 과목 목록 출력 메소드
	public List<Instructor> percentage_addible_osList(String instructor_id) {

		/*
		 * CREATE OR REPLACE VIEW os_detail_view AS SELECT os.os_id, os_start_date,
		 * os_end_date, os.bs_id, bs_name, os.textbook_id, textbook_title, publisher, isbn,
		 * os.instructor_id, os.oc_id, oc_start_date, oc_end_date, classroom_id, bc_id
		 * FROM offered_subjects os, basic_subjects bs, textbooks t, instructors i,
		 * offered_courses oc WHERE os.bs_id = bs.bs_id AND os.textbook_id=t.textbook_id
		 * AND os.instructor_id=i.instructor_id AND os.oc_id=oc.oc_id ORDER BY os_id;
		 */
		/*
		 * CREATE OR REPLACE VIEW oc_detail_view AS SELECT oc.oc_id, bc.bc_title AS
		 * oc_title, oc.oc_start_date, oc.oc_end_date, c.classroom_name FROM
		 * offered_courses oc, basic_courses bc, classrooms c WHERE oc.bc_id=bc.bc_id
		 * AND oc.classroom_id=c.classroom_id;
		 * 
		 * SELECT * FROM oc_detail_view;
		 */
		/*
		 * 
		CREATE OR REPLACE VIEW instructor_31_view
		AS
		SELECT osv.os_id, bs_name, os_start_date, os_end_date, oc_title, ocv.oc_start_date, ocv.oc_end_date, classroom_name, textbook_title, isbn, publisher,
		    NVL(attendance_percentage, 0) attendance_percentage, NVL(writing_percentage, 0) writing_percentage, NVL(practice_percentage, 0) practice_percentage,
		    (SELECT COUNT(*) FROM course_history WHERE oc_id=ocv.oc_id) registerred_number,
		    instructor_id, profile_img
		FROM os_detail_view osv, oc_detail_view ocv, score_percentages sp
		WHERE osv.oc_id=ocv.oc_id
		AND osv.os_id=sp.os_id(+)
		AND os_end_date <= SYSDATE;
		 */

		// 배점 입력 가능한 과목 목록 - 개설 과목 id, 개설 과목명, 개설 과목기간, 개설 과정명, 개설 과정기간, 강의실, 교재명, 출결배점,
		// 필기배점, 실기배점, 수강생 등록 인원
		String sql = "SELECT os_id, bs_name AS os_name, os_start_date, os_end_date, oc_title, oc_start_date, oc_end_date, classroom_name, textbook_title, publisher, isbn, attendance_percentage, writing_percentage, practice_percentage, registerred_number, DECODE((SELECT COUNT(*) FROM scores WHERE os_id=v.os_id),0,'Y','N') AS delete_status FROM instructor_31_view v WHERE instructor_id=? ORDER BY os_id";
		
		List<Instructor> result = this.jdbcTemplate.query(sql, new RowMapper<Instructor>() {

			@Override
			public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {

				String os_id = rs.getString("os_id");
				String os_name = rs.getString("os_name");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				String textbook_title = rs.getString("textbook_title");
				String publisher = rs.getString("publisher");
				String isbn = rs.getString("isbn");
				int attendance_percentage = rs.getInt("attendance_percentage");
				int writing_percentage = rs.getInt("writing_percentage");
				int practice_percentage = rs.getInt("practice_percentage");
				int registerred_number = rs.getInt("registerred_number");
				String delete_status = rs.getString("delete_status");

				Instructor i = new Instructor();
				i.setOs_id(os_id);
				i.setOs_name(os_name);
				i.setOs_start_date(os_start_date);
				i.setOs_end_date(os_end_date);
				i.setOc_title(oc_title);
				i.setOc_start_date(oc_start_date);
				i.setOc_end_date(oc_end_date);
				i.setClassroom_name(classroom_name);
				i.setTextbook_title(textbook_title);
				i.setPublisher(publisher);
				i.setIsbn(isbn);
				i.setAttendance_percentage(attendance_percentage);
				i.setWriting_percentage(writing_percentage);
				i.setPractice_percentage(practice_percentage);
				i.setRegisterred_number(registerred_number);
				i.setDelete_status(delete_status);

				return i;
			}
		}, instructor_id);
		return result;
	}

	// 배점 삭제 가능한 과목 목록 출력 메소드
	public List<Instructor> percentage_deletable_osList(String instructor_id) {

		/*
		 * CREATE OR REPLACE VIEW offered_subjects_scores_view AS SELECT oc_id,
		 * os.os_id, bs_name, student_id, os_start_date, os_end_date, os.bs_id,
		 * textbook_id, instructor_id, score_id, attendance_score, writing_score,
		 * practice_score FROM offered_subjects os, scores s, basic_subjects bs WHERE
		 * os.os_id=s.os_id(+) AND os.bs_id=bs.bs_id ORDER BY os.os_id;
		 */

		/*
		 * CREATE OR REPLACE VIEW oc_detail_view AS SELECT oc.oc_id, bc.bc_title AS
		 * oc_title, oc.oc_start_date, oc.oc_end_date, c.classroom_name FROM
		 * offered_courses oc, basic_courses bc, classrooms c WHERE oc.bc_id=bc.bc_id
		 * AND oc.classroom_id=c.classroom_id;
		 */
		/*
		 * CREATE OR REPLACE VIEW instructor_32_1_view AS SELECT osv.os_id, osv.bs_name,
		 * os_start_date, os_end_date, oc_title, ocv.oc_start_date, ocv.oc_end_date,
		 * classroom_name, textbook_title, publisher, isbn NVL(attendance_percentage, 0)
		 * attendance_percentage, NVL(writing_percentage, 0) writing_percentage,
		 * NVL(practice_percentage, 0) practice_percentage, (SELECT COUNT(*) FROM
		 * course_history WHERE oc_id=ocv.oc_id) registerred_number, instructor_id FROM
		 * offered_subjects_scores_view osv, oc_detail_view ocv, score_percentages sp,
		 * textbooks t WHERE osv.oc_id=ocv.oc_id AND osv.os_id=sp.os_id(+) AND
		 * osv.textbook_id=t.textbook_id AND attendance_percentage IS NOT NULL AND
		 * osv.student_id IS NULL;
		 */

		// 배점 삭제 가능한 과목 목록 - 개설 과목 id, 개설 과목명, 개설 과목기간, 개설 과정명, 개설 과정기간, 강의실, 교재명, 출결배점,
		// 필기배점, 실기배점, 수강생 등록 인원
		String sql = "SELECT os_id, bs_name AS os_name, os_start_date, os_end_date, oc_title, oc_start_date, oc_end_date, classroom_name, textbook_title, publisher, isbn, attendance_percentage,writing_percentage,practice_percentage,registerred_number FROM instructor_32_1_view WHERE instructor_id = ? ORDER BY os_id";

		List<Instructor> result = this.jdbcTemplate.query(sql, new RowMapper<Instructor>() {

			@Override
			public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {

				String os_id = rs.getString("os_id");
				String os_name = rs.getString("os_name");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				String textbook_title = rs.getString("textbook_title");
				String publisher = rs.getString("publisher");
				String isbn = rs.getString("isbn");
				int attendance_percentage = rs.getInt("attendance_percentage");
				int writing_percentage = rs.getInt("writing_percentage");
				int practice_percentage = rs.getInt("practice_percentage");
				int registerred_number = rs.getInt("registerred_number");

				Instructor i = new Instructor();
				i.setOs_id(os_id);
				i.setOs_name(os_name);
				i.setOs_start_date(os_start_date);
				i.setOs_end_date(os_end_date);
				i.setOc_title(oc_title);
				i.setOc_start_date(oc_start_date);
				i.setOc_end_date(oc_end_date);
				i.setClassroom_name(classroom_name);
				i.setTextbook_title(textbook_title);
				i.setPublisher(publisher);
				i.setIsbn(isbn);
				i.setAttendance_percentage(attendance_percentage);
				i.setWriting_percentage(writing_percentage);
				i.setPractice_percentage(practice_percentage);
				i.setRegisterred_number(registerred_number);

				return i;
			}
		}, instructor_id);
		return result;
	}

	// 시험 날짜/파일 삭제 가능한 과목 목록 출력 메소드
	public List<Instructor> exam_osList(String instructor_id) {

		/*
		 * CREATE OR REPLACE VIEW os_detail_view AS SELECT os.os_id, os_start_date,
		 * os_end_date, os.bs_id, bs_name, os.textbook_id, textbook_title, publisher, isbn,
		 * os.instructor_id, os.oc_id, oc_start_date, oc_end_date, classroom_id, bc_id
		 * FROM offered_subjects os, basic_subjects bs, textbooks t, instructors i,
		 * offered_courses oc WHERE os.bs_id = bs.bs_id AND os.textbook_id=t.textbook_id
		 * AND os.instructor_id=i.instructor_id AND os.oc_id=oc.oc_id ORDER BY os_id;
		 */
		/*
		 * CREATE OR REPLACE VIEW oc_detail_view AS SELECT oc.oc_id, bc.bc_title AS
		 * oc_title, oc.oc_start_date, oc.oc_end_date, c.classroom_name FROM
		 * offered_courses oc, basic_courses bc, classrooms c WHERE oc.bc_id=bc.bc_id
		 * AND oc.classroom_id=c.classroom_id;
		 */
		/*
		 * CREATE OR REPLACE VIEW instructor_33_1_view AS SELECT os.os_id, bs_name,
		 * os_start_date, os_end_date, oc_title, oc.oc_start_date, oc.oc_end_date,
		 * classroom_name, textbook_title, publisher, isbn, exam_date, exam_file, instructor_id,
		 * os.profile_img FROM os_detail_view os, oc_detail_view oc, exams ex WHERE
		 * os.oc_id=oc.oc_id AND os.os_id=ex.os_id(+) AND os_end_date <= SYSDATE;
		 */

		// 시험 날짜/파일 삭제 가능한 과목 목록 - 개설 과목 id, 개설 과목명, 개설 과목기간, 개설 과정명, 개설 과정기간, 강의실, 교재명,
		// 시험날짜, 시험파일
		String sql = "SELECT os_id, bs_name AS os_name, os_start_date, os_end_date, oc_title, oc_start_date, oc_end_date, classroom_name, textbook_title, publisher, isbn, exam_date, exam_file, DECODE((SELECT COUNT(*) FROM scores WHERE os_id=v.os_id),0,'Y','N') AS delete_status FROM instructor_33_1_view v WHERE instructor_id=? ORDER BY os_id";

		List<Instructor> result = this.jdbcTemplate.query(sql, new RowMapper<Instructor>() {

			@Override
			public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {

				String os_id = rs.getString("os_id");
				String os_name = rs.getString("os_name");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				String textbook_title = rs.getString("textbook_title");
				String publisher = rs.getString("publisher");
				String isbn = rs.getString("isbn");
				String exam_date = rs.getString("exam_date");
				String exam_file = rs.getString("exam_file");
				String delete_status = rs.getString("delete_status");
				
				Instructor i = new Instructor();
				i.setOs_id(os_id);
				i.setOs_name(os_name);
				i.setOs_start_date(os_start_date);
				i.setOs_end_date(os_end_date);
				i.setOc_title(oc_title);
				i.setOc_start_date(oc_start_date);
				i.setOc_end_date(oc_end_date);
				i.setClassroom_name(classroom_name);
				i.setTextbook_title(textbook_title);
				i.setPublisher(publisher);
				i.setIsbn(isbn);
				i.setExam_date(exam_date);
				i.setExam_file(exam_file);
				i.setDelete_status(delete_status);

				return i;
			}
		}, instructor_id);
		return result;
	}

	// 성적 확인 목록 출력 메소드
	public List<Instructor> score_osList(String instructor_id) {

		/*
		 * CREATE OR REPLACE VIEW os_detail_view AS SELECT os.os_id, os_start_date,
		 * os_end_date, os.bs_id, bs_name, os.textbook_id, textbook_title, publisher, isbn,
		 * os.instructor_id, os.oc_id, oc_start_date, oc_end_date, classroom_id, bc_id
		 * FROM offered_subjects os, basic_subjects bs, textbooks t, instructors i,
		 * offered_courses oc WHERE os.bs_id = bs.bs_id AND os.textbook_id=t.textbook_id
		 * AND os.instructor_id=i.instructor_id AND os.oc_id=oc.oc_id ORDER BY os_id;
		 */
		/*
		 * CREATE OR REPLACE VIEW oc_detail_view AS SELECT oc.oc_id, bc.bc_title AS
		 * oc_title, oc.oc_start_date, oc.oc_end_date, c.classroom_name FROM
		 * offered_courses oc, basic_courses bc, classrooms c WHERE oc.bc_id=bc.bc_id
		 * AND oc.classroom_id=c.classroom_id;
		 */
		/*
		 * CREATE OR REPLACE VIEW instructor_41_1_view AS SELECT osv.os_id, bs_name,
		 * os_start_date, os_end_date, oc_title, ocv.oc_start_date, ocv.oc_end_date,
		 * classroom_name, textbook_title, publisher, isbn, NVL(attendance_percentage,0)
		 * attendance_percentage, NVL(writing_percentage,0) writing_percentage,
		 * NVL(practice_percentage,0) practice_percentage, exam_date, (SELECT COUNT(*)
		 * FROM scores WHERE os_id=osv.os_id)||'/'||(SELECT COUNT(*) FROM course_history
		 * WHERE osv.oc_id=oc_id) score_added_number, instructor_id FROM os_detail_view
		 * osv, oc_detail_view ocv, score_percentages sp, exams ex WHERE
		 * osv.oc_id=ocv.oc_id AND osv.os_id=sp.os_id(+) AND osv.os_id=ex.os_id(+);
		 */

		// 성적 확인 목록 - 개설 과목 id, 개설 과목명, 개설 과목기간, 개설 과정명, 개설 과정기간, 강의실, 교재명, 출결배점, 필기배점,
		// 실기배점, 시험 날짜, 성적 등록 인원수
		String sql = "SELECT os_id, bs_name AS os_name, os_start_date, os_end_date, oc_title, oc_start_date, oc_end_date, classroom_name, textbook_title, publisher, isbn, attendance_percentage, writing_percentage, practice_percentage, exam_date, score_added_number FROM instructor_41_1_view WHERE instructor_id=? AND os_end_date<=SYSDATE AND attendance_percentage >=20 AND exam_date IS NOT NULL ORDER BY os_id";

		List<Instructor> result = this.jdbcTemplate.query(sql, new RowMapper<Instructor>() {

			@Override
			public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {

				String os_id = rs.getString("os_id");
				String os_name = rs.getString("os_name");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				String textbook_title = rs.getString("textbook_title");
				String publisher = rs.getString("publisher");
				String isbn = rs.getString("isbn");
				int attendance_percentage = rs.getInt("attendance_percentage");
				int writing_percentage = rs.getInt("writing_percentage");
				int practice_percentage = rs.getInt("practice_percentage");
				String exam_date = rs.getString("exam_date");
				String score_added_number = rs.getString("score_added_number");

				Instructor i = new Instructor();
				i.setOs_id(os_id);
				i.setOs_name(os_name);
				i.setOs_start_date(os_start_date);
				i.setOs_end_date(os_end_date);
				i.setOc_title(oc_title);
				i.setOc_start_date(oc_start_date);
				i.setOc_end_date(oc_end_date);
				i.setClassroom_name(classroom_name);
				i.setTextbook_title(textbook_title);
				i.setPublisher(publisher);
				i.setIsbn(isbn);
				i.setAttendance_percentage(attendance_percentage);
				i.setWriting_percentage(writing_percentage);
				i.setPractice_percentage(practice_percentage);
				i.setExam_date(exam_date);
				i.setScore_added_number(score_added_number);

				return i;
			}
		}, instructor_id);
		return result;
	}

	// os_id로 특정 과목의 수강생 성적 검색 메소드
	public List<Instructor> scoreSearch(String instructor_id, String os_id) {

		/*
		 * CREATE OR REPLACE VIEW os_learning_status_view AS SELECT chv.oc_id, os.os_id,
		 * chv.student_id, name_, phone, CASE WHEN dropout_date IS NOT NULL THEN '중도탈락'
		 * WHEN (dropout_date IS NULL) AND (oc_end_date>SYSDATE) THEN '수료예정' WHEN
		 * (dropout_date IS NULL) AND (oc_end_date<SYSDATE) THEN '수료' END
		 * learning_status, NVL(dropout_date, oc_end_date) course_end_date,
		 * instructor_id FROM course_history_dropouts_view chv, offered_subjects os,
		 * student_detail_view sdv, offered_courses oc WHERE chv.oc_id=os.oc_id AND
		 * chv.student_id=sdv.student_id AND chv.oc_id=oc.oc_id;
		 */
		/*
		 * CREATE OR REPLACE VIEW instructor_41_2_view AS SELECT olv.os_id,
		 * olv.student_id, name_, phone, learning_status, course_end_date,
		 * attendance_score, writing_score, practice_score,
		 * (attendance_score+writing_score+practice_score) total_score, instructor_id
		 * FROM os_learning_status_view olv, scores s WHERE
		 * olv.student_id=s.student_id(+) AND olv.os_id=s.os_id(+);
		 */

		// 개설 과정의 수강생 목록 - 수강생 id, 수강생 이름, 전화번호, 수료 여부, 수료예정(중도탈락) 날짜, 출결점수, 필기점수, 실기점수,
		// 총점
		String sql = "SELECT student_id, name_, phone, learning_status, course_end_date, attendance_score, writing_score, practice_score, total_score, DECODE(attendance_score, NULL, 'Y', 'N') AS delete_status FROM instructor_41_2_view WHERE instructor_id=? AND os_id=? ORDER BY student_id";

		List<Instructor> result = this.jdbcTemplate.query(sql, new RowMapper<Instructor>() {

			@Override
			public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {

				String student_id = rs.getString("student_id");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				String learning_status = rs.getString("learning_status");
				String course_end_date = rs.getString("course_end_date");
				int attendance_score = rs.getInt("attendance_score");
				int writing_score = rs.getInt("writing_score");
				int practice_score = rs.getInt("practice_score");
				int total_score = rs.getInt("total_score");
				String delete_status = rs.getString("delete_status");

				Instructor i = new Instructor();
				i.setStudent_id(student_id);
				i.setName_(name_);
				i.setPhone(phone);
				i.setLearning_status(learning_status);
				i.setCourse_end_date(course_end_date);
				i.setAttendance_score(attendance_score);
				i.setWriting_score(writing_score);
				i.setPractice_score(practice_score);
				i.setTotal_score(total_score);
				i.setDelete_status(delete_status);

				return i;
			}
		}, instructor_id, os_id);
		return result;
	}


	// ---------------------------------------------------------------------

	// 개설과목 배점 입력 메소드
	public int percentageAdd(Instructor i) {

		// 개설 과목 id, 출결 배점, 필기 배점, 실기 배점
		String sql = "INSERT INTO score_percentages(os_id, attendance_percentage, writing_percentage, practice_percentage) VALUES(?, ?, ?, ?)";
		int result = this.jdbcTemplate.update(sql, i.getOs_id(), i.getAttendance_percentage(),
				i.getWriting_percentage(), i.getPractice_percentage());
		return result;
	}

	// 개설과목 시험 정보 입력 메소드
	public int examAdd(Instructor i) {

		// 개설 과목 id, 시험 날짜, 시험 파일
		String sql = "INSERT INTO exams(os_id, exam_date, exam_file) VALUES(?, ?, ?)";
		int result = this.jdbcTemplate.update(sql, i.getOs_id(), i.getExam_date(), i.getExam_file());
		return result;
	}

	// 성적 입력 - 배점 반환 메소드
	public Instructor scorePercentage(String os_id) {

		String sql = "SELECT attendance_percentage, writing_percentage, practice_percentage FROM score_percentages WHERE os_id=?";

		Instructor result = this.jdbcTemplate.queryForObject(sql, new RowMapper<Instructor>() {

			@Override
			public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {

				int attendance_percentage = rs.getInt("attendance_percentage");
				int writing_percentage = rs.getInt("writing_percentage");
				int practice_percentage = rs.getInt("practice_percentage");

				Instructor i = new Instructor();

				i.setAttendance_percentage(attendance_percentage);
				i.setWriting_percentage(writing_percentage);
				i.setPractice_percentage(practice_percentage);

				return i;
			}
		}, os_id);
		return result;
	}

	// 성적 입력 메소드
	public int scoreAdd(Instructor i) {

		// 성적 id, 출결 점수, 필기 점수, 실기 점수, 수강생 id, 개설 과목 id
		String sql = "INSERT INTO scores(score_id, attendance_score, writing_score, practice_score, student_id, os_id) VALUES(CONCAT('SC', LPAD(SUBSTR(NVL((SELECT MAX(score_id) FROM scores),'SC000'),3)+1, 3, 0)), ? , ? , ? , ?, ?)";
		int result = this.jdbcTemplate.update(sql, i.getAttendance_score(), i.getWriting_score(), i.getPractice_score(),
				i.getStudent_id(), i.getOs_id());
		return result;
	}

	// ----------------------------------------------------------------------------

	// 배점 삭제 메소드
	public int percentageDelete(String os_id, String instructor_id) {

		/*
		 * CREATE OR REPLACE VIEW instructor_32_2_view AS SELECT
		 * os.os_id,attendance_percentage,writing_percentage, practice_percentage,
		 * os_start_date, os_end_date,bs_id, textbook_id, instructor_id, oc_id FROM
		 * score_percentages sp, offered_subjects os WHERE sp.os_id=os.os_id;
		 * 
		 */

		// 개설 과목 id, 강사 id
		String sql = "DELETE FROM instructor_32_2_view WHERE os_id=? AND instructor_id=?";
		int result = this.jdbcTemplate.update(sql, os_id, instructor_id);
		return result;
	}

	// 성적 삭제 메소드
	public int scoreDelete(String instructor_id, String student_id, String os_id) {

		/*
		 * CREATE OR REPLACE VIEW instructor_43_2_view AS SELECT olv.os_id, score_id,
		 * olv.student_id, name_, phone, learning_status, course_end_date,
		 * attendance_score, writing_score, practice_score,
		 * (attendance_score+writing_score+practice_score) total_score, instructor_id
		 * FROM os_learning_status_view olv, scores s WHERE
		 * olv.student_id=s.student_id(+) AND olv.os_id=s.os_id(+) ORDER BY os_id;
		 */

		// 강사 id, 수강생 id, 개설 과목 id
		String sql = "DELETE FROM scores WHERE score_id = (SELECT score_id FROM instructor_43_2_view WHERE instructor_id=? AND student_id=? AND os_id=?)";
		int result = this.jdbcTemplate.update(sql, instructor_id, student_id, os_id);
		return result;

	}

	// 시험 날짜/파일 삭제 메소드
	public int examInfoDelete(String os_id, String login_id) {

		/*
		 * CREATE OR REPLACE VIEW instructor_33_2_view AS SELECT ex.os_id, exam_date,
		 * exam_file, os_start_date, os_end_date, bs_id, textbook_id, instructor_id,
		 * oc_id FROM exams ex, offered_subjects os WHERE ex.os_id=os.os_id;
		 */

		// 개설 과목 id, 강사 id
		String sql = "DELETE FROM instructor_33_2_view WHERE os_id=? AND instructor_id=?";
		int result = this.jdbcTemplate.update(sql, os_id, login_id); //
		return result;

	}

	@Override
	public Instructor ocosInfo(String os_id) {
		
		String sql = "SELECT oc_title, os_name, os_start_date, os_end_date FROM oc_os_login_detail_view WHERE os_id=?";
		List<Instructor> list = jdbcTemplate.query(sql, new RowMapper<Instructor>() {

			@Override
			public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				String oc_title = rs.getString("oc_title");
				String os_name = rs.getString("os_name");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				
				Instructor i = new Instructor();
				i.setOc_title(oc_title);
				i.setOs_name(os_name);
				i.setOs_start_date(os_start_date);
				i.setOs_end_date(os_end_date);
				
				return i;
				
			}
		
		}, os_id);
		
		Instructor result = list.get(0);
		return result;
	}
}
