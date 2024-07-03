package com.aiims.pds.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiims.pds.exceptions.ApiException;
import com.aiims.pds.exceptions.ResourceNotFoundException;
import com.aiims.pds.payloads.EmployeeRequest;
import com.aiims.pds.payloads.JwtAuthRequest;
import com.aiims.pds.payloads.JwtAuthResponse;
import com.aiims.pds.payloads.OtpRequest;
import com.aiims.pds.payloads.PassKeyRequest;
import com.aiims.pds.payloads.UserDto;
import com.aiims.pds.payloads.UserResponse;
import com.aiims.pds.payloads.UserResponse;
import com.aiims.pds.repository.UserRepository;
import com.aiims.pds.security.JwtTokenService;
import com.aiims.pds.services.AdminServices;
import com.aiims.pds.security.JwtTokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private JwtTokenService jwtTokenService;
	@Autowired
	private AdminServices adminServices;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public JwtAuthResponse createToken(HttpServletRequest req, HttpServletResponse res,@RequestBody JwtAuthRequest request) throws Exception
	{
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
				);
		var user = userRepository.findByContactNo(request.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("Username", "ContactNo.", request.getUsername()));
		
		var jwtToken = jwtTokenService.generateToken(user);
		return JwtAuthResponse.builder()
				.token(jwtToken)
				.role(user.getRole())
				.username(user.getUsername())
				.build();  
	}
	
//	public void sessionFunction(HttpServletRequest request, HttpServletResponse response, String username) 
//	{
//		Session session = new Session();
//		User user = userRepo.findByEmployeeId(username).orElseThrow(() -> new ResourceNotFoundException("User", "EmployeeId", username));
//		if(user != null)
//		{
//			session.setUserId(user.getUserId());
//		}
//		session.setIpAddress(request.getRemoteAddr());
//		session.setLoginDt(new Date());
//		session.setWebBrowser(request.getHeader("user-agent"));
//		Session s = sessionRepo.save(session);
//		sessId = s.getSessionId();
//		
//	}
//	
	//register new user 
	@PostMapping("/forgetPassword")
	public ResponseEntity<Object> forgetUserPassword(@Valid @RequestBody EmployeeRequest user)
	{
		try 
		{
			UserResponse forgetUserPassword = this.adminServices.forgetUserPassword(user);
			return new ResponseEntity<>(forgetUserPassword,HttpStatus.OK);				
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/verifyOtp")
	public ResponseEntity<Object> verifyOtp(@Valid @RequestBody OtpRequest user){
		try 
		{
			UserResponse verifyOtp = this.adminServices.VerifyOtp(user);
			return new ResponseEntity<>(verifyOtp,HttpStatus.OK);				
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity<Object> updatePassword(@Valid @RequestBody PassKeyRequest user)
	{
		try 
		{
			UserResponse updatePassword = this.adminServices.updatePassword(user);
			return new ResponseEntity<>(updatePassword,HttpStatus.OK);				
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@GetMapping("/getUser/{empId}")
//	public UserDto getUser(@PathVariable("empId") String empId)
//	{
//		return this.adminServices.getUser(empId);
//	}
}
