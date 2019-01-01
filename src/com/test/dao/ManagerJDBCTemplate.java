package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.test.domain.Manager;

public class ManagerJDBCTemplate implements ManagerDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	private PlatformTransactionManager transactionManager;

	// setter를 통한 의존 주입(DI)
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	// 로그인 정보 메소드 
	public List<Manager> loginInfo(String login_id) {

		String sql = "SELECT name_, ssn, phone, initial_reg_date,access_id, login_id, profile_img, pw FROM login WHERE login_id=?";

		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String login_id = rs.getString("login_id");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String initial_reg_date = rs.getString("initial_reg_date");
				String access_id = rs.getString("access_id");
				String profile_img = rs.getString("profile_img");
				String pw = rs.getString("pw");

				Manager m = new Manager();
				m.setLogin_id(login_id);
				m.setName_(name_);
				m.setSsn(ssn);
				m.setPhone(phone);
				m.setInitial_reg_date(initial_reg_date);
				m.setAccess_id(access_id);
				m.setProfile_img(profile_img);
				m.setPw(pw);

				return m;
			}
		}, login_id);

		return result;
	}
	
	// 등록된 과정 목록 출력 메소드 
	public List<Manager> bcList() {
		
		
		// 등록된 과정 목록 - 기초 과정 id, 기초 과정명
		String sql = "SELECT bc_id, bc_title FROM basic_courses ORDER BY bc_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String bc_id = rs.getString("bc_id");
				String bc_title = rs.getString("bc_title");
				
				Manager m = new Manager();
				m.setBc_id(bc_id);
				m.setBc_title(bc_title);

				return m;
			}
		});
		return result;
	}
	
	// 등록된 과정 목록(삭제 가능 여부 포함) 출력 메소드 
	public List<Manager> bcList_ds() {
		
		
		// 등록된 과정 목록 - 기초 과정 id, 기초 과정명, 삭제 가능 여부  
		String sql = "SELECT bc_id, bc_title, DECODE((SELECT COUNT(*) FROM offered_courses WHERE bc_id=bc.bc_id),0,'Y','N') delete_status FROM basic_courses bc ORDER BY bc_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String bc_id = rs.getString("bc_id");
				String bc_title = rs.getString("bc_title");
				String delete_status = rs.getString("delete_status");
				
				Manager m = new Manager();
				m.setBc_id(bc_id);
				m.setBc_title(bc_title);
				m.setDelete_status(delete_status);

				return m;
			}
		});
		return result;
	}
	
	// 등록된 과목 목록 출력 메소드 
	public List<Manager> bsList() {
		
		
		// 등록된 과목 목록 - 기초 과목 id, 기초 과목명
		String sql = "SELECT bs_id, bs_name FROM basic_subjects ORDER BY bs_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String bs_id = rs.getString("bs_id");
				String bs_name = rs.getString("bs_name");
				
				Manager m = new Manager();
				m.setBs_id(bs_id);
				m.setBs_name(bs_name);

				return m;
			}
		});
		return result;
	}
	
	// 등록된 과목 목록(삭제 가능 여부 포함) 출력 메소드 
	public List<Manager> bsList_ds() {
		
		
		// 등록된 과목 목록 - 기초 과목 id, 기초 과목명, 삭제 가능 여부
		String sql = "SELECT bs_id, bs_name, CASE WHEN ((SELECT COUNT(*) FROM available_subjects WHERE bs_id=bs.bs_id)=0)AND ((SELECT COUNT(*) FROM offered_subjects WHERE bs_id=bs.bs_id)=0) THEN 'Y' ELSE 'N' END delete_status FROM basic_subjects bs ORDER BY bs_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String bs_id = rs.getString("bs_id");
				String bs_name = rs.getString("bs_name");
				String delete_status = rs.getString("delete_status");
				
				Manager m = new Manager();
				m.setBs_id(bs_id);
				m.setBs_name(bs_name);
				m.setDelete_status(delete_status);

				return m;
			}
		});
		return result;
	}
	
	// 강의실 목록 출력 메소드 
	public List<Manager> classroomList() {
		
		
		// 강의실 목록 - 강의실 id, 강의실명, 정원, 관리자 id
		String sql = "SELECT classroom_id, classroom_name, maximum, manager_id FROM classrooms ORDER BY classroom_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String classroom_id = rs.getString("classroom_id");
				String classroom_name = rs.getString("classroom_name");
				int maximum = rs.getInt("maximum");
				String manager_id = rs.getString("manager_id");
				
				Manager m = new Manager();
				m.setClassroom_id(classroom_id);
				m.setClassroom_name(classroom_name);
				m.setMaximum(maximum);
				m.setManager_id(manager_id);

				return m;
			}
		});
		return result;
	}
	
	// 강의실 목록(삭제 가능 여부 포함) 출력 메소드 
	public List<Manager> classroomList_ds() {
		
		
		// 강의실 목록 - 강의실 id, 강의실명, 정원, 관리자 id, 삭제 가능 여부
		String sql = "SELECT classroom_id, classroom_name, maximum, manager_id, DECODE((SELECT COUNT(*) FROM offered_courses WHERE classroom_id=cl.classroom_id), 0, 'Y', 'N') delete_status FROM classrooms cl ORDER BY classroom_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String classroom_id = rs.getString("classroom_id");
				String classroom_name = rs.getString("classroom_name");
				int maximum = rs.getInt("maximum");
				String manager_id = rs.getString("manager_id");
				String delete_status = rs.getString("delete_status");
				
				Manager m = new Manager();
				m.setClassroom_id(classroom_id);
				m.setClassroom_name(classroom_name);
				m.setMaximum(maximum);
				m.setManager_id(manager_id);
				m.setDelete_status(delete_status);

				return m;
			}
		});
		return result;
	}
	
	// 교재 목록 출력 메소드 
	public List<Manager> textbookList() {
				
		// 등록된 교재 목록 - 교재 id, 교재명, 출판사
		String sql = "SELECT textbook_id, textbook_title, publisher FROM textbooks ORDER BY textbook_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String textbook_id = rs.getString("textbook_id");
				String textbook_title = rs.getString("textbook_title");
				String publisher = rs.getString("publisher");
				
				Manager m = new Manager();
				m.setTextbook_id(textbook_id);
				m.setTextbook_title(textbook_title);
				m.setPublisher(publisher);

				return m;
			}
		});
		return result;
	}
	
	// 교재 목록(삭제 가능 여부 포함) 출력 메소드 
	public List<Manager> textbookList_ds() {
				
		// 등록된 교재 목록 - 교재 id, 교재명, 출판사, 삭제 가능 여부
		String sql = "SELECT textbook_id, textbook_title, publisher, isbn, DECODE((SELECT COUNT(*) FROM offered_subjects WHERE textbook_id=tx.textbook_id),0,'Y','N') delete_status FROM textbooks tx ORDER BY textbook_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String textbook_id = rs.getString("textbook_id");
				String textbook_title = rs.getString("textbook_title");
				String publisher = rs.getString("publisher");
				String delete_status = rs.getString("delete_status");
				String isbn = rs.getString("isbn");
				
				Manager m = new Manager();
				m.setTextbook_id(textbook_id);
				m.setTextbook_title(textbook_title);
				m.setPublisher(publisher);
				m.setDelete_status(delete_status);
				m.setIsbn(isbn);

				return m;
			}
		});
		return result;
	}

	// 강사 목록 출력 메소드  
	public List<Manager> instructorList() {
		
		
		/*
		CREATE OR REPLACE VIEW instructor_detail_view
		AS 
		SELECT i.instructor_id, bs_id, instructor_reg_date, name_, ssn, phone, initial_reg_date, access_id, login_id
		FROM instructors i, login l, available_subjects a
		WHERE i.instructor_id=l.login_id
		AND i.instructor_id=a.instructor_id;
		*/

		/*
		CREATE OR REPLACE VIEW manager_22_1_view
		AS
		SELECT i.instructor_id, name_, ssn, phone, available_subjects, instructor_reg_date
		FROM login l, instructors i, instructor_available_view iav
		WHERE l.login_id=i.instructor_id
		AND i.instructor_id = iav.instructor_id 
		AND access_id='LV002'
		ORDER BY i.instructor_id;
		*/
		
		// 등록된 강사 목록 - 강사 id, 이름, 주민번호 뒷자리, 강의 가능 과목, 강사 등록일
		String sql = "SELECT instructor_id, name_, ssn, phone, available_subjects, instructor_reg_date, profile_img FROM manager_22_1_view ORDER BY instructor_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String instructor_id = rs.getString("instructor_id");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String available_subjects = rs.getString("available_subjects");
				String instructor_reg_date = rs.getString("instructor_reg_date");
				String profile_img = rs.getString("profile_img");
				
				Manager m = new Manager();
				m.setInstructor_id(instructor_id);
				m.setName_(name_);
				m.setSsn(ssn);
				m.setPhone(phone);
				m.setAvailable_subjects(available_subjects);
				m.setInstructor_reg_date(instructor_reg_date);
				m.setProfile_img(profile_img);

				return m;
			}
		});
		
		return result;
	}
	
	// 강사 id를 통한 강의 가능 과목 검색 메소드 
	public List<String> availableSubjectList(String instructor_id) {
	
		String sql = "SELECT instructor_id, bs_id FROM available_subjects WHERE instructor_id = ?";
				
		List<String> result = this.jdbcTemplate.query(sql, new RowMapper<String>() {
		
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		
						String bs_id = rs.getString("bs_id");

						return bs_id;
					}
					
				}, instructor_id);
				
				return result;
		
	};


	// 강사 강의 정보 출력 메소드 
	public List<Manager> courseInfoList(String instructor_id) {
		
		/*
		 * CREATE OR REPLACE VIEW oc_detail_view AS SELECT oc.oc_id, bc.bc_title AS
		 * oc_title, oc.oc_start_date, oc.oc_end_date, c.classroom_name FROM
		 * offered_courses oc, basic_courses bc, classrooms c WHERE oc.bc_id=bc.bc_id
		 * AND oc.classroom_id=c.classroom_id;
		 */

		/*
		 * CREATE OR REPLACE VIEW manager_22_2_view AS SELECT osv.os_id, bs_name,
		 * os_start_date, os_end_date, bc_title, oc_start_date, oc_end_date,
		 * classroom_name, CASE WHEN (SYSDATE>=os_start_date AND SYSDATE<os_end_date)
		 * THEN '강의중' WHEN (SYSDATE<os_start_date) THEN '강의예정' WHEN
		 * (SYSDATE>=os_end_date) THEN '강의종료' END course_status ,instructor_id FROM
		 * os_detail_view osv, basic_courses bc, classrooms cl WHERE osv.bc_id=bc.bc_id
		 * AND osv.classroom_id=cl.classroom_id;
		 */

		String sql = "SELECT os_id, os_name, os_start_date, os_end_date, oc_title, oc_start_date, oc_end_date, classroom_name, course_status, instructor_id, name_, ssn FROM manager_22_2_view WHERE instructor_id=? ORDER BY os_id";

		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String os_id = rs.getString("os_id");
				String os_name = rs.getString("os_name");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				String course_status = rs.getString("course_status");
				String instructor_id = rs.getString("instructor_id");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");

				Manager m = new Manager();
				m.setOs_id(os_id);
				m.setOs_name(os_name);
				m.setOs_start_date(os_start_date);
				m.setOs_end_date(os_end_date);
				m.setOc_title(oc_title);
				m.setOc_start_date(oc_start_date);
				m.setOc_end_date(oc_end_date);
				m.setClassroom_name(classroom_name);
				m.setCourse_status(course_status);
				m.setInstructor_id(instructor_id);
				m.setName_(name_);
				m.setSsn(ssn);

				return m;
			}
		}, instructor_id);
		return result;
	}

	// 강사 목록(삭제 가능 여부 포함) 출력 메소드 
	public List<Manager> instructorList_ds() {
				
		/*
		CREATE OR REPLACE VIEW instructor_detail_view
		AS 
		SELECT i.instructor_id, bs_id, instructor_reg_date, name_, ssn, phone, profile_img, initial_reg_date, access_id, login_id
		FROM instructors i, login l, available_subjects a
		WHERE i.instructor_id=l.login_id
		AND i.instructor_id=a.instructor_id;
		*/

		/*
		CREATE OR REPLACE VIEW manager_23_view
		AS
		SELECT i.instructor_id, name_, ssn, phone, profile_img, available_subjects, instructor_reg_date,
		    (DECODE((SELECT COUNT(*) FROM offered_subjects WHERE instructor_id=i.instructor_id),0,'Y','N')) delete_status
		FROM login l, instructors i, instructor_available_view iav
		WHERE l.login_id=i.instructor_id
		AND i.instructor_id = iav.instructor_id 
		AND access_id='LV002';
		*/
		
		// 강사 목록 - 강사 id, 이름, 주민번호 뒷자리, 전화번호, 강의 가능 과목, 강사 등록일, 삭제 가능 여부
		String sql = "SELECT instructor_id, name_, ssn, phone, profile_img, available_subjects, instructor_reg_date, delete_status FROM manager_23_view ORDER BY instructor_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String instructor_id = rs.getString("instructor_id");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String profile_img = rs.getString("profile_img");
				String available_subjects = rs.getString("available_subjects");
				String instructor_reg_date = rs.getString("instructor_reg_date");
				String delete_status = rs.getString("delete_status");
				
				Manager m = new Manager();
				m.setInstructor_id(instructor_id);
				m.setName_(name_);
				m.setSsn(ssn);
				m.setPhone(phone);
				m.setProfile_img(profile_img);
				m.setAvailable_subjects(available_subjects);
				m.setInstructor_reg_date(instructor_reg_date);
				m.setDelete_status(delete_status);

				return m;
			}
		});
		return result;
	}
	
	// oc_id로 특정 개설 과정의 과목 정보 검색 메소드 
	public List<Manager> oc_osSearch(String oc_id) {
		
		/*
		CREATE OR REPLACE VIEW os_de_login_percentages_view
		AS
		SELECT odv.os_id, odv.bs_name AS os_name, odv.os_start_date, odv.os_end_date, textbook_title, publisher, isbn, l.name_, l.profile_img, odv.oc_id, sp.attendance_percentage, sp.writing_percentage, sp.practice_percentage, available_subjects,
		    CASE
		    WHEN os_start_date> SYSDATE THEN 'Y'
		    ELSE 'N'
		    END delete_status
		FROM os_detail_view odv, login l, score_percentages sp, INSTRUCTOR_AVAILABLE_VIEW avv
		WHERE login_id = odv.instructor_id AND odv.os_id = sp.os_id(+) AND odv.instructor_id=avv.instructor_id
		ORDER BY odv.os_id; 

		*/
		
		// 특정 개설 과정의 과목 정보 검색 목록 - 개설 과목 id, 개설과목명, 개설과목시작일, 개설과목종료일, 교재명, 강사명
		String sql = "SELECT os_id, os_name, os_start_date, os_end_date, textbook_title, publisher, isbn, name_, available_subjects, delete_status, profile_img FROM OS_DE_LOGIN_PERCENTAGES_VIEW WHERE oc_id=? ORDER BY os_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String os_id = rs.getString("os_id");
				String os_name = rs.getString("os_name");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				String textbook_title = rs.getString("textbook_title");
				String publisher = rs.getString("publisher");
				String isbn = rs.getString("isbn");
				String name_ = rs.getString("name_");
				String available_subjects = rs.getString("available_subjects");
				String delete_status = rs.getString("delete_status");
				String profile_img = rs.getString("profile_img");
				
				Manager m = new Manager();
				m.setOs_id(os_id);
				m.setOs_name(os_name);
				m.setOs_start_date(os_start_date);
				m.setOs_end_date(os_end_date);
				m.setTextbook_title(textbook_title);
				m.setPublisher(publisher);
				m.setIsbn(isbn);
				m.setName_(name_);
				m.setAvailable_subjects(available_subjects);
				m.setDelete_status(delete_status);
				m.setProfile_img(profile_img);
				
				return m;
			}
		}, oc_id);
		return result;
	}
	
	// oc_id로 특정 개설 과정의 수강생 검색 메소드 
	public List<Manager> oc_studentSearch(String oc_id) {
				
		/*
		CREATE OR REPLACE VIEW manager_32_2_view
		AS
		SELECT chv.oc_id,  chv.student_id, name_, ssn, phone, initial_reg_date,
		    CASE 
		    WHEN dropout_date IS NOT NULL THEN '중도 탈락'
		    WHEN (dropout_date IS NULL) AND (oc_end_date > SYSDATE) THEN '수료 예정'
		    WHEN (dropout_date IS NULL) AND (oc_end_date <= SYSDATE) THEN '수료'
		    END learning_status,
		    NVL(dropout_date, oc_end_date) course_end_date
		FROM course_history_dropouts_view chv, student_detail_view sdv, offered_courses oc
		WHERE chv.student_id=sdv.student_id
		AND chv.oc_id=oc.oc_id;
		*/
		
		String sql = "SELECT student_id, name_, ssn, phone, initial_reg_date, learning_status, course_end_date FROM manager_32_2_view WHERE oc_id=? ORDER BY student_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String student_id = rs.getString("student_id");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String initial_reg_date = rs.getString("initial_reg_date");
				String learning_status = rs.getString("learning_status");
				String course_end_date = rs.getString("course_end_date");
				
				Manager m = new Manager();
				m.setStudent_id(student_id);
				m.setName_(name_);
				m.setSsn(ssn);
				m.setPhone(phone);
				m.setInitial_reg_date(initial_reg_date);
				m.setLearning_status(learning_status);
				m.setCourse_end_date(course_end_date);

				return m;
			}
		}, oc_id);
		return result;
	}
	
	// 개설 과정 목록(삭제 가능 여부 포함) 출력 메소드 
	public List<Manager> ocList_ds() {
		
		/*
		CREATE OR REPLACE VIEW manager_33_view
		AS
		SELECT oc_id, oc_title, oc_start_date, oc_end_date, classroom_name, subject_offered_number, registerred_number,
		        CASE
		        WHEN ((SELECT COUNT(*) FROM course_history WHERE oc_id=odv.oc_id)=0) AND (SYSDATE<oc_start_date) THEN 'Y'
		        ELSE 'N'
		        END delete_status,
		        CASE
		        WHEN (SYSDATE>=oc_start_date AND SYSDATE<oc_end_date) THEN '강의중'
		        WHEN (SYSDATE<oc_start_date) THEN '강의예정'
		        WHEN (SYSDATE>=oc_end_date) THEN '강의종료'
		        END course_status
		FROM oc_detail_count_view odv;
		*/
		
		// 개설 과정 목록 - 개설과정 id, 개설과정명, 개설과정시작일, 개설과정 종료일, 강의실명, 개설 과목수, 수강생 등록 인원, 삭제 가능 여부
		String sql = "SELECT oc_id, oc_title, oc_start_date, oc_end_date, classroom_name, subject_offered_number, registerred_number, delete_status, course_status FROM manager_33_view ORDER BY oc_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String oc_id = rs.getString("oc_id");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				int subject_offered_number = rs.getInt("subject_offered_number");
				int registerred_number = rs.getInt("registerred_number");
				String delete_status = rs.getString("delete_status");
				String course_status = rs.getString("course_status");
				
				Manager m = new Manager();
				m.setOc_id(oc_id);
				m.setOc_title(oc_title);
				m.setOc_start_date(oc_start_date);
				m.setOc_end_date(oc_end_date);
				m.setClassroom_name(classroom_name);
				m.setSubject_offered_number(subject_offered_number);
				m.setRegisterred_number(registerred_number);
				m.setDelete_status(delete_status);
				m.setCourse_status(course_status);

				return m;
			}
		});
		return result;
	}
	
	
	// 등록된 강사 목록 출력 메소드 41 
	public List<Manager> instructorList41() {
				
		/*
		CREATE OR REPLACE VIEW instructor_detail_view
		AS 
		SELECT i.instructor_id, bs_id, instructor_reg_date, name_, ssn, phone, initial_reg_date, access_id, login_id
		FROM instructors i, login l, available_subjects a
		WHERE i.instructor_id=l.login_id
		AND i.instructor_id=a.instructor_id;
		*/

		/*
		CREATE OR REPLACE VIEW instructor_available_view
		AS
		SELECT i.instructor_id, LISTAGG(bs_name,'/') WITHIN GROUP(ORDER BY i.instructor_id) available_subjects
		FROM basic_subjects b, instructor_detail_view i 
		WHERE b.bs_id=i.bs_id
		GROUP BY i.instructor_id;
		*/
		
		// 등록된 강사 목록 - 강사id, 강사명, 주민번호 뒷자리, 전화번호, 강의 가능 과목, 최초 등록일
		String sql = "SELECT idv.instructor_id, name_, ssn, phone, available_subjects, initial_reg_date FROM instructor_detail_view idv, instructor_available_view iav WHERE idv.instructor_id = iav.instructor_id ORDER BY idv.instructor_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String instructor_id = rs.getString("instructor_id");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String available_subjects = rs.getString("available_subjects");
				String initial_reg_date = rs.getString("initial_reg_date");
				
				Manager m = new Manager();
				m.setInstructor_id(instructor_id);
				m.setName_(name_);
				m.setSsn(ssn);
				m.setPhone(phone);
				m.setAvailable_subjects(available_subjects);
				m.setInitial_reg_date(initial_reg_date);

				return m;
			}
		});
		return result;
	}
	

	// 개설 과정 목록 출력 메소드
	public List<Manager> ocList51() {
				
		/*
		CREATE OR REPLACE VIEW oc_detail_view
		AS
		SELECT oc.oc_id, bc.bc_title AS oc_title, oc.oc_start_date, oc.oc_end_date, c.classroom_name
		FROM offered_courses oc, basic_courses bc, classrooms c
		WHERE oc.bc_id=bc.bc_id AND oc.classroom_id=c.classroom_id;
		*/

		/*
		CREATE OR REPLACE VIEW oc_detail_count_view
		AS  
		SELECT oc_id, oc_title, oc_start_date, oc_end_date, classroom_name, 
		        (SELECT COUNT(*) FROM offered_subjects WHERE oc_id=ocv.oc_id) subject_offered_number,
		        (SELECT COUNT(*) FROM course_history WHERE oc_id=ocv.oc_id) registerred_number
		FROM oc_detail_view ocv;
		*/
		
		// 개설 과정 목록 - 개설과정 id, 개설과정명, 개설과정시작일, 개설과정종료일, 강의실명, 개설 과목수, 수강생 등록 인원
		String sql = "SELECT oc_id, oc_title, oc_start_date, oc_end_date, classroom_name, subject_offered_number, registerred_number FROM oc_detail_count_view WHERE oc_start_date > SYSDATE ORDER BY oc_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String oc_id = rs.getString("oc_id");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				int subject_offered_number = rs.getInt("subject_offered_number");
				int registerred_number = rs.getInt("registerred_number");
				
				Manager m = new Manager();
				m.setOc_id(oc_id);
				m.setOc_title(oc_title);
				m.setOc_start_date(oc_start_date);
				m.setOc_end_date(oc_end_date);
				m.setClassroom_name(classroom_name);
				m.setSubject_offered_number(subject_offered_number);
				m.setRegisterred_number(registerred_number);		

				return m;
			}
		});
		return result;
	}
	
	// 수강생 id로 특정 수강생의 수강 취소 가능 과정 검색 메소드  
	public List<Manager> ocSearchToCancle(String student_id) {
		
			/* 개설과정/수료여부 view:
			CREATE OR REPLACE VIEW oc_de_co_his_drop_view
			AS
			SELECT student_id, oc.oc_id, oc_title, oc.oc_start_date, oc.oc_end_date, oc.classroom_name
			    , CASE
			            WHEN (chd.dropout_date < oc.oc_end_date) THEN '중도 탈락'
			            WHEN (oc.oc_end_date <= SYSDATE) THEN '수료'
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
			
			String sql = "SELECT oc_id, oc_title, oc_start_date, oc_end_date, classroom_name, learning_status, course_end_date FROM oc_de_co_his_drop_view WHERE oc_start_date > SYSDATE AND student_id = ? ORDER BY oc_id";

			List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

				@Override
				public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

					String oc_id = rs.getString("oc_id");
					String oc_title = rs.getString("oc_title");
					String oc_start_date = rs.getString("oc_start_date");
					String oc_end_date = rs.getString("oc_end_date");
					String classroom_name = rs.getString("classroom_name");
					String learning_status = rs.getString("learning_status");
					String course_end_date = rs.getString("course_end_date");

					Manager m = new Manager();
					m.setOc_id(oc_id);
					m.setOc_title(oc_title);
					m.setOc_start_date(oc_start_date);
					m.setOc_end_date(oc_end_date);
					m.setClassroom_name(classroom_name);
					m.setLearning_status(learning_status);
					m.setCourse_end_date(course_end_date);

					return m;
				}
			}, student_id);
			return result;
		}		
	
	// 수강생 id로 특정 수강생의 중도 탈락 가능 과정 검색 메소드  
	public List<Manager> ocSearchForDropout(String student_id) {
				
				/* 개설과정/수료여부 view:
				CREATE OR REPLACE VIEW oc_de_co_his_drop_view
				AS
				SELECT student_id, oc.oc_id, oc_title, oc.oc_start_date, oc.oc_end_date, oc.classroom_name
				    , CASE
				            WHEN (chd.dropout_date < oc.oc_end_date) THEN '중도 탈락'
				            WHEN (oc.oc_end_date <= SYSDATE) THEN '수료'
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
				
				String sql = "SELECT oc_id, oc_title, oc_start_date, oc_end_date, classroom_name, learning_status, course_end_date FROM oc_de_co_his_drop_view WHERE oc_start_date < SYSDATE AND learning_status = '수료 예정' AND student_id = ? ORDER BY oc_id";

				List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

					@Override
					public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

						String oc_id = rs.getString("oc_id");
						String oc_title = rs.getString("oc_title");
						String oc_start_date = rs.getString("oc_start_date");
						String oc_end_date = rs.getString("oc_end_date");
						String classroom_name = rs.getString("classroom_name");
						String learning_status = rs.getString("learning_status");
						String course_end_date = rs.getString("course_end_date");

						Manager m = new Manager();
						m.setOc_id(oc_id);
						m.setOc_title(oc_title);
						m.setOc_start_date(oc_start_date);
						m.setOc_end_date(oc_end_date);
						m.setClassroom_name(classroom_name);
						m.setLearning_status(learning_status);
						m.setCourse_end_date(course_end_date);

						return m;
					}
				}, student_id);
				return result;
			}
	
	
	// 수강생 id로 특정 수강생의 수강 과정 검색 메소드  
	public List<Manager> ocSearch(String student_id) {
			
		/* 과정 history/중도탈락 view: course_history_dropouts
		CREATE OR REPLACE VIEW course_history_dropouts_view AS
		SELECT ch.oc_id, ch.student_id, dropout_date FROM course_history ch, dropouts d WHERE ch.oc_id=d.oc_id(+) AND ch.student_id=d.student_id(+);
		*/
		/* 개설 과정 정보 view: oc_detail
		CREATE OR REPLACE VIEW oc_detail_view
		AS
		SELECT oc.oc_id, bc.bc_title AS oc_title, oc.oc_start_date, oc.oc_end_date, c.classroom_name
		FROM offered_courses oc, basic_courses bc, classrooms c
		WHERE oc.bc_id=bc.bc_id AND oc.classroom_id=c.classroom_id;
		*/
		/*
		개설과정/수료여부 view:
       	CREATE OR REPLACE VIEW oc_de_co_his_drop_view
		AS
		SELECT chd.student_id, oc.oc_id, oc_title, oc.oc_start_date, oc.oc_end_date, oc.classroom_name
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
		
		// 특정 수강생의 수강 과정 목록 - 개설과정id, 개설과정명, 개설과정시작일, 개설과정종료일, 강의실명, 수료 여부, 수료예정(중도탈락) 날짜 
		String sql = "SELECT oc_id, oc_title, oc_start_date, oc_end_date, classroom_name, learning_status, course_end_date FROM oc_de_co_his_drop_view WHERE student_id = ? ORDER BY oc_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String oc_id = rs.getString("oc_id");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				String learning_status = rs.getString("learning_status");
				String course_end_date = rs.getString("course_end_date");
				
				Manager m = new Manager();
				m.setOc_id(oc_id);
				m.setOc_title(oc_title);
				m.setOc_start_date(oc_start_date);
				m.setOc_end_date(oc_end_date);
				m.setClassroom_name(classroom_name);
				m.setLearning_status(learning_status);
				m.setCourse_end_date(course_end_date);

				return m;
			}
		}, student_id);
		return result;
	}
	
	// oc_id로 특정 과정의 개설 과목 목록(시험 정보 포함) 검색 메소드  
	public List<Manager> osInfoSearch(String oc_id) {
			
		/*
		CREATE OR REPLACE VIEW os_de_lo_per_sco_view
		AS
		SELECT o.os_id, o.os_name, o.os_start_date, o.os_end_date, o.textbook_title, o.publisher, o.isbn, o.name_, o.profile_img, o.oc_id, o.attendance_percentage, o.writing_percentage, o.practice_percentage
		    , s.score_id, s.student_id, s.attendance_score, s.writing_score, s.practice_score, (s.attendance_score + s.writing_score + s.practice_score) AS total_score, available_subjects
		FROM os_de_login_percentages_view o, scores s WHERE o.os_id = s.os_id(+);
		 */
		/*
    	CREATE OR REPLACE VIEW manager_61_view
    	AS
    	SELECT os.os_id, os.os_name, os.os_start_date, os.os_end_date, os.textbook_title, os.publisher, os.isbn, os.name_, os.profile_img, os.available_subjects, oc.oc_id, oc.oc_title, oc_start_date, oc_end_date, oc.classroom_name, os.attendance_percentage, os.writing_percentage, os.practice_percentage
        	, exam_date, exam_file
    	FROM os_de_login_percentages_view os, exams e, oc_detail_view oc WHERE os.os_id = e.os_id(+) AND oc.oc_id = os.oc_id;
		*/
		
		// 특정 과정의 개설 과목 목록 - 개설과목id, 개설과목명, 개설과목시작일, 개설과목종료일, 교재명, 강사명, 개설과정명, 개설과정시작일, 개설과정종료일, 강의실명, 출결배점, 필기배점, 실기배점, 시험 날짜, 시험 파일
		String sql = "SELECT os_id, os_name, os_start_date, os_end_date, textbook_title, publisher, isbn, name_, profile_img, available_subjects, oc_title, oc_start_date, oc_end_date, classroom_name, attendance_percentage, writing_percentage, practice_percentage, exam_date, exam_file FROM manager_61_view WHERE oc_id = ? ORDER BY os_id";
	
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

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
				String name_ = rs.getString("name_");
				String profile_img = rs.getString("profile_img");
				String available_subjects = rs.getString("available_subjects");
				int attendance_percentage = rs.getInt("attendance_percentage");
				int writing_percentage = rs.getInt("writing_percentage");
				int practice_percentage = rs.getInt("practice_percentage");
				String exam_date = rs.getString("exam_date");
				String exam_file = rs.getString("exam_file");
				
				Manager m = new Manager();
				m.setOs_id(os_id);
				m.setOs_name(os_name);
				m.setOs_start_date(os_start_date);
				m.setOs_end_date(os_end_date);
				m.setOc_title(oc_title);
				m.setOc_start_date(oc_start_date);
				m.setOc_end_date(oc_end_date);
				m.setClassroom_name(classroom_name);
				m.setTextbook_title(textbook_title);
				m.setPublisher(publisher);
				m.setIsbn(isbn);
				m.setName_(name_);
				m.setProfile_img(profile_img);
				m.setAvailable_subjects(available_subjects);
				m.setAttendance_percentage(attendance_percentage);
				m.setWriting_percentage(writing_percentage);
				m.setPractice_percentage(practice_percentage);
				m.setExam_date(exam_date);
				m.setExam_file(exam_file);

				return m;
			}
		}, oc_id);
		return result;
	}

	// 수강생 정보 검색 출력 메소드 
	// 수강생 id, 수강생 이름, 주민번호 뒷자리 기준으로 검색
	public List<Manager> studentInfoSearch(String key, String value) {

			/*
			 * 수강생 정보 view: student_detail_view CREATE OR REPLACE VIEW student_detail_view
			 * AS SELECT student_id, name_, ssn, phone, initial_reg_date , (SELECT COUNT(*)
			 * FROM course_history c WHERE c.student_id = s.student_id) AS
			 * registration_count FROM students s, login l WHERE s.student_id=l.login_id;
			 */

			// 수강생 정보 - 수강생 id, 수강생 이름, 주민번호 뒷자리, 전화번호, 최초 등록일, 수강 횟수
			String sql = "SELECT student_id, name_, ssn, phone, initial_reg_date, registration_count, profile_img FROM student_detail_view";

			switch (key) {
			case "ALL":
				break;
			case "student_id":
				sql += " WHERE student_id=?";
				break;
			case "name_":
				sql += "WHERE INSTR(name_,?)>0";
				break;
			case "ssn":
				sql += "WHERE INSTR(ssn,?)>0";
				break;
			case "phone":
				sql += "WHERE INSTR(phone,?)>0";
				break;
			}
			List<Manager> result = null;

			if (key.equals("ALL")) {
				result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

					@Override
					public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

						String student_id = rs.getString("student_id");
						String name_ = rs.getString("name_");
						String ssn = rs.getString("ssn");
						String phone = rs.getString("phone");
						String initial_reg_date = rs.getString("initial_reg_date");
						int registration_count = rs.getInt("registration_count");
						String profile_img = rs.getString("profile_img");

						Manager m = new Manager();
						m.setStudent_id(student_id);
						m.setName_(name_);
						m.setSsn(ssn);
						m.setPhone(phone);
						m.setInitial_reg_date(initial_reg_date);
						m.setRegistration_count(registration_count);
						m.setProfile_img(profile_img);

						return m;
					}
				});
			} else {
				result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

					@Override
					public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

						String student_id = rs.getString("student_id");
						String name_ = rs.getString("name_");
						String ssn = rs.getString("ssn");
						String phone = rs.getString("phone");
						String initial_reg_date = rs.getString("initial_reg_date");
						int registration_count = rs.getInt("registration_count");
						String profile_img = rs.getString("profile_img");
						
						Manager m = new Manager();
						m.setStudent_id(student_id);
						m.setName_(name_);
						m.setSsn(ssn);
						m.setPhone(phone);
						m.setInitial_reg_date(initial_reg_date);
						m.setRegistration_count(registration_count);
						m.setProfile_img(profile_img);

						return m;
					}
				}, value);
			}
			return result;
	}

	// 수강생 정보 검색 출력 메소드  
	// 수강생 id, 수강생 이름, 주민번호 뒷자리 기준으로 검색
	public List<Manager> studentInfoSearch(String key, String value, int start_row, int end_row) {
		
		/* 수강생 정보 view: student_detail_view
		CREATE OR REPLACE VIEW student_detail_view
		AS
		SELECT student_id, name_, ssn, phone, initial_reg_date
		    , (SELECT COUNT(*) FROM course_history c WHERE c.student_id = s.student_id) AS registration_count
		FROM students s, login l WHERE s.student_id=l.login_id;
		*/
		// 수강생 정보 - 수강생 id, 수강생 이름, 주민번호 뒷자리, 전화번호, 최초 등록일, 수강 횟수
		
		String sql = "SELECT student_id, name_, ssn, phone, initial_reg_date, registration_count, profile_img,  DECODE((SELECT COUNT(*) FROM oc_de_co_his_drop_view WHERE oc_start_date < SYSDATE AND learning_status = '수료 예정' AND student_id=v.student_id),0,'N','Y') AS dropout_status, DECODE((SELECT COUNT(*) FROM oc_de_co_his_drop_view WHERE oc_start_date > SYSDATE AND student_id=v.student_id),0,'N','Y') AS cancel_status FROM student_detail_view v";

		switch (key) {
		case "ALL":
			sql = " SELECT student_id, name_, ssn, phone, initial_reg_date, registration_count, profile_img, dropout_status, cancel_status" + 
				  " FROM (SELECT student_id, name_, ssn, phone, initial_reg_date, registration_count, profile_img,  DECODE((SELECT COUNT(*) FROM oc_de_co_his_drop_view WHERE oc_start_date < SYSDATE AND learning_status = '수료 예정' AND student_id=v.student_id),0,'N','Y') AS dropout_status, DECODE((SELECT COUNT(*) FROM oc_de_co_his_drop_view WHERE oc_start_date > SYSDATE AND student_id=v.student_id),0,'N','Y') AS cancel_status, row_number() OVER (ORDER BY student_id) rnk" + 
				  " FROM student_detail_view v)";
			sql += String.format(" WHERE rnk BETWEEN %d AND %d", start_row, end_row);
			break;
		case "student_id":
			sql += " WHERE student_id = ?";
			break;
		case "name_":
			sql += " WHERE INSTR(name_, ?) > 0";
			break;
		case "ssn":
			sql += " WHERE INSTR(ssn, ?) > 0";
			break;
		case "phone":
			sql += " WHERE INSTR(phone, ?) > 0";
			break;
		}
		
		sql += " ORDER BY student_id";

		List<Manager> result = null;

		if (key.equals("ALL")) {

			result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

				@Override
				public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

					String student_id = rs.getString("student_id");
					String name_ = rs.getString("name_");
					String ssn = rs.getString("ssn");
					String phone = rs.getString("phone");
					String initial_reg_date = rs.getString("initial_reg_date");
					int registration_count = rs.getInt("registration_count");
					String profile_img = rs.getString("profile_img");
					String dropout_status = rs.getString("dropout_status");
					String cancel_status = rs.getString("cancel_status");

					Manager m = new Manager();
					m.setStudent_id(student_id);
					m.setName_(name_);
					m.setSsn(ssn);
					m.setPhone(phone);
					m.setInitial_reg_date(initial_reg_date);
					m.setRegistration_count(registration_count);
					m.setProfile_img(profile_img);
					m.setDropout_status(dropout_status);
					m.setCancel_status(cancel_status);
					
					return m;
				}
			});
			
		} else {
			
			result = this.jdbcTemplate.query(sql, new Object[] { value } , new RowMapper<Manager>() {

				@Override
				public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

					String student_id = rs.getString("student_id");
					String name_ = rs.getString("name_");
					String ssn = rs.getString("ssn");
					String phone = rs.getString("phone");
					String initial_reg_date = rs.getString("initial_reg_date");
					int registration_count = rs.getInt("registration_count");
					String profile_img = rs.getString("profile_img");

					Manager m = new Manager();
					m.setStudent_id(student_id);
					m.setName_(name_);
					m.setSsn(ssn);
					m.setPhone(phone);
					m.setInitial_reg_date(initial_reg_date);
					m.setRegistration_count(registration_count);
					m.setProfile_img(profile_img);

					return m;
				}
			});
		}

		
		return result;
	}
	
	
	// 
	public Manager studentInfo(String student_id) {
		
		String sql = "SELECT student_id, name_, ssn FROM student_detail_view WHERE student_id =?";

		Manager result  = this.jdbcTemplate.queryForObject(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String student_id = rs.getString("student_id");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");

				Manager m = new Manager();
				m.setStudent_id(student_id);
				m.setName_(name_);
				m.setSsn(ssn);
				
				return m;
			}
			
		}, student_id);
		
		return result;
		
	}
	
	// 총 수강생 수 출력 메소드  
	public int studentCount() {

		/*
		CREATE OR REPLACE VIEW student_detail_view
		AS
		SELECT student_id, name_, ssn, phone, initial_reg_date, access_id
	    	, (SELECT COUNT(*) FROM course_history c WHERE c.student_id = s.student_id) AS registration_count
		FROM students s, login l WHERE s.student_id=l.login_id;
		*/
		/*
		 CREATE OR REPLACE VIEW manager_52_view
			AS 
			SELECT student_id, name_, ssn, phone, initial_reg_date, registration_count
	   		, CASE
	   		WHEN ((SELECT COUNT(*) FROM course_history ch WHERE ch.student_id = sdv.student_id) > 0) THEN 'N'
	   		ELSE 'Y'
	   		END AS deleteCheck 
			FROM student_detail_view sdv ORDER BY sdv.student_id;
		 */
		String sql = "SELECT COUNT(*) FROM manager_52_view";
			
		int result = this.jdbcTemplate.queryForObject(sql, Integer.class);
			
		return result;
	}
	
	// os_id로 수강생 성적 검색 메소드  
	public List<Manager> studentScoreList(String os_id) {
		
		// 수강생 성적 검색 목록 - 수강생 id, 수강생 이름, 주민번호 뒷자리, 전화번호, 출결 점수, 필기 점수, 실기 점수, 총점
		String sql = "SELECT sdv.student_id, sdv.name_, ssn, sdv.phone, attendance_score, writing_score, practice_score, (attendance_score+writing_score+practice_score) AS total_score FROM student_detail_view sdv, OS_LEARNING_STATUS_SCORE_VIEW ov WHERE sdv.student_id = ov.student_id(+) AND os_id = ? ORDER BY os_id, student_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String student_id = rs.getString("student_id");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				int attendance_score = rs.getInt("attendance_score");
				int writing_score = rs.getInt("writing_score");
				int practice_score = rs.getInt("practice_score");
				int total_score = rs.getInt("total_score");
				
				Manager m = new Manager();
				m.setStudent_id(student_id);
				m.setName_(name_);
				m.setPhone(phone);
				m.setAttendance_score(attendance_score);
				m.setWriting_score(writing_score);
				m.setPractice_score(practice_score);
				m.setTotal_score(total_score);

				return m;
			}
		}, os_id);
		return result;
	}
	
	// 개설 과정 목록 출력 메소드 61  
	public List<Manager> ocList61() {
		
		/*
		개설과정 detail view:
		CREATE OR REPLACE VIEW oc_detail_view
		AS
		SELECT oc.oc_id, bc.bc_title AS oc_title, oc.oc_start_date, oc.oc_end_date, c.classroom_name
		FROM offered_courses oc, basic_courses bc, classrooms c
		WHERE oc.bc_id=bc.bc_id AND oc.classroom_id=c.classroom_id;
		*/
		/* 
		CREATE OR REPLACE VIEW oc_detail_count_view
	   	AS  
 		SELECT oc_id, oc_title, oc_start_date, oc_end_date, classroom_name, 
    	(SELECT COUNT(*) FROM offered_subjects WHERE oc_id=ocv.oc_id) subject_offered_number,
    	(SELECT COUNT(*) FROM course_history WHERE oc_id=ocv.oc_id) registerred_number
		FROM oc_detail_view ocv;
		 */
		
		// 개설 과정 목록 - 개설 과정id, 개설과정명, 개설과정시작일, 개설과정종료일, 강의실명, 개설과목수, 수강생 등록 인원
		String sql = "SELECT oc_id, oc_title, oc_start_date, oc_end_date, classroom_name, subject_offered_number, registerred_number," 
					+" CASE WHEN (SYSDATE>oc_start_date AND SYSDATE<oc_end_date) THEN '강의중'"
					+" WHEN (SYSDATE<oc_start_date) THEN '강의예정' WHEN (SYSDATE > oc_end_date) THEN '강의종료'" 
					+" END course_status FROM oc_detail_count_view ORDER BY oc_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String oc_id = rs.getString("oc_id");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String classroom_name = rs.getString("classroom_name");
				int subject_offered_number = rs.getInt("subject_offered_number");
				int registerred_number = rs.getInt("registerred_number");
				String course_status = rs.getString("course_status");
				
				Manager m = new Manager();
				m.setOc_id(oc_id);
				m.setOc_title(oc_title);
				m.setOc_start_date(oc_start_date);
				m.setOc_end_date(oc_end_date);
				m.setClassroom_name(classroom_name);
				m.setSubject_offered_number(subject_offered_number);
				m.setRegisterred_number(registerred_number);
				m.setCourse_status(course_status);

				return m;
			}
		});
		
		return result;
	}

	
	// oc_id로 특정 과목의 성적 검색 메소드  
	public List<Manager> stu_scoreSearch(String student_id, String oc_id) {
		
		/*
		개설과목/강사명/배점 view:
		CREATE OR REPLACE VIEW os_de_login_percentages_view
		AS
		SELECT odv.os_id, odv.bs_name AS os_name, odv.os_start_date, odv.os_end_date, odv.textbook_title, l.name_, odv.oc_id, sp.attendance_percentage, sp.writing_percentage, sp.practice_percentage
		FROM os_detail_view odv, login l, score_percentages sp
		WHERE login_id = instructor_id AND odv.os_id = sp.os_id(+)
		ORDER BY odv.os_id;
		*/
		/*
		개설과목/강사명/배점/학생별 점수 view:
		CREATE OR REPLACE VIEW os_de_lo_per_sco_view
		AS
		SELECT o.os_id, o.os_name, o.os_start_date, o.os_end_date, o.textbook_title, o.name_, o.oc_id, o.attendance_percentage, o.writing_percentage, o.practice_percentage
		    , s.score_id, s.student_id, s.attendance_score, s.writing_score, s.practice_score, (s.attendance_score + s.writing_score + s.practice_score) AS total_score
		FROM os_de_login_percentages_view o, scores s WHERE o.os_id = s.os_id(+);
		*/
		/*
		CREATE OR REPLACE VIEW os_de_lo_per_sco_ex_view
		AS
		SELECT o.os_id, o.os_name, o.os_start_date, o.os_end_date, o.textbook_title, o.name_, o.oc_id, o.attendance_percentage, o.writing_percentage, o.practice_percentage
		    , o.score_id, o.student_id, o.attendance_score, o.writing_score, o.practice_score, o.total_score, e.exam_date, e.exam_file
		FROM oc_de_lo_per_sco_view o, exams e WHERE o.os_id = e.os_id(+);
		*/
		
		// 특정 과목의 성적 검색 목록 - 개설과목id, 개설과목명, 성적 id, 개설과목시작일, 개설과목종료일, 교재명, 강사명, 출결배점, 필기배점, 실기배점, 출결점수, 필기점수, 실기점수, 총점, 시험 날짜, 시험 파일
		String sql = "SELECT osv.os_id, os_name, score_id, os_start_date, os_end_date, textbook_title, osv.name_, osv.profile_img, available_subjects, attendance_percentage, writing_percentage, practice_percentage, attendance_score, writing_score, practice_score, total_score, exam_date, exam_file FROM OS_DE_LOGIN_PERCENTAGES_VIEW osv, OS_LEARNING_STATUS_SCORE_VIEW lsv, exams e WHERE osv.os_id=lsv.os_id(+) AND osv.os_id=e.os_id(+) AND student_id=? AND osv.oc_id=? ORDER BY osv.os_id";
		
		List<Manager> result = this.jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

				String os_id = rs.getString("os_id");
				String os_name = rs.getString("os_name");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				String name_ = rs.getString("name_");
				String profile_img = rs.getString("profile_img");
				String available_subjects = rs.getString("available_subjects");
				int attendance_percentage = rs.getInt("attendance_percentage");
				int writing_percentage = rs.getInt("writing_percentage");
				int practice_percentage = rs.getInt("practice_percentage");
				int attendance_score = rs.getInt("attendance_score");
				int writing_score = rs.getInt("writing_score");
				int practice_score = rs.getInt("practice_score");
				int total_score = rs.getInt("total_score");
				String exam_file = rs.getString("exam_file");
				
				Manager m = new Manager();
				m.setOs_id(os_id);
				m.setOs_name(os_name);
				m.setOs_start_date(os_start_date);
				m.setOs_end_date(os_end_date);
				m.setName_(name_);
				m.setProfile_img(profile_img);
				m.setAvailable_subjects(available_subjects);
				m.setAttendance_percentage(attendance_percentage);
				m.setWriting_percentage(writing_percentage);
				m.setPractice_percentage(practice_percentage);
				m.setAttendance_score(attendance_score);
				m.setWriting_score(writing_score);
				m.setPractice_score(practice_score);
				m.setTotal_score(total_score);
				m.setExam_file(exam_file);

				return m;
			}
		},student_id, oc_id);
		return result;
	}
	
	
	// -------------------------------------------------------------------------------------------------------
	
	// 기초 과정 입력 메소드 
	public int bcAdd(String value) {
				
		// 기초 과정 id, 기초 과정명
		String sql = "INSERT INTO basic_courses(bc_id, bc_title) VALUES (CONCAT('BC', LPAD(SUBSTR(NVL((SELECT MAX(bc_id) FROM basic_courses),'BC000'),3)+1, 3, 0)), ?)";

		int result = this.jdbcTemplate.update(sql, value);
		return result;
	}
	
	// 기초 과목 입력 메소드 
	public int bsAdd(String value) {
		
		// 기초 과목 id, 기초과목명
		String sql = "INSERT INTO basic_subjects(bs_id, bs_name) VALUES (CONCAT('BS', LPAD(SUBSTR(NVL((SELECT MAX(bs_id) FROM basic_subjects),'BS000'),3)+1, 3, 0)), ?)";

		int result = this.jdbcTemplate.update(sql, value);
		return result;
	}
	
	// 강의실 입력 메소드 
	public int classroomAdd(Manager m) {
		
		// 강의실 id, 강의실명, 정원, 관리자 id
		String sql = "INSERT INTO classrooms(classroom_id, classroom_name, maximum, manager_id) VALUES (CONCAT('CL', LPAD(SUBSTR(NVL((SELECT MAX(classroom_id) FROM classrooms),'CL000'),3)+1, 3, 0)), ?, ?, ?)";

		int result = this.jdbcTemplate.update(sql, m.getClassroom_name(), m.getMaximum(), m.getManager_id());
		return result;
	}
	
	// 교재 정보 입력 메소드 
	public int textbookAdd(Manager m) {
		// 교재 id, 교재명, 출판사
		String sql = "INSERT INTO textbooks(textbook_id, textbook_title, publisher, isbn) VALUES(CONCAT('TX', LPAD(SUBSTR(NVL((SELECT MAX(textbook_id) FROM textbooks),'TX000'),3)+1, 3, 0)), ?, ?, ?)";

		int result = this.jdbcTemplate.update(sql, m.getTextbook_title(), m.getPublisher(), m.getIsbn());
		return result;
	}
	
	// 강사 정보 입력 메소드  ******************************************************트랜잭션******************************************************************** 
		public int instructorAdd(Manager m, List<String> newavailables) {
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = transactionManager.getTransaction(def);
			
			int result = 0;
			
			try {
				
			String sql1 = "INSERT INTO login(name_, ssn, phone, initial_reg_date, profile_img, pw, access_id, login_id) VALUES (?, ?, ?, ?, ?, ?, 'LV002', CONCAT('S', LPAD(SUBSTR(NVL((SELECT MAX(login_id) FROM login),'S000'),2)+1, 3, 0)))";
			result = this.jdbcTemplate.update(sql1, m.getName_(), m.getSsn(), m.getPhone(), m.getInitial_reg_date(), m.getProfile_img(), m.getSsn());

			String sql2 = "INSERT INTO instructors(instructor_id, instructor_reg_date) VALUES ((SELECT login_id FROM login WHERE name_=? AND ssn=?), ?)";
			result = this.jdbcTemplate.update(sql2, m.getName_(), m.getSsn(), m.getInitial_reg_date());
			
			String sql3 = "INSERT INTO available_subjects(instructor_id, bs_id) VALUES((SELECT login_id FROM login WHERE name_=? AND ssn=?), ?)";
			
			for(String bs_id : newavailables) {
					result = this.jdbcTemplate.update(sql3, m.getName_(), m.getSsn(), bs_id);
				}
			
			transactionManager.commit(status);
			
			} catch (DataAccessException e) {
				
				System.out.println("Error in creating record, rolling back");
				transactionManager.rollback(status);
				
				throw e;
			}
			
			return result;
		}




		
	// 개설 과정 입력 메소드 
	public int ocAdd(Manager m) {
		
		String sql = "INSERT INTO offered_courses(oc_id, oc_start_date, oc_end_date, classroom_id, bc_id) VALUES (CONCAT('OC', LPAD(SUBSTR(NVL((SELECT MAX(oc_id) FROM offered_courses),'OC000'),3)+1, 3, 0)),  ?, ?, ?, ?)";

		int result = this.jdbcTemplate.update(sql, m.getOc_start_date(), m.getOc_end_date(), m.getClassroom_id(), m.getBc_id());
		return result;
	}
	
	// 개설 과목 입력 메소드 
	public int osAdd(Manager m) {
		
		// 개설과목 id, 개설과목시작일, 개설과목종료일, 기초과목id, 교재id, 강사id, 개설과정id
		String sql = "INSERT INTO offered_subjects(os_id, os_start_date, os_end_date, bs_id, textbook_id, instructor_id, oc_id) VALUES(CONCAT('OS', LPAD(SUBSTR(NVL((SELECT MAX(os_id) FROM offered_subjects),'OS000'),3)+1, 3, 0)), ?, ?, ?, ?, ?, ?)";

		int result = this.jdbcTemplate.update(sql, m.getOs_start_date(), m.getOs_end_date(), m.getBs_id(), m.getTextbook_id(), m.getInstructor_id(), m.getOc_id());
		return result;
	}
	
	// 
	@Override
	public List<Manager> getinstructor(String bs_id) {
		String sql = "SELECT instructor_id, name_ FROM instructor_detail_view WHERE bs_id=?";
		
		List<Manager> result = this.jdbcTemplate.query(sql,  new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
				String instructor_id = rs.getString("instructor_id");
				String name_ = rs.getString("name_");
				
				Manager m = new Manager();
				m.setInstructor_id(instructor_id);
				m.setName_(name_);
				
				return m;
			}
			
			
		}, bs_id);
		
		return result;
	}
	
	// 수강생 개설과정 등록 메소드 
	public int studentOcAdd(String oc_id, String student_id) {

		String sql = "INSERT INTO course_history (oc_id, student_id) VALUES ( ? , ? )";

		int result = this.jdbcTemplate.update(sql, oc_id, student_id);
		return result;
	}


	// 수강생 입력 메소드 ※ 
	// **************************************************트랜잭션************************************************************************
	public int studentAdd(Manager m) {

		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		int result = 0;

		try {
			
			String sql1 = "INSERT INTO login (name_, ssn, phone, initial_reg_date, profile_img, pw, access_id, login_id) VALUES ( ?, ?, ?, ?, ?, ?, 'LV003', CONCAT('S', LPAD(SUBSTR(NVL((SELECT MAX(login_id) FROM login),'S000'),2)+1, 3, 0)))";
			result = this.jdbcTemplate.update(sql1, m.getName_(), m.getSsn(), m.getPhone(), m.getInitial_reg_date(),
					m.getProfile_img(), m.getSsn());

			String sql2 = "INSERT INTO students (student_id, student_reg_date) VALUES (CONCAT('S', LPAD(SUBSTR(NVL((SELECT MAX(login_id) FROM login),'S000'),2), 3, 0)), ?)";
			result = this.jdbcTemplate.update(sql2, m.getInitial_reg_date());

			transactionManager.commit(status);
			
		} catch (DataAccessException e) {

			System.out.println("Error in creating record, rolling back");
			transactionManager.rollback(status);

			throw e;
		}
		
		return result;
	}





	// --------------------------------------------------------------------------------------------------------------
	
	// 기초 과정 삭제 메소드 
	public int bcDelete(String value) {
		// 기초 과정 id
		String sql = "DELETE FROM basic_courses WHERE bc_id=?";

		int result = this.jdbcTemplate.update(sql, value);
		return result;

	}
	
	// 기초 과목 삭제 메소드 
	public int bsDelete(String value) {
		
		// 기초 과목 id
		String sql = "DELETE FROM basic_subjects WHERE bs_id=?";

		int result = this.jdbcTemplate.update(sql, value);
		return result;

	}
	
	// 강의실 삭제 메소드 
	public int classroomDelete(String value) {
		
		// 강의실 id
		String sql = "DELETE FROM classrooms WHERE classroom_id=?";

		int result = this.jdbcTemplate.update(sql, value);
		return result;

	}
	
	// 교재 삭제 메소드 
	public int textbookDelete(String value) {
		
		// 교재 id
		String sql = "DELETE FROM textbooks WHERE textbook_id=?";

		int result = this.jdbcTemplate.update(sql, value);
		return result;

	}
	
	// 강사 삭제 메소드 
	public int instructorDelete(String value) {
	
		String sql = "DELETE FROM login WHERE login_id=? AND access_id='LV002'";

		int result = this.jdbcTemplate.update(sql, value);
		return result;

	}

	// 개설 과정 삭제 메소드 
	public int ocDelete(String oc_id) {
	
		// 개설 과정 id
		String sql = "DELETE FROM manager_33_view WHERE oc_id=?";

		int result = this.jdbcTemplate.update(sql, oc_id);
		return result;

	}
	
	// 개설 과목 삭제 메소드 
	public int osDelete(String value) {
	
		// 개설 과목 id
		String sql = "DELETE FROM offered_subjects WHERE os_id = ?";

		int result = this.jdbcTemplate.update(sql, value);
		return result;

	}
	
	// 수강 취소 메소드 
	public int courseCancel(String oc_id, String student_id) {

		String sql = "DELETE FROM course_history WHERE oc_id =? AND student_id =?";

		int result = this.jdbcTemplate.update(sql, oc_id, student_id);
		return result;

	}

	// 수강생 중도탈락 메소드 
	public int studentDropout(String oc_id, String student_id, String dropout_date) {

		String sql = "INSERT INTO dropouts(oc_id, student_id, dropout_date) VALUES (?, ?, ?)";

		int result = this.jdbcTemplate.update(sql, oc_id, student_id, dropout_date);
		return result;

	}

	// 수강생 삭제 메소드 
	public int studentDelete(String student_id) {

		String sql = "DELETE FROM login WHERE login_id = ?";

		int result = this.jdbcTemplate.update(sql, student_id);
		return result;

	}

	// 수강생 삭제 메소드 - 프로필 사진명 얻어오기 
	public String getProfileImgName(String student_id) {
		String sql = "SELECT profile_img FROM login WHERE login_id=?";
		
		String result = this.jdbcTemplate.queryForObject(sql, String.class, student_id);
		return result;
	}


	// ---------------------------------------------------------------------------------------------------------------

	// 목록의 타이틀 정보 반환 메소드 
	@Override
	public Manager ocInfo(String oc_id) {
		
		/*
		CREATE OR REPLACE VIEW oc_detail_view
		AS
		SELECT oc.oc_id, bc.bc_title AS oc_title, oc.oc_start_date, oc.oc_end_date, c.classroom_name,
		    CASE
		    WHEN oc_start_date> SYSDATE THEN 'Y'
		    ELSE 'N'
		    END delete_status
		FROM offered_courses oc, basic_courses bc, classrooms c
		WHERE oc.bc_id=bc.bc_id AND oc.classroom_id=c.classroom_id;

		 */
		
		String sql = "SELECT oc_id, oc_title, oc_start_date, oc_end_date, delete_status FROM oc_detail_view WHERE oc_id=?";
		List<Manager> list = jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				String oc_id = rs.getString("oc_id");
				String oc_title = rs.getString("oc_title");
				String oc_start_date = rs.getString("oc_start_date");
				String oc_end_date = rs.getString("oc_end_date");
				String delete_status = rs.getString("delete_status");
				
				Manager m = new Manager();
				m.setOc_id(oc_id);
				m.setOc_title(oc_title);
				m.setOc_start_date(oc_start_date);
				m.setOc_end_date(oc_end_date);
				m.setDelete_status(delete_status);
				
				return m;
				
			}
		
		}, oc_id);
		
		Manager result = list.get(0);
		return result;
	}
	
	// 목록의 타이틀 정보 반환 메소드 
	@Override
	public Manager instructorInfo(String instructor_id) {
					
		String sql = "SELECT instructor_id, name_, ssn FROM instructor_detail_view WHERE instructor_id=?";
		List<Manager> list = jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				String instructor_id = rs.getString("instructor_id");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				
				Manager m = new Manager();
				m.setInstructor_id(instructor_id);
				m.setName_(name_);
				m.setSsn(ssn);
				
				return m;
				
			}
		
		}, instructor_id);
		
		Manager result = list.get(0);
		return result;
	}



	// 강사 강의가능과목 업데이트 메소드 ※******************************************************트랜잭션******************************************************************** 
	public int instructorUpdate(List<String> newavailables, String instructor_id) {

		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		int result = 0;

		try {

			String sql = "DELETE FROM available_subjects WHERE instructor_id = ?";
			result = this.jdbcTemplate.update(sql, instructor_id);

			String sql2 = "INSERT INTO available_subjects VALUES (?, ?)";
			for (String bs_id : newavailables) {		
				result = this.jdbcTemplate.update(sql2, instructor_id, bs_id);
			}

			transactionManager.commit(status);

		} catch (DataAccessException e) {

			System.out.println("Error in creating record, rolling back");
			transactionManager.rollback(status);

			throw e;
		}

		return result;
	}
	
	// 목록의 타이틀 정보 반환 메소드 
	@Override
	public Manager osInfo(String os_id) {
					
		String sql = "SELECT oc_id, oc_title, os_id, os_name, os_start_date, os_end_date FROM oc_os_login_detail_view WHERE os_id=?";
		List<Manager> list = jdbcTemplate.query(sql, new RowMapper<Manager>() {

			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				String oc_id = rs.getString("oc_id");
				String oc_title = rs.getString("oc_title");
				String os_name = rs.getString("os_name");
				String os_start_date = rs.getString("os_start_date");
				String os_end_date = rs.getString("os_end_date");
				
				Manager m = new Manager();
				m.setOc_id(oc_id);
				m.setOc_title(oc_title);
				m.setOs_name(os_name);
				m.setOs_start_date(os_start_date);
				m.setOs_end_date(os_end_date);
				
				return m;
				
			}
		
		}, os_id);
		
		Manager result = list.get(0);
		return result;
	}

}


