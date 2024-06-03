package com.yedam.app.test.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class ParamController {
	// QureyString(질의문자열) : key=value&key=value&...
	// method는 상관없음
	// Content-type : application/x-www-form-urlencoded
	
	// QueryString => 커맨드객체
	@RequestMapping(path="comobj",
					method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "path : /comobj\n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
			
	// QueryString => @RequestParam : 기본타입, 단일값
		@RequestMapping(path="reqparam",
						method= {RequestMethod.GET, RequestMethod.POST})
		@ResponseBody
		public String requestParam(@RequestParam 
//				(name="employeeId", defaultValue="1",required=true)
												Integer employeeId, //필수
												 String lastName, //생략가능
								   @RequestParam(name="message", defaultValue="No message",
								   required = true)String msg) {
			String result = "";
			result += "path : /reqparam\n";
			result += "\t employee_id : " + employeeId;
			result += "\t last_name : " + lastName;
			result += "\t message : " + msg;
			return result;
		}
		
		// @PathVariable : 경로에 값을 포함하는 방식, 단일 값
		// Method는 상관없음
		// Content-type도 상관없음
		@RequestMapping("path/{id}")
		@ResponseBody
		public String PathVariable(@PathVariable String id) {
			String result = "";
			result += "path : /path/{id} \n";
			result += "\t id : " + id;
			return result;			
		}
		
		// @RequestBody : JSON 포맷, 배열 or 객체
		// Method : POST, PUT
		// Content-type : application/json
		@PostMapping("resbody")
		@ResponseBody
		public String requestBody(@RequestBody EmpVO empVO) {
			String result = "";
			result += "path : /resbody";
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			return result;
		}
		
		@PostMapping("resbodyList")
		@ResponseBody
		public String requestBodyList(@RequestBody List<EmpVO> list) {
			String result = "path : /resbodyList \n";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			for(EmpVO empVO : list) {				
			result += "\n\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\t hire_date : " + format.format(empVO.getHireDate());
			}
			return result;
		}

		
		
		
}
