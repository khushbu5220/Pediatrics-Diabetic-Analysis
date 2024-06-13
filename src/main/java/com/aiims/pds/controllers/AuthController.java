package com.aiims.pds.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiims.pds.exceptions.ApiException;
import com.aiims.pds.exceptions.ResourceNotFoundException;
import com.aiims.pds.payloads.JwtAuthRequest;
import com.aiims.pds.payloads.JwtAuthResponse;
import com.aiims.pds.payloads.UserDto;
import com.aiims.pds.repository.UserRepository;
import com.aiims.pds.security.JwtTokenHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
//	@Autowired
//	private SessionRepo sessionRepo;
//	@Autowired
//	private AdminServices adminServices;
	
//	public static Long sessId = null;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(HttpServletRequest req, HttpServletResponse res,@RequestBody JwtAuthRequest request) throws Exception
	{
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails  = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		UserDto userData = this.modelMapper.map(this.userRepository.getRoleByContactNo(request.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("Username", "Employee ID", request.getUsername())), UserDto.class);
//		sessionFunction(req,res,userData.getEmployeeId());
		JwtAuthResponse response = new JwtAuthResponse();
		response.setRole(userData.getRole());
		response.setToken(token);
		response.setUsername(request.getUsername());
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	
	private void authenticate(String username,String password) throws Exception 
	{	
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
		try
		{
			this.authenticationManager.authenticate(authenticationToken);
		}					
		catch (BadCredentialsException e) 
		{
			throw new ApiException("Invalid username or password !!");
		}		
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
//	//register new user 
//	@PostMapping("/forgetPassword")
//	public ResponseEntity<Object> forgetUserPassword(@Valid @RequestBody EmployeeRequest user)
//	{
//		try 
//		{
//			UserResponse forgetUserPassword = this.adminServices.forgetUserPassword(user);
//			return new ResponseEntity<>(forgetUserPassword,HttpStatus.OK);				
//		} 
//		catch (Exception e) 
//		{
//			return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	@PostMapping("/verifyOtp")
//	public ResponseEntity<Object> verifyOtp(@Valid @RequestBody OtpRequest user){
//		try 
//		{
//			UserResponse verifyOtp = this.adminServices.VerifyOtp(user);
//			return new ResponseEntity<>(verifyOtp,HttpStatus.OK);				
//		} 
//		catch (Exception e) 
//		{
//			return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	@PostMapping("/updatePassword")
//	public ResponseEntity<Object> updatePassword(@Valid @RequestBody PassKeyRequest user)
//	{
//		try 
//		{
//			UserResponse updatePassword = this.adminServices.updatePassword(user);
//			return new ResponseEntity<>(updatePassword,HttpStatus.OK);				
//		} 
//		catch (Exception e) 
//		{
//			return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	@GetMapping("/getUser/{empId}")
//	public UserDto getUser(@PathVariable("empId") String empId)
//	{
//		return this.adminServices.getUser(empId);
//	}
}
