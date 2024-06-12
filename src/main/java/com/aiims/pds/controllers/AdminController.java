package com.aiims.pds.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiims.pds.exceptions.ResourceNotFoundException;
import com.aiims.pds.modals.User;
import com.aiims.pds.payloads.UserDto;
import com.aiims.pds.repository.UserRepository;
import com.aiims.pds.services.AdminServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin-action/")
public class AdminController 
{
	@Autowired
	private AdminServices adminServices;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/superadminregister")
	public ResponseEntity<UserDto> registerSuperAdminUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto registeredUser = this.adminServices.registerSuperAdminUser(userDto);
		return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(Principal principal, @Valid @RequestBody UserDto userDto)
	{
		String contactNo = principal.getName();
		User user = this.userRepository.findByContactNo(contactNo).orElseThrow(() -> new ResourceNotFoundException("Username", "contactNo", contactNo));
		UserDto registeredUser = this.adminServices.registerNewUser(user, userDto);
		return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/dashboard/getUsers")
	public List<UserDto> getUsers()
	{
		return this.adminServices.getUsers();
	}
	
	//update/'change password
//	@PostMapping("/changePassword")
//	public ResponseEntity<Object> changePassword(@Valid @RequestBody ChangePassKey password,Principal principal){
//		try 
//		{
//			String changePassword = this.adminServices.changeUserPassword(password,principal);
//			return new ResponseEntity<>(changePassword,HttpStatus.OK);				
//		} 
//		catch (Exception e) 
//		{
//			return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
