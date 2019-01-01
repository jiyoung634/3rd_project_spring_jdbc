package com.test.domain;

import java.time.*;


public class Instructor {
	
	// 강사 개인정보 - 이름, 주민번호, 전화번호, 강의 가능 과목
	private String instructor_id, name_, ssn, phone, available_subjects;
	
	// 강사 개인정보 - 최초 등록일
	private String initial_reg_date;

	
	
	// 강의 과목 조회 - 개설과목id, 개설과목명, 개설과정명, 강의실, 교재명, 출판사, isbn 강의 진행 상태
	private String os_id, os_name, oc_title, classroom_name, textbook_title, publisher, isbn, course_status;
	
	// 강의 과목 조회 - 개설과목 시작일, 개설과목 종료일, 개설과정 시작일, 개설과정 종료일
	private String os_start_date, os_end_date, oc_start_date, oc_end_date;

	// 강의 과목 조회 - 수강생 등록 인원
	private int registerred_number;

	
	
	// 개설 과정의 수강생 목록 - 수강생 id, 수료 여부
	private String student_id, learning_status;
	
	// 개설 과정의 수강생 목록 - 수료예정(중도탈락) 날짜
	private String course_end_date;

	
	
	// 출결 배점, 필기 배점, 실기 배점
	private int attendance_percentage, writing_percentage, practice_percentage;

	// 시험 날짜
	private String exam_date;
	
	// 시험 파일
	private String exam_file;
	
	// 입력 / 삭제 가능 여부
	private String delete_status;
	
	//성적 등록 인원수
	private String score_added_number;

	// 출결 점수, 필기 점수, 실기 점수, 총점
	private int attendance_score, writing_score, practice_score, total_score;

	
	// getter, setter	
	public String getName_() {
		return name_;
	}

	public String getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
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

	public String getInitial_reg_date() {
		return initial_reg_date;
	}

	public void setInitial_reg_date(String initial_reg_date) {
		this.initial_reg_date = initial_reg_date;
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

	public String getClassroom_name() {
		return classroom_name;
	}

	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
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

	public String getCourse_status() {
		return course_status;
	}

	public void setCourse_status(String course_status) {
		this.course_status = course_status;
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

	public int getRegisterred_number() {
		return registerred_number;
	}

	public void setRegisterred_number(int registerred_number) {
		this.registerred_number = registerred_number;
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

	public String getCourse_end_date() {
		return course_end_date;
	}

	public void setCourse_end_date(String course_end_date) {
		this.course_end_date = course_end_date;
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

	public String getScore_added_number() {
		return score_added_number;
	}

	public void setScore_added_number(String score_added_number) {
		this.score_added_number = score_added_number;
	}

	public String getDelete_status() {
		return delete_status;
	}

	public void setDelete_status(String delete_status) {
		this.delete_status = delete_status;
	}
	


	@Override
	public String toString() {
		return "Instructor [instructor_id=" + instructor_id + ", name_=" + name_ + ", ssn=" + ssn + ", phone=" + phone
				+ ", available_subjects=" + available_subjects + ", initial_reg_date=" + initial_reg_date + ", os_id="
				+ os_id + ", os_name=" + os_name + ", oc_title=" + oc_title + ", classroom_name=" + classroom_name
				+ ", textbook_title=" + textbook_title + ", course_status=" + course_status + ", os_start_date="
				+ os_start_date + ", os_end_date=" + os_end_date + ", oc_start_date=" + oc_start_date + ", oc_end_date="
				+ oc_end_date + ", registerred_number=" + registerred_number + ", student_id=" + student_id
				+ ", learning_status=" + learning_status + ", course_end_date=" + course_end_date
				+ ", attendance_percentage=" + attendance_percentage + ", writing_percentage=" + writing_percentage
				+ ", practice_percentage=" + practice_percentage + ", exam_date=" + exam_date + ", exam_file="
				+ exam_file + ", score_added_number=" + score_added_number + ", attendance_score=" + attendance_score
				+ ", writing_score=" + writing_score + ", practice_score=" + practice_score + ", total_score="
				+ total_score + "]";
	}

}