package com.test.dao;

import java.util.List;

import com.test.domain.Manager;

public interface ManagerDAO {

	// 로그인 정보 메소드 
		public List<Manager> loginInfo(String login_id);
		
		// 등록된 과정 목록 출력 메소드 
		public List<Manager> bcList();
		
		// 등록된 과정 목록(삭제 가능 여부 포함) 출력 메소드 
		public List<Manager> bcList_ds();
		
		// 등록된 과목 목록 출력 메소드 
		public List<Manager> bsList();
		
		// 등록된 과목 목록(삭제 가능 여부 포함) 출력 메소드 
		public List<Manager> bsList_ds();
		
		// 강의실 목록 출력 메소드 
		public List<Manager> classroomList();
		
		// 강의실 목록(삭제 가능 여부 포함) 출력 메소드 
		public List<Manager> classroomList_ds();
		
		// 교재 목록 출력 메소드 
		public List<Manager> textbookList();
		
		// 교재 목록(삭제 가능 여부 포함) 출력 메소드 
		public List<Manager> textbookList_ds();

		// 강사 목록 출력 메소드  
		public List<Manager> instructorList();
		
		// 강사 id를 통한 강의 가능 과목 검색 메소드 
		public List<String> availableSubjectList(String instructor_id);

		// 강사 강의 정보 출력 메소드 
		public List<Manager> courseInfoList(String instructor_id);

		// 강사 목록(삭제 가능 여부 포함) 출력 메소드 
		public List<Manager> instructorList_ds();
		
		// oc_id로 특정 개설 과정의 과목 정보 검색 메소드 
		public List<Manager> oc_osSearch(String oc_id);
		
		// oc_id로 특정 개설 과정의 수강생 검색 메소드 
		public List<Manager> oc_studentSearch(String oc_id);
		
		// 개설 과정 목록(삭제 가능 여부 포함) 출력 메소드 
		public List<Manager> ocList_ds();
		
		// 등록된 강사 목록 출력 메소드 41 
		public List<Manager> instructorList41();
		

		// 개설 과정 목록 출력 메소드
		public List<Manager> ocList51();
		
		// 수강생 id로 특정 수강생의 수강 취소 가능 과정 검색 메소드  
		public List<Manager> ocSearchToCancle(String student_id);
		
		// 수강생 id로 특정 수강생의 중도 탈락 가능 과정 검색 메소드  
		public List<Manager> ocSearchForDropout(String student_id);
		
		
		// 수강생 id로 특정 수강생의 수강 과정 검색 메소드  
		public List<Manager> ocSearch(String student_id);
		
		// oc_id로 특정 과정의 개설 과목 목록(시험 정보 포함) 검색 메소드  
		public List<Manager> osInfoSearch(String oc_id);

		// 수강생 정보 검색 출력 메소드 
		// 수강생 id, 수강생 이름, 주민번호 뒷자리 기준으로 검색
		public List<Manager> studentInfoSearch(String key, String value);

		// 수강생 정보 검색 출력 메소드  
		// 수강생 id, 수강생 이름, 주민번호 뒷자리 기준으로 검색
		public List<Manager> studentInfoSearch(String key, String value, int start_row, int end_row);	
		
		// 
		public Manager studentInfo(String student_id);
		
		// 총 수강생 수 출력 메소드  
		public int studentCount();
		
		// os_id로 수강생 성적 검색 메소드  
		public List<Manager> studentScoreList(String os_id);
		
		// 개설 과정 목록 출력 메소드 61  
		public List<Manager> ocList61();
		
		// oc_id로 특정 과목의 성적 검색 메소드  
		public List<Manager> stu_scoreSearch(String student_id, String oc_id);
		
		
		// -------------------------------------------------------------------------------------------------------
		
		// 기초 과정 입력 메소드 
		public int bcAdd(String value);
		
		// 기초 과목 입력 메소드 
		public int bsAdd(String value);
		
		// 강의실 입력 메소드 
		public int classroomAdd(Manager m);
		
		// 교재 정보 입력 메소드 
		public int textbookAdd(Manager m);
		
		// 강사 정보 입력 메소드  ******************************************************트랜잭션******************************************************************** 
		public int instructorAdd(Manager m, List<String> newavailables);
			
		// 개설 과정 입력 메소드 
		public int ocAdd(Manager m);
		
		// 개설 과목 입력 메소드 
		public int osAdd(Manager m);
		
		// 
		public List<Manager> getinstructor(String bs_id);
		
		// 수강생 개설과정 등록 메소드 
		public int studentOcAdd(String oc_id, String student_id);

		// 수강생 입력 메소드 ※ 
		// **************************************************트랜잭션************************************************************************
		public int studentAdd(Manager m);




		// --------------------------------------------------------------------------------------------------------------
		
		// 기초 과정 삭제 메소드 
		public int bcDelete(String value);
		
		// 기초 과목 삭제 메소드 
		public int bsDelete(String value);
		
		// 강의실 삭제 메소드 
		public int classroomDelete(String value);
		
		// 교재 삭제 메소드 
		public int textbookDelete(String value);
		
		// 강사 삭제 메소드 
		public int instructorDelete(String value);
		
		// 개설 과정 삭제 메소드 
		public int ocDelete(String oc_id);
		
		// 개설 과목 삭제 메소드 
		public int osDelete(String value);
		
		// 수강 취소 메소드 
		public int courseCancel(String oc_id, String student_id);

		// 수강생 중도탈락 메소드 
		public int studentDropout(String oc_id, String student_id, String dropout_date);

		// 수강생 삭제 메소드 
		public int studentDelete(String student_id);

		// 수강생 삭제 메소드 - 프로필 사진명 얻어오기 
		public String getProfileImgName(String student_id);


		// ---------------------------------------------------------------------------------------------------------------

		// 목록의 타이틀 정보 반환 메소드 
		public Manager ocInfo(String oc_id);
		
		// 목록의 타이틀 정보 반환 메소드 
		public Manager instructorInfo(String instructor_id);


		// 강사 강의가능과목 업데이트 메소드 ※******************************************************트랜잭션******************************************************************** 
		public int instructorUpdate(List<String> newavailables, String instructor_id);
		
		// 목록의 타이틀 정보 반환 메소드 
		public Manager osInfo(String os_id);
}