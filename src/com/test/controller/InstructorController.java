package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

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
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	private InstructorJDBCTemplate jdbcTemplate;
	
	@Autowired
	private ServletContext context;
		
	private String instructor_id; 
	
	// 1.Main
	@RequestMapping("/main") 
	public String main(Model model, HttpSession session) {

		Login l = (Login) session.getAttribute("instructorLoginInfo");
		this.instructor_id = l.getLogin_id();
		
		List<Instructor> list = this.jdbcTemplate.instructorList(instructor_id);
		String name_ = list.get(0).getName_();
		String phone = list.get(0).getPhone();
		String initial_reg_date = list.get(0).getInitial_reg_date();
		String available_subjects = list.get(0).getAvailable_subjects();
		
		model.addAttribute("name_", name_);
		model.addAttribute("phone", phone);
		model.addAttribute("initial_reg_date", initial_reg_date);
		model.addAttribute("available_subjects", available_subjects);
		return "/instructor/main"; 
	}
	
	// 2.강의스케쥴 조회
	@RequestMapping("/schedule") 
	public String schedule(Model model) {
		
		List<Instructor> courseScheduleList = this.jdbcTemplate.courseScheduleList(instructor_id);

		model.addAttribute("courseScheduleList", courseScheduleList);
		
		return "/instructor/schedule"; 
	}
	
	// 2.강의스케쥴 조회/수강생 목록
	@RequestMapping("/schedule/studentlist") 
	public String scheduleStudentlist(Model model, String os_id) {

		List<Instructor> studentSearch = this.jdbcTemplate.studentSearch(instructor_id, os_id);
		Instructor ocosInfo = this.jdbcTemplate.ocosInfo(os_id);

		model.addAttribute("studentSearch", studentSearch);
		model.addAttribute("studentCount", studentSearch.size());
		model.addAttribute("ocosInfo", ocosInfo);
		
		return "/instructor/schedule-studentlist"; 
	}
	
	// 3.배점 및 시험 관리/배점 관리
	@RequestMapping("/test/scoreper") 
	public String testScoreper(Model model) {

		List<Instructor> percentage_addible_osList = this.jdbcTemplate.percentage_addible_osList(instructor_id);

		model.addAttribute("percentage_addible_osList", percentage_addible_osList);
		model.addAttribute("percentage_addible_os_count",percentage_addible_osList.size());
		
		return "/instructor/test-scoreper"; 
	}
	
	// 3.배점 및 시험 관리/배점 관리/배점 입력
	@RequestMapping("/percentageInsert")
	public String percentageInsert(Instructor i, RedirectAttributes rttr) throws DataAccessException {

		int result = 0;
		try {
			result = this.jdbcTemplate.percentageAdd(i);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
			
		rttr.addFlashAttribute("success", result);
			
		return "redirect:/instructor/test/scoreper";
	}
		
	// 3.배점 및 시험 관리/배점 관리/배점 삭제
	@RequestMapping("/percentageDelete")
	public String percentageDelete(String os_id, RedirectAttributes rttr) throws DataAccessException {

		int result = 0;
		try {
			result = this.jdbcTemplate.percentageDelete(os_id, instructor_id);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
				
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/instructor/test/scoreper";
	}
				
	
	// 3.배점 및 시험 관리/시험 날짜 및 파일 관리
	@RequestMapping("/test/info") 
	public String testInfo(Model model) {

		List<Instructor> exam_osList = this.jdbcTemplate.exam_osList(instructor_id);

		model.addAttribute("exam_osList", exam_osList);
		model.addAttribute("exam_osList_count", exam_osList.size());

		return "/instructor/test-info"; 
	}
		
	// 3.배점 및 시험 관리/시험 날짜 및 파일 관리/시험 날짜 및 파일 업로드
	@RequestMapping("/fileUpload")
	public String fileUpload(FileModel file, Model model, RedirectAttributes rttr) throws IOException {
		
		MultipartFile multipartFile = file.getFile();
		String uploadPath = context.getRealPath("") + "resources/uploads/files" + File.separator;
		System.out.println(uploadPath);
		if (multipartFile.getOriginalFilename() == null) {
			System.out.println("파일없음.");
		}

		String fileName = multipartFile.getOriginalFilename();
		String contentType = multipartFile.getContentType();
		
		int fail = 1;
		
		if (contentType.equals("application/x-zip-compressed")) {
		
			FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + fileName));
			
			// DB에 저장
			Instructor i = new Instructor();
			i.setOs_id(file.getOs_id());
			i.setExam_date(file.getExam_date());
			i.setExam_file(fileName);
		
			this.jdbcTemplate.examAdd(i);
		
			fail = 0;
		
		}
		
		rttr.addFlashAttribute("fail", fail);
		
		return "redirect:/instructor/test/info";
	}
	
	// 3.배점 및 시험 관리/시험 날짜 및 파일 관리/시험 날짜 및 파일 삭제
	@RequestMapping("/infoDelete")
	public String infoDelete(Model model, String os_id, RedirectAttributes rttr) throws IOException {
		
		this.jdbcTemplate.examInfoDelete(os_id, instructor_id);
		
		rttr.addFlashAttribute("success", 1);
		
		return "redirect:/instructor/test/info";
	}
		
	// 4.성적 관리
	@RequestMapping("/score") 
	public String score(Model model) {
		
		List<Instructor> score_osList = this.jdbcTemplate.score_osList(instructor_id);
			
		//model.addAttribute("name_", name_);
		model.addAttribute("score_osList", score_osList);
		model.addAttribute("score_osList_count", score_osList.size());
		
		return "/instructor/score"; 
	}
		
	// 4.성적 관리/수강생 성적 확인
	@RequestMapping("/score/studentlist") 
	public String scoreStudentlist(Model model, String os_id) {

		List<Instructor> scoreSearch = this.jdbcTemplate.scoreSearch(instructor_id, os_id);
		Instructor score_percentage = this.jdbcTemplate.scorePercentage(os_id);
		Instructor ocosInfo = this.jdbcTemplate.ocosInfo(os_id);

		model.addAttribute("scoreSearch", scoreSearch);
		model.addAttribute("scoreSearchCount", scoreSearch.size());
		model.addAttribute("score_percentage", score_percentage);
		model.addAttribute("ocosInfo", ocosInfo);
		model.addAttribute("os_id", os_id);

		return "/instructor/score-studentlist"; 
	}
	
	// 4.성적 관리/수강생 성적 확인/수강생 성적 입력
	@RequestMapping("/score/studentlist/scoreInsert")
	public String scoreInsert(int attendance_score, int writing_score, int practice_score, String student_id,  String os_id, RedirectAttributes rttr) {

		int result = 0;
		
		Instructor i = new Instructor();
		i.setAttendance_score(attendance_score);
		i.setWriting_score(writing_score);
		i.setPractice_score(practice_score);
		i.setStudent_id(student_id);
		i.setOs_id(os_id);
	
		result = this.jdbcTemplate.scoreAdd(i);
		
		rttr.addFlashAttribute("success", result);

		return "redirect:/instructor/score/studentlist?os_id=" + os_id;
	}
	
	// 4.성적 관리/수강생 성적 확인/수강생 성적 삭제
	@RequestMapping("/score/studentlist/scoreDelete")
	public String scoreDelete(String student_id, String os_id, RedirectAttributes rttr) {
	
		int result = 0;
		
		result = this.jdbcTemplate.scoreDelete(this.instructor_id, student_id, os_id);
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/instructor/score/studentlist?os_id=" + os_id;
	}
	
	// Interpark 책 정보 읽어오기
	@RequestMapping("/interparkapi")
	public @ResponseBody String interparkapi(String isbn) throws ParserConfigurationException, IOException, SAXException {
		
		InterparkAPI api = new InterparkAPI();
		String xmldoc = api.bookXml(isbn); 

		return xmldoc;
	}
	
}
