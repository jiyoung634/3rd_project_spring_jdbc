package com.test.dao;

import java.util.List;

import com.test.domain.Instructor;

public interface InstructorDAO {

	// 강사 개인정보 출력 메소드
	public List<Instructor> instructorList(String instructor_id);

	// 강의 스케줄 출력 메소드
	public List<Instructor> courseScheduleList(String instructor_id);

	// os_id로 특정 개설 과목의 수강생 목록 검색 메소드
	public List<Instructor> studentSearch(String instructor_id, String os_id);

	// 배점 입력 가능한 과목 목록 출력 메소드
	public List<Instructor> percentage_addible_osList(String instructor_id);

	// 배점 삭제 가능한 과목 목록 출력 메소드
	public List<Instructor> percentage_deletable_osList(String instructor_id);

	// 시험 날짜/파일 삭제 가능한 과목 목록 출력 메소드
	public List<Instructor> exam_osList(String instructor_id);

	// 성적 확인 목록 출력 메소드
	public List<Instructor> score_osList(String instructor_id);

	// os_id로 특정 과목의 수강생 성적 검색 메소드
	public List<Instructor> scoreSearch(String instructor_id, String os_id);

	// ---------------------------------------------------------------------

	// 개설과목 배점 입력 메소드
	public int percentageAdd(Instructor i);

	// 개설과목 시험 정보 입력 메소드
	public int examAdd(Instructor i);

	// 성적 입력 - 배점 반환 메소드
	public Instructor scorePercentage(String os_id);

	// 성적 입력 메소드
	public int scoreAdd(Instructor i);

	// ----------------------------------------------------------------------------

	// 배점 삭제 메소드
	public int percentageDelete(String os_id, String instructor_id);

	// 성적 삭제 메소드
	public int scoreDelete(String instructor_id, String student_id, String os_id);

	// 시험 날짜/파일 삭제 메소드
	public int examInfoDelete(String os_id, String login_id);

	public Instructor ocosInfo(String os_id);
}
