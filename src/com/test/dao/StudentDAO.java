package com.test.dao;

import java.util.List;

import com.test.domain.Student;

public interface StudentDAO {

	// 메인> 수강생 정보
	public List<Student> studentList(String student_id);
	
	// 성적 조회> 수강생 수강 과정 목록
	public List<Student> studentCourseList(String student_id);
	
	// 성적 조회> 과정별 성적 조회> 성적 목록
	public List<Student> scoreList(String student_id, String oc_id);
	
	// 성적 조회> 과정별 성적 조회> 과정 정보 얻어오기
	public Student ocInfo(String oc_id);

}
	
