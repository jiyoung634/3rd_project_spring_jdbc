package com.test.domain;

public class Manager {
	
	// 기초 과정/과목 정보 - 과정 id, 과정명, 삭제가능여부, 과목id, 과목명
	private String bc_id, bc_title, delete_status, bs_id, bs_name;
	
	// 강의실 정보 - 강의실 id, 강의실명, 관리자id
	private String classroom_id, classroom_name, manager_id;
	// 정원
	private int maximum;
	
	//(수정된부분) 로그인 정보 - 등급
	private String access_id, access_name, profile_img;
	
	// 교재 정보 - 교재 id, 교재명, 출판사
	private String textbook_id, textbook_title, publisher, isbn;
	// 강사 정보 - 강사 id, 이름, 주민번호, 전화번호, 강의 가능 과목
	private String instructor_id, name_, ssn, phone, available_subjects;
	// 개설 과목/과정 정보 - 개설과목id, 개설과목명, 개설과정id, 개설과정명, 강의진행상태
	private String os_id, os_name, oc_id, oc_title, course_status;
	// 수강생 정보 - 수강생 id, 수료 여부
	private String student_id, learning_status;
	
	private String instructor_reg_date;

	private String os_start_date;

	private String os_end_date;

	private String initial_reg_date;

	private String course_end_date;
	
	private String login_id, pw, dropout_status, cancel_status;

	// 강사 등록일, 개설과목 시작일, 개설과목 종료일, 개설과정 시작일, 개설과정 종료일, 최초 등록일, 수료예정(중도탈락) 날짜, 중도탈락 날짜
	private String dropout_date;

	String oc_end_date;

	String oc_start_date;
	// 개설 과목 수, 수강생 등록 인원, 수강 횟수
	private int subject_offered_number, registerred_number, registration_count;
	
	// 출결 배점, 필기 배점, 실기 배점
	private int attendance_percentage, writing_percentage, practice_percentage;
	// 시험 날짜
	private String exam_date;
	// 시험 파일
	private String exam_file;
	// 출결 점수, 필기 점수, 실기 점수, 총점
	private int attendance_score, writing_score, practice_score, total_score;

