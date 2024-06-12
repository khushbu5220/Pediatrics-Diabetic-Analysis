package com.aiims.pds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiims.pds.repository.UserRepository;
import com.aiims.pds.services.UserServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/user-action/")
public class UserController 
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/createBaseLine")
	public ResponseEntity<?> createBaseLine()
	{
		
		return null;
	}
}