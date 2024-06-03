package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller	// => 사용자의 요청(Endpoint)에 대한처리
			// : url + method <=> service => view
public class EmpController {
	// 해당 컨트롤러에서 제공하는 서비스들 추가
	@Autowired
	EmpService empService;
	
	// GET : 조회, 빈페이지
	// POST : 데이터 조작(등록, 수정, 삭제)
	
	// 전체조회 : GET
	@GetMapping("empList")
	public String empList(Model model) { // Model = Request + Response
		// 1) 해당 기능 수행 -> Service
		List<EmpVO> list = empService.empList();
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("empList", list);
		return "emp/list"; // 3) 데이터를 출력할 페이지 결정
		// classpath:/templates/	emp/list    .html
		// prefix					 return     subfix
	}
	
	// 단건조회 : GET
	@GetMapping("empInfo") // 커맨드 객체 => QuerryString(key value%key=value...)
	public String empInfo(EmpVO empVO, Model model) {
		// 1) 해당 기능 수행 -> Service
		EmpVO findVO = empService.empInfo(empVO);
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("empInfo", findVO);
		return "emp/info"; //3) 데이터를 출력할 페이지 결정
		// "classpath:/templates/" + "emp/info" + ".html"
		// => classpath:/templates/emp/info.html
		// classpath: => src/main/resources
	}
	
	// 등록 - 페이지 : GET
	@GetMapping("empInsert")
	public String empInsertForm(Model model) {
		model.addAttribute("empVO", new EmpVO());
		return "emp/insert";
	}
	
	// 등록 - 처리 => form태그를 통한 submit
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		String url = null;
		if(eid > -1) {
			//정상적으로 등록된 경우
			url = "redirect:empInfo?employeeId="+eid;
		}else {
			//정상적으로 등록되지 않은 경우
			url = "redirect:empList";
		}
		return url;
	}
	// 수정 - 페이지 : GET
	@GetMapping("empUpdate")
	public String empUpdateForm(Integer employeeId, Model model) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO);
		
		return "emp/update";
	}
	// 수정 - 처리 : AJAX => QueryString
	//@PostMapping("empUpdate")
	@ResponseBody // = AJAX
	public Map<String, Object> empUpdateAJAXQueryString(EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	// 수정 - 처리 : AJAX => JSON
	@PostMapping("empUpdate")
	@ResponseBody // = AJAX
	public Map<String, Object> empUpdateAJAXJSON(@RequestBody EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	//삭제 -- 처리
	@GetMapping("empDelete")
	public String empDelete(EmpVO empVO) {
		empService.empDelete(empVO);
		return "redirect:empList";
	}
}