	// getter, setter
	public String getBc_id() {
		return bc_id;
	}
	public void setBc_id(String bc_id) {
		this.bc_id = bc_id;
	}
	public String getBc_title() {
		return bc_title;
	}
	public void setBc_title(String bc_title) {
		this.bc_title = bc_title;
	}
	public String getDelete_status() {
		return delete_status;
	}
	public void setDelete_status(String delete_status) {
		this.delete_status = delete_status;
	}
	public String getBs_id() {
		return bs_id;
	}
	public void setBs_id(String bs_id) {
		this.bs_id = bs_id;
	}
	public String getBs_name() {
		return bs_name;
	}
	public void setBs_name(String bs_name) {
		this.bs_name = bs_name;
	}
	public String getClassroom_id() {
		return classroom_id;
	}
	public void setClassroom_id(String classroom_id) {
		this.classroom_id = classroom_id;
	}
	public String getClassroom_name() {
		return classroom_name;
	}
	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getTextbook_id() {
		return textbook_id;
	}
	public void setTextbook_id(String textbook_id) {
		this.textbook_id = textbook_id;
	}
	public String getTextbook_title() {
		return textbook_title;
	}
	public void setTextbook_title(String textbook_title) {
		this.textbook_title = textbook_title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getInstructor_id() {
		return instructor_id;
	}
	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
	}

	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public String getName_() {
		return name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAvailable_subjects() {
		return available_subjects;
	}
	public void setAvailable_subjects(String available_subjects) {
		this.available_subjects = available_subjects;
	}
	public String getOs_id() {
		return os_id;
	}
	public void setOs_id(String os_id) {
		this.os_id = os_id;
	}
	public String getOs_name() {
		return os_name;
	}
	public void setOs_name(String os_name) {
		this.os_name = os_name;
	}
	public String getOc_title() {
		return oc_title;
	}
	public void setOc_title(String oc_title) {
		this.oc_title = oc_title;
	}
	public String getCourse_status() {
		return course_status;
	}
	public void setCourse_status(String course_status) {
		this.course_status = course_status;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getLearning_status() {
		return learning_status;
	}
	public void setLearning_status(String learning_status) {
		this.learning_status = learning_status;
	}
	public String getInstructor_reg_date() {
		return instructor_reg_date;
	}
	public void setInstructor_reg_date(String instructor_reg_date2) {
		this.instructor_reg_date = instructor_reg_date2;
	}
	public String getOs_start_date() {
		return os_start_date;
	}
	public void setOs_start_date(String os_start_date2) {
		this.os_start_date = os_start_date2;
	}
	public String getOs_end_date() {
		return os_end_date;
	}
	public void setOs_end_date(String os_end_date2) {
		this.os_end_date = os_end_date2;
	}
	public String getOc_start_date() {
		return oc_start_date;
	}
	public void setOc_start_date(String oc_start_date2) {
		this.oc_start_date = oc_start_date2;
	}
	public String getOc_end_date() {
		return oc_end_date;
	}
	public void setOc_end_date(String oc_end_date2) {
		this.oc_end_date = oc_end_date2;
	}
	public String getInitial_reg_date() {
		return initial_reg_date;
	}
	public void setInitial_reg_date(String initial_reg_date2) {
		this.initial_reg_date = initial_reg_date2;
	}
	public String getCourse_end_date() {
		return course_end_date;
	}
	public void setCourse_end_date(String course_end_date2) {
		this.course_end_date = course_end_date2;
	}
	public int getSubject_offered_number() {
		return subject_offered_number;
	}
	public void setSubject_offered_number(int subject_offered_number) {
		this.subject_offered_number = subject_offered_number;
	}
	public int getRegisterred_number() {
		return registerred_number;
	}
	public void setRegisterred_number(int registerred_number) {
		this.registerred_number = registerred_number;
	}
	public int getRegistration_count() {
		return registration_count;
	}
	public void setRegistration_count(int registration_count) {
		this.registration_count = registration_count;
	}
	public int getAttendance_percentage() {
		return attendance_percentage;
	}
	public void setAttendance_percentage(int attendance_percentage) {
		this.attendance_percentage = attendance_percentage;
	}
	public int getWriting_percentage() {
		return writing_percentage;
	}
	public void setWriting_percentage(int writing_percentage) {
		this.writing_percentage = writing_percentage;
	}
	public int getPractice_percentage() {
		return practice_percentage;
	}
	public void setPractice_percentage(int practice_percentage) {
		this.practice_percentage = practice_percentage;
	}
	public String getExam_date() {
		return exam_date;
	}
	public void setExam_date(String exam_date) {
		this.exam_date = exam_date;
	}
	public String getExam_file() {
		return exam_file;
	}
	public void setExam_file(String exam_file) {
		this.exam_file = exam_file;
	}
	public int getAttendance_score() {
		return attendance_score;
	}
	public void setAttendance_score(int attendance_score) {
		this.attendance_score = attendance_score;
	}
	public int getWriting_score() {
		return writing_score;
	}
	public void setWriting_score(int writing_score) {
		this.writing_score = writing_score;
	}
	public int getPractice_score() {
		return practice_score;
	}
	public void setPractice_score(int practice_score) {
		this.practice_score = practice_score;
	}
	public int getTotal_score() {
		return total_score;
	}
	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}
	public String getOc_id() {
		return oc_id;
	}
	public void setOc_id(String oc_id) {
		this.oc_id = oc_id;
	}
	public String getDropout_date() {
		return dropout_date;
	}
	public void setDropout_date(String dropout_date) {
		this.dropout_date = dropout_date;
	}
	public String getAccess_id() {
		return access_id;
	}
	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}
	public String getAccess_name() {
		return access_name;
	}
	public void setAccess_name(String access_name) {
		this.access_name = access_name;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getDropout_status() {
		return dropout_status;
	}
	public void setDropout_status(String dropout_status) {
		this.dropout_status = dropout_status;
	}
	public String getCancel_status() {
		return cancel_status;
	}
	public void setCancel_status(String cancel_status) {
		this.cancel_status = cancel_status;
	}

	
}
