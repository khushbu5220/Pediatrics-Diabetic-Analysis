package com.aiims.pds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@RequestMapping("/test")
	public String test() {
		return "user/NewFile";
	}
	
	@RequestMapping("/followUp")
	public String followUp() {
		return "user/followUpSheet";
	}
}
