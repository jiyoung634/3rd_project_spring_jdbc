package com.test.controller;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.*;
import org.xml.sax.*;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.test.domain.*;
import com.test.util.InterparkAPI;
import com.test.dao.*;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentJDBCTemplate jdbcTemplate;
	
	private String student_id;

	// 1.Main
	@RequestMapping("/main")
	public String main(Model model, HttpSession session) {
		
		Login l = (Login) session.getAttribute("studentLoginInfo");
		this.student_id = l.getLogin_id();
		
		// 수강생 정보
		List<Student> list = this.jdbcTemplate.studentList(student_id);
		String name_ = list.get(0).getName_();
		String phone = list.get(0).getPhone();
		String initial_reg_date = list.get(0).getInitial_reg_date();
		int registration_count = list.get(0).getRegistration_count();

		// 수강 이력
		List<Student> courseList = this.jdbcTemplate.studentCourseList(student_id);
		
		model.addAttribute("name_", name_);
		model.addAttribute("phone", phone);
		model.addAttribute("initial_reg_date", initial_reg_date);
		model.addAttribute("registration_count", registration_count);

		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());

		return "/student/main";
	}
	
	// 1.Main - 비밀번호 변경
	//@RequestMapping("/updatepw")
	//public String updatepw(String )

	// 2.성적 조회
	@RequestMapping("/score")
	public String score(Model model) {

		// 성적 조회
		List<Student> courseList = this.jdbcTemplate.studentCourseList(student_id);

		//model.addAttribute("name_", name_);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());

		return "/student/score";
	}

	// 2.성적 조회/과정별 성적 조회
	@RequestMapping("/score/bycourse")
	public String scoreBycourse(Model model, String oc_id) {

		StringBuilder chartData = new StringBuilder();

		// XX 과정( ~ ) 성적 조회
		Student ocInfo = this.jdbcTemplate.ocInfo(oc_id);
		List<Student> scoreList = this.jdbcTemplate.scoreList(student_id, oc_id);

		// model.addAttribute("name_", name_);
		model.addAttribute("ocInfo", ocInfo);
		model.addAttribute("scoreList", scoreList);

		// Chart 그리기 위해 JSON 문자열 형태로 model에 추가
		/*{
		   "Student": [{
				"os_name":"뭐뭐",
				"attendance_score":"15", 
				"writing_score":"35", 
				"practice_score":"30", 
				"total_score":"100"
				},
				{
				"os_name":"뭐뭐",
				"attendance_score":"15", 
				"writing_score":"35", 
				"practice_score":"30", 
				"total_score":"100"
				}
			]
		}
		*/
		chartData.append("{\\\"Student\\\":[");
		for (int i = 0; i < scoreList.size(); ++i) {
			Student s = scoreList.get(i);
			chartData.append(String.format(
					"{\\\"os_name\\\":\\\"%s\\\",\\\"attendance_score\\\":\\\"%s\\\",\\\"writing_score\\\":\\\"%s\\\",\\\"practice_score\\\":\\\"%s\\\",\\\"total_score\\\":\\\"%s\\\"}",
					s.getOs_name(), s.getAttendance_score(), s.getWriting_score(), s.getPractice_score(),
					s.getTotal_score()));
			if (i < scoreList.size() - 1) {
				chartData.append(",");
			}
		}
		chartData.append("]}");

		model.addAttribute("chartData", chartData.toString());
		model.addAttribute("scoreListSize", scoreList.size());
		return "/student/score-bycourse";
	}
	
	// Interpark 책 정보 읽어오기
	@RequestMapping("/interparkapi")
	public @ResponseBody String interparkapi(String isbn) throws ParserConfigurationException, IOException, SAXException {
		
		InterparkAPI api = new InterparkAPI();
		String xmldoc = api.bookXml(isbn); 

		return xmldoc;
	}
	
}