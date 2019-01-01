package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xml.sax.SAXException;

import com.test.domain.*;
import com.test.util.InterparkAPI;
import com.test.dao.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerJDBCTemplate jdbcTemplate;
	
	@Autowired
	private ServletContext context;
	
	private String manager_id;

	// 1.Main
	@RequestMapping("/main")
	public String main(Model model, HttpSession session) {
		
		Login l = (Login) session.getAttribute("managerLoginInfo");
		this.manager_id = l.getLogin_id();

		// 관리자 정보
		List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		String name_ = list.get(0).getName_();
		String phone = list.get(0).getPhone();
		
		model.addAttribute("name_", name_);
		model.addAttribute("phone", phone);
		
		return "/manager/main";
	}

	// 2-1.기초 정보 관리/과정 관리
	@RequestMapping("/basicinfo/course")
	public String basicinfoCourse(Model model) {
		
		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();

		List<Manager> bcList_ds = this.jdbcTemplate.bcList_ds();

		//model.addAttribute("name_", name_);
		model.addAttribute("bcList_ds", bcList_ds);

		return "/manager/basicinfo-course";
	}

	// 2-2.기초 정보 관리/과정 관리(새로 등록)
	@RequestMapping("/basicinfo/course/insert")
	public String bcAdd( Model model, RedirectAttributes rttr, String bc_title) {
		int result = 0;
		
		
		result = this.jdbcTemplate.bcAdd(bc_title);

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/basicinfo/course";
	}

	// 2-3.기초 정보 관리/과정 관리(삭제)
	@RequestMapping("/basicinfo/course/delete")
	public String bcDelete(String bc_id, RedirectAttributes rttr) {
		
		int result = 0;

		result = this.jdbcTemplate.bcDelete(bc_id);

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/basicinfo/course";
	}

	// 2-1.기초 정보 관리/과목 관리
	@RequestMapping("/basicinfo/subject")
	public String basicinfoSubject(Model model) {

		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();
		
		List<Manager> bsList_ds = this.jdbcTemplate.bsList_ds();

		//model.addAttribute("name_", name_);
		model.addAttribute("bsList_ds", bsList_ds);

		return "/manager/basicinfo-subject";
	}
	
	// 2-2.기초 정보 관리/과목 관리(새로 등록)
	@RequestMapping("/basicinfo/subject/insert")
	public String bsAdd(Model model, RedirectAttributes rttr, String bs_name) {
		int result = 0;

		result = this.jdbcTemplate.bsAdd(bs_name);
				
		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/basicinfo/subject";
	}

	// 2-3.기초 정보 관리/과목 관리(삭제)
	@RequestMapping("/basicinfo/subject/delete")
	public String bsDelete(String bs_id, RedirectAttributes rttr) {
		
		int result = 0;

		result = this.jdbcTemplate.bsDelete(bs_id);

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/basicinfo/subject";
	}

	// 2-1.기초 정보 관리/강의실 관리
	@RequestMapping("/basicinfo/class")
	public String basicinfoClass(Model model) {
		
		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();

		List<Manager> classroomList_ds = this.jdbcTemplate.classroomList_ds();

		//model.addAttribute("name_", name_);
		model.addAttribute("classroomList_ds", classroomList_ds);

		return "/manager/basicinfo-class";
	}

	// 2-2.기초 정보 관리/강의실 관리(새로 등록)
	@RequestMapping("/basicinfo/class/insert")
	public String classroomAdd(Model model, RedirectAttributes rttr, Manager m) {
		
		int result = 0;

		result = this.jdbcTemplate.classroomAdd(m);

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/basicinfo/class";
	}

	// 2-3.기초 정보 관리/강의실 관리(삭제)
	@RequestMapping("/basicinfo/class/delete")
	public String classroomDelete(String classroom_id, RedirectAttributes rttr) {

		int result = 0;

		result = this.jdbcTemplate.classroomDelete(classroom_id);

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/basicinfo/class";
	}

	// 2-1.기초 정보 관리/교재 관리
	@RequestMapping("/basicinfo/book")
	public String basicinfoBook(Model model) {
		
		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();

		List<Manager> textbookList_ds = this.jdbcTemplate.textbookList_ds();

	//	model.addAttribute("name_", name_);
		model.addAttribute("textbookList_ds", textbookList_ds);

		return "/manager/basicinfo-book";
	}

	// 2-2.기초 정보 관리/교재 관리(새로 등록)
	@RequestMapping("/basicinfo/book/insert")
	public String textbookAdd(Model model, RedirectAttributes rttr, Manager m) {
		int result = 0;

		result = this.jdbcTemplate.textbookAdd(m);

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/basicinfo/book";
	}

	// 2-3.기초 정보 관리/교재 관리(삭제)
	@RequestMapping("/basicinfo/book/delete")
	public String textbookDelete(String textbook_id, RedirectAttributes rttr) {

		int result = 0;

		result = this.jdbcTemplate.textbookDelete(textbook_id);

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/basicinfo/book";
	}

	// 3.강사 계정 관리
	@RequestMapping("/instructor")
	public String instructor(Model model) {

		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();
		
		List<Manager> instructorList = this.jdbcTemplate.instructorList_ds();
		List<Manager> bsList = this.jdbcTemplate.bsList();

		//model.addAttribute("name_", name_);
		model.addAttribute("instructorList", instructorList);
		model.addAttribute("bsList", bsList);

		return "/manager/instructor";
	}

	// 강의 가능 과목 리스트
	@RequestMapping("/instructor/bslist")
	public @ResponseBody List<String> availableSubjectList(String instructor_id) {
	
		List<String> availableSubjectList = this.jdbcTemplate.availableSubjectList(instructor_id);

		return availableSubjectList;
	}
	
	// 3.강사 계정 관리/수정
	@RequestMapping("/instructor/update")
	public String instructorUpdate(Model model, RedirectAttributes rttr, @RequestParam(value="bs_id") List<String> newavailables, String instructor_id) {
		
		int result = 0;

		try {
			result = this.jdbcTemplate.instructorUpdate(newavailables, instructor_id);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		

		rttr.addFlashAttribute("update_success", result);
		
		return "redirect:/manager/instructor";
	}

	// 3.강사 계정 관리/등록
	@RequestMapping("/instructor/instructorAdd")
	public String instructorAdd(FileModel file, Model model, RedirectAttributes rttr, Manager m, @RequestParam(value="bs_id") List<String> newavailables) throws IOException {

		MultipartFile multipartFile = file.getFile();
		String uploadPath = context.getRealPath("") + "resources/uploads/pictures" + File.separator;
		System.out.println(uploadPath);
		if (multipartFile.getOriginalFilename() == null) {
			System.out.println("파일없음.");
		}

		String fileName = java.util.UUID.randomUUID().toString();
		String contentType = multipartFile.getContentType();
		String extension = "." + contentType.substring(contentType.indexOf("/") + 1);
		int fail = 1;

		System.out.println(contentType);
			
		// 이미지를 등록한 경우
		if (contentType.equals("image/gif") || contentType.equals("image/jpeg") || contentType.equals("image/png")
				|| contentType.equals("image/bmp")) {

			FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + fileName + extension));

				// DB에 저장
			m.setProfile_img(fileName + extension);
			this.jdbcTemplate.instructorAdd(m, newavailables);
			//this.jdbcTemplate.inst_availableSubjectAdd(m, newavailables);

			fail = 0;

		}

		// 이미지를 등록하지 않은 경우
		if (contentType.equals("application/octet-stream")) {
			// DB에 저장
			m.setProfile_img("instructor.png");
			this.jdbcTemplate.instructorAdd(m, newavailables);
			// this.jdbcTemplate.inst_availableSubjectAdd(m, newavailables);

			fail = 0;
		}

		rttr.addFlashAttribute("fail", fail);
		return "redirect:/manager/instructor";
	}
		
	// 3.강사 계정 관리/삭제
	@RequestMapping("/instructor/delete")
	public String instructorDelete(String instructor_id, RedirectAttributes rttr) {

		// 사진 파일 삭제 (기본 파일(instructor.png)인 경우는 제외)
		String uploadPath = context.getRealPath("") + "resources/uploads/pictures" + File.separator;
		String fileName = this.jdbcTemplate.getProfileImgName(instructor_id);
		if (!fileName.equals("instructor.png")) {
			File file = new File(uploadPath + "/" + fileName);
			file.delete();
		}

		// DB 삭제
		int result = 0;
		try {
			result = this.jdbcTemplate.instructorDelete(instructor_id);

		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/instructor";
	}

	// 3.강사 계정 관리/강의 정보
	@RequestMapping("/instructor/courseinfo")
	public String instructorCourseinfo(Model model, String instructor_id) {

		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();
		
		List<Manager> instructorList = this.jdbcTemplate.instructorList();
		List<Manager> courseInfoList = this.jdbcTemplate.courseInfoList(instructor_id);
		Manager instructorInfo = this.jdbcTemplate.instructorInfo(instructor_id);
		
		//model.addAttribute("name_", name_);
		model.addAttribute("instructor_id", instructor_id);
		model.addAttribute("instructorList", instructorList);
		model.addAttribute("courseInfoList", courseInfoList);
		model.addAttribute("instructorInfo", instructorInfo);

		
		return "/manager/instructor-courseinfo";
	}

	// 4.개설 과정 및 과목 관리
	@RequestMapping("/course")
	public String course(Model model) {
		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();
		
		List<Manager> ocList_ds = this.jdbcTemplate.ocList_ds();
		List<Manager> bcList = this.jdbcTemplate.bcList();
		List<Manager> classroomList = this.jdbcTemplate.classroomList();
		
		//model.addAttribute("name_", name_);
		model.addAttribute("ocList_ds", ocList_ds);
		model.addAttribute("bcList", bcList);
		model.addAttribute("classroomList", classroomList);
				
		return "/manager/course";
	}
	
	// 4.개설 과정 및 과목 관리/개설 과정 등록
	@RequestMapping("/course/courseInsert")
	public String courseInsert(Model model, RedirectAttributes rttr, Manager m) {
		
		int result = 0;
		try {
			result = this.jdbcTemplate.ocAdd(m);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/manager/course";
	}
	
	// 4.개설 과정 및 과목 관리/개설 과정 삭제
	@RequestMapping("/course/courseDelete")
	public String courseDelete(String oc_id, RedirectAttributes rttr) throws DataAccessException {

		int result = 0;
		try {
			result = this.jdbcTemplate.ocDelete(oc_id);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
				
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/manager/course";
	}
	
	// 4.개설 과정 및 과목 관리/개설 과정 목록/개설 과목 목록
	@RequestMapping("/course/subject")
	public String courseSubject(Model model, String oc_id) {

	//	List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();
		
		Manager ocInfo = this.jdbcTemplate.ocInfo(oc_id);
		List<Manager> oc_osSearch = this.jdbcTemplate.oc_osSearch(oc_id);
		List<Manager> bsList = this.jdbcTemplate.bsList();
		List<Manager> textbookList = this.jdbcTemplate.textbookList();
		List<Manager> instructorList = this.jdbcTemplate.instructorList();
		List<Manager> instructorList41 = this.jdbcTemplate.instructorList41();
		
		model.addAttribute("oc_id", oc_id);
		model.addAttribute("ocInfo", ocInfo);
		//model.addAttribute("name_", name_);
		model.addAttribute("oc_osSearch", oc_osSearch);
		model.addAttribute("bsList", bsList);
		model.addAttribute("textbookList", textbookList);
		model.addAttribute("instructorList", instructorList);
		model.addAttribute("instructorList41", instructorList41);
				
		return "/manager/course-subject";
	}
	
	// 4.개설 과정 및 과목 관리/개설 과정 목록/개설 과목 목록/개설 과목 등록
	@RequestMapping("/course/subject/subjectInsert")
	public String subjectInsert(Model model, RedirectAttributes rttr, Manager m) {
		
		int result = 0;
		try {
			result = this.jdbcTemplate.osAdd(m);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/manager/course/subject?oc_id=" + m.getOc_id();
	}
	
	// 4.개설 과정 및 과목 관리/개설 과정 목록/개설 과목 목록/개설 과목 삭제
	@RequestMapping("/course/subject/subjectDelete")
	public String subjectDelete(String os_id, String oc_id, RedirectAttributes rttr) throws DataAccessException {

		int result = 0;
		try {
			result = this.jdbcTemplate.osDelete(os_id);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
				
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/manager/course/subject?oc_id=" + oc_id;
	}
	
	// 4.개설 과정 및 과목 관리/개설 과정 목록/수강생 목록
	@RequestMapping("/course/studentlist")
	public String courseStudentlist(Model model, String oc_id) {

		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();
		
		List<Manager> oc_studentSearch = this.jdbcTemplate.oc_studentSearch(oc_id);
		Manager ocInfo = this.jdbcTemplate.ocInfo(oc_id);
		
	//	model.addAttribute("name_", name_);
		model.addAttribute("oc_id", oc_id);
		model.addAttribute("oc_studentSearch", oc_studentSearch);
		model.addAttribute("ocInfo", ocInfo);
		
		return "/manager/course-studentlist";
	}
	
	@RequestMapping("/getinstructor")
	public  @ResponseBody List<Manager> getinstructor(String bs_id) {
		
		List<Manager> instructorList = this.jdbcTemplate.getinstructor(bs_id);

		return instructorList;
	}

	// 5.수강생 관리
	@RequestMapping("/student")
	public String student(Model model, String key, String value, String pageNum) {
		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();

		// 검색, 페이징 처리
		if (key == null) {
			key = "ALL";
			value = "";
		}
		
		int totalCount = this.jdbcTemplate.studentCount();

		// 페이징 처리
		if (pageNum == null) {
			pageNum = "1";
		}

		int start_row = Integer.parseInt(pageNum);
		if (start_row > 1) {
			start_row = ((Integer.parseInt(pageNum) - 1) * 10) + 1;
		}
		int limit_count = 9;
		int end_row = start_row + limit_count;

		int previous = Integer.parseInt(pageNum) - 1;
		int next = Integer.parseInt(pageNum) + 1;


		
		// 수강생 목록 출력
		List<Manager> studentList = this.jdbcTemplate.studentInfoSearch(key, value, start_row, end_row);

		int resultSize = studentList.size();
		
		// 수강 등록할 과정 출력(in Modal)
		List<Manager> ocAddibleList = this.jdbcTemplate.ocList51();

	//	model.addAttribute("name_", name_);
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		model.addAttribute("studentList", studentList);
		model.addAttribute("resultSize", resultSize);
		model.addAttribute("studentCount", studentList.size());
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);
		model.addAttribute("ocAddibleList", ocAddibleList);
		model.addAttribute("ocAddibleListCount", ocAddibleList.size());
		model.addAttribute("limit_count", limit_count+1);
		
		return "/manager/student";
	}

	// 5.수강생 관리/수강 취소 페이지
	@RequestMapping("/student/coursecancel")
	public String studentCoursecancle(Model model, String student_id) {
		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();

		// 수강생 정보
		Manager studentInfo = this.jdbcTemplate.studentInfoSearch("student_id", student_id).get(0);
		// 수강생의 수강취소 처리 가능 과정
		List<Manager> cancelList = this.jdbcTemplate.ocSearchToCancle(student_id);

	//	model.addAttribute("name_", name_);
		model.addAttribute("studentInfo", studentInfo);
		model.addAttribute("cancelList", cancelList);
		model.addAttribute("cancelListCount", cancelList.size());

		return "/manager/student-coursecancel";
	}

	// 5.수강생 관리/수강 취소
	@RequestMapping("/student/doCoursecancel")
	public String doCoursecancel(RedirectAttributes rttr, String oc_id, String student_id) {
		int result = 0;
		try {
			result = this.jdbcTemplate.courseCancel(oc_id, student_id);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/student/coursecancel?student_id=" + student_id;
	}

	// 5.수강생 관리/수강생 상세보기
	@RequestMapping("/student/detail")
	public String studentDetail(Model model, String student_id) {
		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();

		// 수강생 정보
		Manager studentInfo = this.jdbcTemplate.studentInfoSearch("student_id", student_id).get(0);
		// 수강생의 과정 수강 이력
		List<Manager> ocSearchList = this.jdbcTemplate.ocSearch(student_id);

	//	model.addAttribute("name_", name_);
		model.addAttribute("studentInfo", studentInfo);
		model.addAttribute("ocSearchList", ocSearchList);
		model.addAttribute("ocSearchListCount", ocSearchList.size());

		return "/manager/student-detail";
	}

	// 5.수강생 관리/수강생 중도탈락 페이지
	@RequestMapping("/student/dropout")
	public String studentDropout(Model model, String student_id) {
		//List<Manager> list = this.jdbcTemplate.loginInfo(manager_id);
		//String name_ = list.get(0).getName_();

		// 수강생 정보
		Manager studentInfo = this.jdbcTemplate.studentInfoSearch("student_id", student_id).get(0);

		// 수강생의 중도탈락 처리 가능 과정
		List<Manager> dropoutList = this.jdbcTemplate.ocSearchForDropout(student_id);

		//model.addAttribute("name_", name_);
		model.addAttribute("studentInfo", studentInfo);
		model.addAttribute("dropoutList", dropoutList);
		model.addAttribute("dropoutListCount", dropoutList.size());

		return "/manager/student-dropout";
	}

	// 5.수강생 관리/수강생 중도탈락 처리
	@RequestMapping("/student/doDropout")
	public String doDropout(RedirectAttributes rttr, Manager m) {
		int result = 0;
		try {
			result = this.jdbcTemplate.studentDropout(m.getOc_id(), m.getStudent_id(), m.getDropout_date());
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/student/dropout?student_id=" + m.getStudent_id();
	}

	// 5.수강생 등록
	@RequestMapping("/student/studentInsert")
	public String studentInsert(FileModel file, Model model, RedirectAttributes rttr, Manager m) throws IOException {

		MultipartFile multipartFile = file.getFile();
		String uploadPath = context.getRealPath("") + "resources/uploads/pictures" + File.separator;
		System.out.println(uploadPath);
		if (multipartFile.getOriginalFilename() == null) {
			System.out.println("파일없음.");
		}

		String fileName = java.util.UUID.randomUUID().toString();
		String contentType = multipartFile.getContentType();
		String extension = "." + contentType.substring(contentType.indexOf("/") + 1);
		int fail = 1;

		// 이미지를 등록한 경우
		if (contentType.equals("image/gif") || contentType.equals("image/jpeg") || contentType.equals("image/png")
				|| contentType.equals("image/bmp")) {

			FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + fileName + extension));

			// DB에 저장
			m.setProfile_img(fileName + extension);
			this.jdbcTemplate.studentAdd(m);

			fail = 0;

		}

		// 이미지를 등록하지 않은 경우
		if (contentType.equals("application/octet-stream")) {
			// DB에 저장
			m.setProfile_img("avatar.png");
			this.jdbcTemplate.studentAdd(m);

			fail = 0;
		}

		rttr.addFlashAttribute("fail", fail);

		return "redirect:/manager/student";
	}

	// 5.수강생 삭제
	@RequestMapping("/student/studentDelete")
	public String studentDelete(String student_id, RedirectAttributes rttr) {

		// 사진 파일 삭제 (기본 파일(avatar.png)인 경우는 제외)
		String uploadPath = context.getRealPath("") + "resources/uploads/pictures" + File.separator;
		String fileName = this.jdbcTemplate.getProfileImgName(student_id);
		if (!fileName.equals("avatar.png")) {
			File file = new File(uploadPath + "/" + fileName);
			file.delete();
		}

		// DB 삭제
		int result = 0;
		try {
			result = this.jdbcTemplate.studentDelete(student_id);

		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/student";
	}

	// 5. 수강생 과정 등록
	@RequestMapping("/student/studentOcAdd")
	public String courseAdd(String oc_id, String student_id, RedirectAttributes rttr) {
		int result = 0;
		try {
			result = this.jdbcTemplate.studentOcAdd(oc_id, student_id);
		} catch (DataAccessException e) {
			e.getMessage();
		}

		rttr.addFlashAttribute("success", result);

		return "redirect:/manager/student";
	}

	// 5. 수강생 과정 등록 - 수강 이력 Ajax 처리
	@RequestMapping("/getCourseHistory")
	public @ResponseBody List<Manager> getCourseHistory(String student_id) {
		List<Manager> ocSearchList = this.jdbcTemplate.ocSearch(student_id);

		return ocSearchList;
	}

	// 6.성적 조회/과정별 성적 조회
	@RequestMapping("/score/bycourse")
	public String scoreBycourse(Model model) {

		List<Manager> courseList = this.jdbcTemplate.ocList61();

		model.addAttribute("courseList", courseList);

		return "/manager/score-bycourse";
	}

	// 6.성적 조회/과정별 성적 조회/과목별 성적 조회
	@RequestMapping("/score/bycourse/subjectlist")
	public String scoreBycourseSubjectlist(Model model, String oc_id) {

		List<Manager> subjectList = this.jdbcTemplate.osInfoSearch(oc_id);

		Manager ocInfo = this.jdbcTemplate.ocInfo(oc_id);

		model.addAttribute("ocInfo", ocInfo);
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("oc_id", oc_id);

		return "/manager/score-bycourse-subjectlist";
	}

	// 6.성적 조회/과정별 성적 조회/과목별 성적 조회/수강생 성적 조회
	@RequestMapping("/score/bycourse/subjectlist/studentlist")
	public String scoreBycourseSubjectlistStudentlist(Model model, String oc_id, String os_id, String os_name,
			int attendance_percentage, int writing_percentage, int practice_percentage) {

		Manager osInfo = this.jdbcTemplate.osInfo(os_id);

		List<Manager> studentScoreList = this.jdbcTemplate.studentScoreList(os_id);

		model.addAttribute("osInfo", osInfo);
		model.addAttribute("os_name", os_name);
		model.addAttribute("os_id", os_id);
		model.addAttribute("studentScoreList", studentScoreList);
		model.addAttribute("attendance_percentage", attendance_percentage);
		model.addAttribute("writing_percentage", writing_percentage);
		model.addAttribute("practice_percentage", practice_percentage);

		return "/manager/score-bycourse-subjectlist-studentlist";
	}

	// 6.성적 조회/수강생별 성적 조회
	@RequestMapping("/score/bystudent")
	public String scoreBystudent(Model model, String key, String value, String pageNum) {

		if (key == null) {
			key = "ALL";
			value = "";
		}

		int totalCount = this.jdbcTemplate.studentCount();

		// 페이징 처리
		if (pageNum == null) {
			pageNum = "1";
		}

		int start_row = Integer.parseInt(pageNum);
		if (start_row > 1) {
			start_row = ((Integer.parseInt(pageNum) - 1) * 10) + 1;
		}

		int limit_count = 9;
		int end_row = start_row + limit_count;

		int previous = Integer.parseInt(pageNum) - 1;
		int next = Integer.parseInt(pageNum) + 1;

		List<Manager> studentList = this.jdbcTemplate.studentInfoSearch(key, value, start_row, end_row);

		int resultSize = studentList.size();

		model.addAttribute("key", key);
		model.addAttribute("value", value);
		model.addAttribute("studentList", studentList);
		model.addAttribute("resultSize", resultSize);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);
		model.addAttribute("limit_count", limit_count+1);

		return "/manager/score-bystudent";
	}

	// 6.성적 조회/수강생별 성적 조회/수강 과정 목록
	@RequestMapping("/score/bystudent/courselist")
	public String scoreBystudentCourselist(Model model, String student_id) {

		List<Manager> ocList = this.jdbcTemplate.ocSearch(student_id);
		Manager studentInfo = this.jdbcTemplate.studentInfo(student_id);

		model.addAttribute("student_id", student_id);
		model.addAttribute("ocList", ocList);
		model.addAttribute("studentInfo", studentInfo);

		return "/manager/score-bystudent-courselist";
	}

	// 6.성적 조회/수강생별 성적 조회/수강 과정 목록/과정 성적 조회
	@RequestMapping("/score/bystudent/courselist/scorelist")
	public String scoreBystudentCourselistScorelist(Model model, String student_id, String oc_id, String oc_title,
			String oc_date) {

		Manager studentInfo = this.jdbcTemplate.studentInfo(student_id);
		List<Manager> scoreList = this.jdbcTemplate.stu_scoreSearch(student_id, oc_id);

		model.addAttribute("studentInfo", studentInfo);
		model.addAttribute("oc_id", oc_id);
		model.addAttribute("oc_title", oc_title);
		model.addAttribute("oc_date", oc_date);
		model.addAttribute("scoreList", scoreList);

		return "/manager/score-bystudent-courselist-scorelist";
	}
	
	// 4.개설 과정 및 과목 관리/개설 과정 목록/수강생 목록/엑셀
	@RequestMapping("/course/studentlist/excel")
	public String courseStudentExcel(HttpServletResponse response, String oc_id) throws IOException {
		
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Course_Student_List");
		
		// Create row object
		XSSFRow row;
		
		// This data needs to be written (Object[]) 맵 자료형만 가능
		Manager ocInfo = this.jdbcTemplate.ocInfo(oc_id);
		Map<String, Object[]> empinfo = new HashMap<String, Object[]>();
		empinfo.put("1", new Object[] { ocInfo.getOc_title() + "(" + ocInfo.getOc_start_date().substring(0, 10) + " ~ " + ocInfo.getOc_end_date().substring(0, 10) + ") 수강생 목록" });
		empinfo.put("2", new Object[] { "수강생 ID", "수강생 이름", "주민번호 뒷자리" , "전화번호", "최초등록일", "수료 여부", "수료예정(중도탈락)날짜"});
		List<Manager> list = this.jdbcTemplate.oc_studentSearch(oc_id);
		int count = list.size();
		for (int a=0; a<count; a++) {
			Manager m = list.get(a);
			empinfo.put(String.valueOf(a+3), new Object[] { m.getStudent_id(), m.getName_(), m.getSsn(), m.getPhone(), m.getInitial_reg_date(), m.getLearning_status(), m.getCourse_end_date().substring(0, 10) });
		}

		// Iterate over data and write to sheet
		Set<String> keyid = empinfo.keySet();
		int rowid = 0;
		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = empinfo.get(key);
			int cellid = 0;
			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
		
		response.setHeader("Content-disposition","attachment;filename=" + ocInfo.getOc_title().substring(0, ocInfo.getOc_title().indexOf(" ")) + "_course" + "(" + ocInfo.getOc_start_date().substring(0, 10) + "_" + ocInfo.getOc_end_date().substring(0, 10) + ").xlsx");
	    response.setHeader("Content-Type", "application/vnd.ms-excel; charset=MS949");
	    response.setHeader("Content-Description", "JSP Generated Data"); 
	    response.setHeader("Content-Transfer-Encoding", "binary;"); 
	    response.setHeader("Pragma", "no-cache;"); 
	    response.setHeader("Expires", "-1;");
	    
	    ServletOutputStream out = response.getOutputStream();
	    workbook.write(out);
	    out.close();
	    
	    workbook.close();
		
		return "redirect:/course/studentlist";
	}
	
	// 6.성적 조회/과정별 성적 조회/과목별 성적 조회/수강생 성적 조회/엑셀
	@RequestMapping("/score/bycourse/subjectlist/studentlist/excel")
	public String subjectStudentExcel(HttpServletResponse response, String oc_id, String os_id) throws IOException {
		
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Subject_Student_List");
		
		// Create row object
		XSSFRow row;
		
		// This data needs to be written (Object[]) 맵 자료형만 가능		
		Manager osInfo = this.jdbcTemplate.osInfo(os_id);
		
		Map<String, Object[]> empinfo = new HashMap<String, Object[]>();
		empinfo.put("1", new Object[] { osInfo.getOc_title() + "의 " + osInfo.getOs_name() + "(" + osInfo.getOs_start_date().substring(0, 10) + " ~ " + osInfo.getOs_end_date().substring(0, 10) + ")" + "수강생 성적 조회" });
		empinfo.put("2", new Object[] { "수강생 ID", "수강생 이름", "전화번호", "출결 점수", "필기 점수", "실기 점수", "총점" });
		List<Manager> list = this.jdbcTemplate.studentScoreList(os_id);
		int count = list.size();
		for (int a=0; a<count; a++) {
			Manager m = list.get(a);
			empinfo.put(String.valueOf(a+3), new Object[] { m.getStudent_id(), m.getName_(), m.getPhone(), String.valueOf(m.getAttendance_score()), String.valueOf(m.getWriting_score()), String.valueOf(m.getPractice_score()), String.valueOf(m.getTotal_score()) });
		}
		
		// Iterate over data and write to sheet
		Set<String> keyid = empinfo.keySet();
		int rowid = 0;
		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = empinfo.get(key);
			int cellid = 0;
			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
		
		response.setHeader("Content-disposition","attachment;filename=" + osInfo.getOs_name() + "(" + osInfo.getOs_start_date().substring(0, 10) + "_" + osInfo.getOs_end_date().substring(0, 10) + ").xlsx");
	    response.setHeader("Content-Type", "application/vnd.ms-excel; charset=MS949");
	    response.setHeader("Content-Description", "JSP Generated Data"); 
	    response.setHeader("Content-Transfer-Encoding", "binary;"); 
	    response.setHeader("Pragma", "no-cache;"); 
	    response.setHeader("Expires", "-1;");
	    
	    ServletOutputStream out = response.getOutputStream();
	    workbook.write(out);
	    out.close();
	    
	    workbook.close();
		
		return "redirect:/score/bycourse/subjectlist/studentlist";
	}
	
	// Interpark 책 정보 읽어오기
	@RequestMapping("/interparkapi")
	public @ResponseBody String interparkapi(String isbn) throws ParserConfigurationException, IOException, SAXException {
		
		InterparkAPI api = new InterparkAPI();
		String xmldoc = api.bookXml(isbn); 

		return xmldoc;
	}
	
}
