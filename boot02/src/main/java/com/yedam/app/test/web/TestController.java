package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	//@RequestMapping(path="/test", method=RequestMethod.GET)
	@GetMapping("test")
	@ResponseBody
	public String urlGetTest(String keyword) {
		return "Server Response : SELECT - " + keyword;
	}
	
	//@RequestMapping(path="/test", method=RequestMethod.POST)
	@PostMapping("test")
	@ResponseBody
	public String urlPostTest(String keyword) {
		return "Server Response : INSERT - " + keyword;
	}
}
