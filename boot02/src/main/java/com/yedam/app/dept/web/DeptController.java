package com.yedam.app.dept.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Controller // => 사용자의 요청(Endpoint)에 대한처리
			// : url + method <=> service => view
public class DeptController {
	// 해당 컨트롤러에서 제공하는 서비스들 추가
	@Autowired
	DeptService deptService;

	// GET : 조회, 빈페이지
	// POST : 데이터 조작(등록, 수정, 삭제)

	// 전체조회 : GET
	@GetMapping("deptList")
	public String deptList(Model model) { // Model = Request + Response
		// 1) 해당 기능 수행 -> Service
		List<DeptVO> list = deptService.deptList();
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("deptList", list);
		return "dept/list"; // 3) 데이터를 출력할 페이지 결정
		// classpath:/templates/dept/list.html
		// prefix return subfix
	}

	// 단건조회 : GET
	@GetMapping("deptInfo")
	public String deptInfo(DeptVO deptVO, Model model) {
		// 1) 해당 기능 수행 -> Service
		DeptVO findVO = deptService.deptInfo(deptVO);
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("deptInfo", findVO);
		return "dept/info"; // 3) 데이터를 출력할 페이지 결정
	}

	// 등록 - 페이지 : GET
	@GetMapping("deptInsert")
	public String deptInsertForm(Model model) {
		model.addAttribute("deptVO", new DeptVO());
		return "dept/insert";
	}

	// 등록 - 처리 => form태그를 통한 submit
	@PostMapping("deptInsert")
	public String deptInsertProcess(DeptVO deptVO) {
		int did = deptService.deptInsert(deptVO);
		String url = null;
		if (did > -1) {
			// 정상적으로 등록된 경우
			url = "redirect:deptInfo?departmentId=" + did;
		} else {
			// 정상적으로 등록되지 않은 경우
			url = "redirect:deptList";
		}
		return url;
	}

	// 수정 - 페이지 : GET
	@GetMapping("deptUpdate")
	public String deptUpdateForm(Integer departmentId, Model model) {
		DeptVO deptVO = new DeptVO();
		deptVO.setDepartmentId(departmentId);

		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("deptInfo", findVO);

		return "dept/update";
	}

	// 수정 - 처리 : AJAX => QueryString
	@PostMapping("deptUpdate")
	@ResponseBody // = AJAX
	public Map<String, Object> deptUpdateAJAXQueryString(DeptVO deptVO) {
		return deptService.deptUpdate(deptVO);
	}

	// 수정 - 처리 : AJAX => JSON
	// @PostMapping("deptUpdate")
	@ResponseBody // = AJAX
	public Map<String, Object> deptUpdateAJAXJSON(@RequestBody DeptVO deptVO) {
		return deptService.deptUpdate(deptVO);
	}

	// 삭제 -- 처리
	@GetMapping("deptDelete")
	public String deptDelete(DeptVO deptVO) {
		deptService.deptDelete(deptVO);
		return "redirect:deptList";
	}
}
