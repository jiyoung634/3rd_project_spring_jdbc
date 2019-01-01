package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import com.test.domain.*;

public class StudentJDBCTemplate implements StudentDAO {
	
	private JdbcTemplate jdbcTemplate;

	// setter를 통한 의존 주입(DI)
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Student> studentList(String student_id) {
		
		/* 수강생 정보 view: student_detail_view
		CREATE OR REPLACE VIEW student_detail_view
		AS
		SELECT student_id, name_, ssn, phone, initial_reg_date
		    , (SELECT COUNT(*) FROM course_history c WHERE c.student_id = s.student_id) AS registration_count
		FROM students s, login l WHERE s.student_id=l.login_id;
		*/
		
		// 수강생 개인정보 - 수강생 id, 이름, 주민번호, 전화번호, 최초등록일, 수강 횟수
		String sql = "SELECT student_id, name_, ssn, phone, initial_reg_date, registration_count FROM student_detail_view WHERE student_id = ? ORDER BY student_id";
		
		List<Student> result = this.jdbcTemplate.query(sql, new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				String student_id = rs.getString("student_id");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String initial_reg_date = rs.getString("initial_reg_date");
				int registration_count = rs.getInt("registration_count");
				
				Student s = new Student();
				s.setStudent_id(student_id);
				s.setName_(name_);
				s.setSsn(ssn);
				s.setPhone(phone);
				s.setInitial_reg_date(initial_reg_date);
				s.setRegistration_count(registration_count);
				
				return s;
			}}, student_id);
		return result;
	}

	
	@Override
	public List<Student> studentCourseList(String student_id) {
		
		/* 과정 history/중도탈락 view: course_history_dropouts
		CREATE OR REPLACE VIEW course_history_dropouts AS
		SELECT ch.oc_id, ch.student_id, dropout_date FROM course_history ch, dropouts d WHERE ch.oc_id=d.oc_id(+) AND ch.student_id=d.student_id(+);
		*/
		/* 개설 과정 정보 view: oc_detail
		CREATE OR REPLACE VIEW oc_detail_view
		AS
		SELECT oc.oc_id, bc.bc_title AS oc_title, oc.oc_start_date, oc.oc_end_date, c.classroom_name
		FROM offered_courses oc, basic_courses bc, classrooms c
		WHERE oc.bc_id=bc.bc_id AND oc.classroom_id=c.classroom_id;
		*/
		/* 개설과정/수료여부 view:
		CREATE OR REPLACE VIEW oc_de_co_his_drop_view
		AS
		SELECT student_id, oc.oc_id, oc_title, oc.oc_start_date, oc.oc_end_date, oc.classroom_name
    	, CASE
            	WHEN (chd.dropout_date < oc.oc_end_date) THEN '중도 탈락'
            	WHEN (oc.oc_end_date < SYSDATE) THEN '수료'
            	WHEN (oc.oc_end_date > SYSDATE) THEN '수료 예정'
            	ELSE '미정'
          	END AS learning_status
    	, CASE
        		WHEN (chd.dropout_date < oc.oc_end_date) THEN dropout_date
        		ELSE oc.oc_end_date 
        	END AS course_end_date
		FROM oc_detail_view oc, course_history_dropouts_view chd
		WHERE oc.oc_id = chd.oc_id(+);
		*/
		
		// 수강생 수강 과정 - 개설 과정 id, 개설과정명, 개설과정기간, 강의실명, 수료 여부, 수료예정(중도탈락) 날짜
		String sql = "SELECT oc_id, oc_title, oc_start_date, oc_end_date, classroom_name, learning_status, course_end_date, (SELECT COUNT(attendance_score) FROM os_learning_status_score_view WHERE oc_id=v.oc_id AND student_id=v.student_id GROUP BY oc_id) score_status FROM oc_de_co_his_drop_view v WHERE student_id = ? ORDER BY oc_id DESC";
		List<Student> result = this.jdbcTemplate.query(sql, new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				String oc_id = rs.getString("oc_id");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				String learning_status = rs.getString("learning_status");
				String course_end_date = rs.getString("course_end_date");
				String score_status = rs.getString("score_status");
				
				Student s = new Student();
				s.setOc_id(oc_id);
				s.setOc_title(oc_title);
				s.setOc_start_date(oc_start_date);
				s.setOc_end_date(oc_end_date);
				s.setClassroom_name(classroom_name);
				s.setLearning_status(learning_status);
				s.setCourse_end_date(course_end_date);
				s.setScore_status(score_status);
				
				return s;
			}}, student_id);
		
		return result;
		
	}

	@Override
	public List<Student> scoreList(String student_id, String oc_id) {

		/* 개설 과목 정보 view: os_detail
		CREATE OR REPLACE VIEW os_detail_view
		AS
		SELECT os.os_id, os_start_date, os_end_date, os.bs_id, bs_name, os.textbook_id, textbook_title, publisher, isbn, os.instructor_id, os.oc_id, oc_start_date, oc_end_date, classroom_id, bc_id
		FROM offered_subjects os, basic_subjects bs, textbooks t, instructors i, offered_courses oc
		WHERE os.bs_id = bs.bs_id
		AND os.textbook_id=t.textbook_id
		AND os.instructor_id=i.instructor_id
		AND os.oc_id=oc.oc_id
		ORDER BY os_id;
		*/
		/*
		개설과목/강사명/배점 view:
		CREATE OR REPLACE VIEW os_de_login_percentages_view
		AS
		SELECT odv.os_id, odv.bs_name AS os_name, odv.os_start_date, odv.os_end_date, textbook_title, publisher, isbn, l.name_, l.profile_img, odv.oc_id, sp.attendance_percentage, sp.writing_percentage, sp.practice_percentage, available_subjects
		FROM os_detail_view odv, login l, score_percentages sp, INSTRUCTOR_AVAILABLE_VIEW avv
		WHERE login_id = odv.instructor_id AND odv.os_id = sp.os_id(+) AND odv.instructor_id=avv.instructor_id
		ORDER BY odv.os_id;
		*/
		/*
		개설과목/강사명/배점/학생별 점수 view:
		CREATE OR REPLACE VIEW os_de_lo_per_sco_view
		AS
		SELECT o.os_id, o.os_name, o.os_start_date, o.os_end_date, o.textbook_title, o.publisher, o.isbn, o.name_, o.profile_img, o.oc_id, o.attendance_percentage, o.writing_percentage, o.practice_percentage
		    , s.score_id, s.student_id, s.attendance_score, s.writing_score, s.practice_score, (s.attendance_score + s.writing_score + s.practice_score) AS total_score, available_subjects
		FROM os_de_login_percentages_view o, scores s WHERE o.os_id = s.os_id(+);
		*/
		/*
		 * 개설과목/강사명/배점/학생별 점수/시험 view:
		CREATE OR REPLACE VIEW os_de_lo_per_sco_ex_view
		AS
		SELECT o.os_id, o.os_name, o.os_start_date, o.os_end_date, o.textbook_title, o.publisher, o.isbn, o.name_, o.profile_img,o.oc_id, o.attendance_percentage, o.writing_percentage, o.practice_percentage
		    , o.score_id, o.student_id, o.attendance_score, o.writing_score, o.practice_score, o.total_score, e.exam_date, e.exam_file, available_subjects
		FROM os_de_lo_per_sco_view o, exams e WHERE o.os_id = e.os_id(+);
		*/
		
		// 수강생 성적 조회 - 개설 과목 id  | 개설 과목명 |      개설 과목기간    |     교재명   | 강사명 | 출결배점 | 필기배점 | 실기배점 | 출결점수 | 필기점수 | 실기점수| 총점 |  시험날짜  | 시험문제(~.zip)
		String sql = "SELECT os_id, os_name, os_start_date, os_end_date, textbook_title, publisher, isbn, name_, profile_img, attendance_percentage, writing_percentage, practice_percentage, attendance_score, writing_score, practice_score, total_score, exam_date, exam_file, available_subjects FROM os_de_lo_per_sco_ex_view WHERE student_id = ? AND oc_id = ? ORDER BY os_id";

		List<Student> result = this.jdbcTemplate.query(sql, new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				String os_id = rs.getString("os_id");
				String os_name = rs.getString("os_name");
				String profile_img = rs.getString("profile_img");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				String textbook_title = rs.getString("textbook_title");
				String publisher = rs.getString("publisher");
				String isbn = rs.getString("isbn");
				String name_ = rs.getString("name_");
				
				int attendance_percentage = rs.getInt("attendance_percentage");
				int writing_percentage = rs.getInt("writing_percentage");
				int practice_percentage = rs.getInt("practice_percentage");
				int attendance_score = rs.getInt("attendance_score");
				int writing_score = rs.getInt("writing_score");
				int practice_score = rs.getInt("practice_score");
				int total_score = rs.getInt("total_score");
				String exam_date = rs.getString("exam_date");
				String exam_file = rs.getString("exam_file");	
				String available_subjects = rs.getString("available_subjects");
				
				Student s = new Student();
				s.setOs_id(os_id);
				s.setOs_name(os_name);
				s.setProfile_img(profile_img);
				s.setOs_start_date(os_start_date);
				s.setOs_end_date(os_end_date);
				s.setTextbook_title(textbook_title);
				s.setPublisher(publisher);
				s.setIsbn(isbn);

				s.setName_(name_);
				s.setAttendance_percentage(attendance_percentage);
				s.setWriting_percentage(writing_percentage);
				s.setPractice_percentage(practice_percentage);
				s.setAttendance_score(attendance_score);
				s.setWriting_score(writing_score);
				s.setPractice_score(practice_score);
				s.setTotal_score(total_score);
				s.setExam_date(exam_date);
				s.setExam_file(exam_file);
				s.setAvailable_subjects(available_subjects);
				
				return s;
				
			}
		
		}, student_id, oc_id);
		
		return result;
	}
	
	
	@Override
	public Student ocInfo(String oc_id) {
		String sql = "SELECT oc_title, oc_start_date, oc_end_date FROM oc_detail_view WHERE oc_id=?";
		List<Student> list = jdbcTemplate.query(sql, new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				
				Student s = new Student();
				s.setOc_title(oc_title);
				s.setOc_start_date(oc_start_date);
				s.setOc_end_date(oc_end_date);
				
				return s;
				
			}
		
		}, oc_id);
		
		Student result = list.get(0);
		return result;
	}
			
}
