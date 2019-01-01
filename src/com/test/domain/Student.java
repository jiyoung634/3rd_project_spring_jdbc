package com.test.domain;

public class Student {
	
	// 수강생 개인정보 - 수강생 id, 이름, 주민번호, 전화번호
	private String student_id, name_, ssn, phone;
	// 수강생 개인정보 - 수강횟수
	private int registration_count;
	// 수강생 개인정보 - 최초등록일
	private String initial_reg_date;
	
	// 수강생 수강 과정 - 개설 과정 id, 개설과정명, 강의실명, 수료 여부, 성적 조회 가능 여부
	private	String oc_id, oc_title, classroom_name, learning_status, score_status;
	// 수강생 수강 과정 - 개설과정 시작일, 개설과정 종료일, 수료예정(중도탈락)날짜
	private String  oc_start_date, oc_end_date, course_end_date;
	
	// 수강생 성적 조회 - 개설 과목 id, 개설 과목명, 교재명, ISBN, 출판사, 교재커버URL, 시험파일
	private String os_id, os_name, textbook_title, isbn, coverSmallUrl, publisher, exam_file;
	// 수강생 성적 조회 - 개설 과목 시작일, 개설 과목 종료일, 시험 날짜, 프로필 이미지, (강사)강의가능과목
	private String os_start_date, os_end_date, exam_date, profile_img, available_subjects; 
	// 수강생 성적 조회 - 출결배점, 필기배점, 실기배점, 출결점수, 필기점수, 실기점수, 총점
	private int attendance_percentage, writing_percentage, practice_percentage, attendance_score, writing_score, practice_score, total_score;
	
	
	// getter, setter
	public String getInitial_reg_date() {
		return initial_reg_date;
	}
	public void setInitial_reg_date(String initial_reg_date) {
		this.initial_reg_date = initial_reg_date;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
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
	public int getRegistration_count() {
		return registration_count;
	}
	public void setRegistration_count(int registration_count) {
		this.registration_count = registration_count;
	}
	public String getOc_id() {
		return oc_id;
	}
	public void setOc_id(String oc_id) {
		this.oc_id = oc_id;
	}
	public String getOc_title() {
		return oc_title;
	}
	public void setOc_title(String oc_title) {
		this.oc_title = oc_title;
	}
	public String getClassroom_name() {
		return classroom_name;
	}
	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
	}
	public String getLearning_status() {
		return learning_status;
	}
	public void setLearning_status(String learning_status) {
		this.learning_status = learning_status;
	}
	public String getOc_start_date() {
		return oc_start_date;
	}
	public void setOc_start_date(String oc_start_date) {
		this.oc_start_date = oc_start_date;
	}
	public String getOc_end_date() {
		return oc_end_date;
	}
	public void setOc_end_date(String oc_end_date) {
		this.oc_end_date = oc_end_date;
	}
	public String getCourse_end_date() {
		return course_end_date;
	}
	public void setCourse_end_date(String course_end_date) {
		this.course_end_date = course_end_date;
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
	public String getTextbook_title() {
		return textbook_title;
	}
	public void setTextbook_title(String textbook_title) {
		this.textbook_title = textbook_title;
	}
	public String getExam_file() {
		return exam_file;
	}
	public void setExam_file(String exam_file) {
		this.exam_file = exam_file;
	}
	public String getOs_start_date() {
		return os_start_date;
	}
	public void setOs_start_date(String os_start_date) {
		this.os_start_date = os_start_date;
	}
	public String getOs_end_date() {
		return os_end_date;
	}
	public void setOs_end_date(String os_end_date) {
		this.os_end_date = os_end_date;
	}
	public String getExam_date() {
		return exam_date;
	}
	public void setExam_date(String exam_date) {
		this.exam_date = exam_date;
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
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCoverSmallUrl() {
		return coverSmallUrl;
	}
	public void setCoverSmallUrl(String coverSmallUrl) {
		this.coverSmallUrl = coverSmallUrl;
	}
	public String getAvailable_subjects() {
		return available_subjects;
	}
	public void setAvailable_subjects(String available_subjects) {
		this.available_subjects = available_subjects;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getScore_status() {
		return score_status;
	}
	public void setScore_status(String score_status) {
		this.score_status = score_status;
	}
	
	
}
