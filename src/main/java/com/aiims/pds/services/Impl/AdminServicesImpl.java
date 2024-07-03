package com.aiims.pds.services.Impl;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aiims.pds.config.AppConstants;
import com.aiims.pds.exceptions.ResourceNotFoundException;
import com.aiims.pds.modals.User;
import com.aiims.pds.payloads.ChangePassKey;
import com.aiims.pds.payloads.EmployeeRequest;
import com.aiims.pds.payloads.JwtAuthResponse;
import com.aiims.pds.payloads.OtpRequest;
import com.aiims.pds.payloads.PassKeyRequest;
import com.aiims.pds.payloads.UserDto;
import com.aiims.pds.payloads.UserResponse;
import com.aiims.pds.repository.UserRepository;
import com.aiims.pds.security.JwtTokenService;
import com.aiims.pds.services.AdminServices;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class AdminServicesImpl implements AdminServices 
{
	@Autowired
	private UserRepository userRepository;
	@Autowired(required=true)
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
//	@Autowired
	private JwtTokenService jwtTokenService;

	@Override
	public UserDto registerSuperAdminUser(@Valid UserDto userDto) 
	{
		var user = User.builder()
				.contactNo(userDto.getContactNo())
				.email(userDto.getEmail())
				.enabled(true)
				.fullName(userDto.getFullName())
				.otp(0)
				.otpSentOn(null)
				.password(passwordEncoder.encode(userDto.getPassword()))
				.registeredOn(new Date())
				.role(userDto.getRole().toUpperCase())
				.status(AppConstants.ACTIVE_USER_STATUS)
				.userAddedBy(null)
				.build();	
		
		return this.modelMapper.map(this.userRepository.save(user), UserDto.class);  
	}
	
	@Override
	public UserDto registerNewUser(User addByUser,UserDto userDto) 
	{
		var user = User.builder()
				.contactNo(userDto.getContactNo())
				.email(userDto.getEmail())
				.enabled(true)
				.fullName(userDto.getFullName())
				.otp(0)
				.otpSentOn(null)
				.password(passwordEncoder.encode(userDto.getPassword()))
				.registeredOn(new Date())
				.role(userDto.getRole().toUpperCase())
				.status(AppConstants.ACTIVE_USER_STATUS)
				.userAddedBy(addByUser)
				.build();	
		
		return this.modelMapper.map(this.userRepository.save(user), UserDto.class);  
	} 

	@Override
	public List<UserDto> getUsers() {
		return this.userRepository.findAll().stream().map(u -> this.modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public UserDto updateUser(User addByUser,UserDto userDto) 
	{
		var user = User.builder()
				.userId(userDto.getUserId())
				.contactNo(userDto.getContactNo())
				.email(userDto.getEmail())
				.enabled(true)
				.fullName(userDto.getFullName())
				.otp(0)
				.otpSentOn(null)
				.password(passwordEncoder.encode(userDto.getPassword()))
				.registeredOn(new Date())
				.role(userDto.getRole().toUpperCase())
				.status(AppConstants.ACTIVE_USER_STATUS)
				.userAddedBy(addByUser)
				.build();	
		
		return this.modelMapper.map(this.userRepository.save(user), UserDto.class);  
	} 

	@Override
	public UserResponse forgetUserPassword(EmployeeRequest user) {
		User otpUpdated = null;
		if(user != null) {
			User existingUser = userRepository.findByContactNo(user.getContactNo()).orElseThrow(()->new ResourceNotFoundException("User", " Employee Id ", user.getEmployeeId()));
			if(existingUser != null) {
		        Random random = new Random();
		        // Generate a random 6-digit number
		        int randomNumber = 100000 + random.nextInt(900000);
		        System.out.println("randomNumber : "+randomNumber);
		        existingUser.setOtp(randomNumber);
		        existingUser.setOtpSentOn(new Date());
		        otpUpdated = userRepository.save(existingUser);
		        return this.modelMapper.map(otpUpdated, UserResponse.class);
			}
		}
		return this.modelMapper.map(otpUpdated, UserResponse.class);
	}	
	
	@Override
	public UserResponse VerifyOtp(OtpRequest otp) {
		User existingUser = null;
		if(otp != null) {
			existingUser = userRepository.findByContactNo(otp.getContactNo()).orElseThrow(()->new ResourceNotFoundException("User", " Employee Id ", otp.getEmployeeId()));
			if(existingUser != null && existingUser.getOtp() == otp.getOtp()) 
			{
				return this.modelMapper.map(existingUser, UserResponse.class);											
			}
		}
		return this.modelMapper.map(existingUser, UserResponse.class);
	}

	@Override
	public UserResponse updatePassword(PassKeyRequest password) {
		User existingUser = null;
		if(password != null) {
			existingUser = userRepository.findByContactNo(password.getContactNo()).orElseThrow(()->new ResourceNotFoundException("User", " Employee Id ", password.getEmployeeId()));
			if(existingUser != null && existingUser.getOtp() == password.getOtp())
			{
				existingUser.setPassword(this.passwordEncoder.encode(password.getPassword()));
				User updatedPassword = userRepository.save(existingUser);
				return this.modelMapper.map(updatedPassword, UserResponse.class);					
			}
		}
		return this.modelMapper.map(existingUser, UserResponse.class);
	}
	
	@Override
	public String changeUserPassword(ChangePassKey password,Principal principal) {
		if(password != null && principal !=null) {
			String empId = principal.getName();
			User user = userRepository.findByContactNo(empId).orElse(null);
			if(user != null) {
				 boolean userpassword = passwordEncoder.matches(password.getOldPassword(),user.getPassword());
                 if (userpassword)
                 {
                	 user.setPassword(passwordEncoder.encode(password.getNewPassword()));
                     userRepository.save(user);
                     return "Success";
                 }
                 else
                	 return "Incorrect";
			}
		}
		return "Error";
	}

//	@Override
//	public UserDto getUser(String empId) 
//	{
//		return this.modelMapper.map(userRepo.findByEmployeeId(empId).orElse(null), UserDto.class);
//	}
}
