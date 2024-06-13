package com.aiims.pds.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aiims.pds.config.AppConstants;
import com.aiims.pds.modals.User;
import com.aiims.pds.payloads.UserDto;
import com.aiims.pds.repository.UserRepository;
import com.aiims.pds.services.AdminServices;

import jakarta.validation.Valid;

@Service
public class AdminServicesImpl implements AdminServices 
{
	@Autowired
	private UserRepository userRepository;
	@Autowired(required=true)
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto registerSuperAdminUser(@Valid UserDto userDto) 
	{
		User adduser = this.modelMapper.map(userDto, User.class);
		//status
		if(userDto.getStatus()!=null) {
			adduser.setStatus(userDto.getStatus());
		}else {
			adduser.setStatus(AppConstants.ACTIVE_USER_STATUS);
		}
		
		//enabled user
		adduser.setEnabled(true);
		//encoded the password
		System.out.println("pswd : "+this.passwordEncoder.encode(userDto.getPassword())+" | "+userDto.getPassword());
		adduser.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		//registered on
		adduser.setRegisteredOn(new Date());
		
		adduser.setRole(userDto.getRole());
//		adduser.setUserAddedBy(0L);
		
		User saveduser = this.userRepository.save(adduser);
		System.out.println("saveduser : "+saveduser.toString());
		return this.modelMapper.map(saveduser, UserDto.class);
	}
	
	@Override
	public UserDto registerNewUser(User user,UserDto userDto) 
	{
		User adduser = this.modelMapper.map(userDto, User.class);
		//status
		if(userDto.getStatus()!=null) {
			adduser.setStatus(userDto.getStatus());
		}else {
			adduser.setStatus(AppConstants.ACTIVE_USER_STATUS);
		}
		
		//enabled user
		adduser.setEnabled(true);
		//encoded the password
		adduser.setPassword(this.passwordEncoder.encode("Aiims@123"));
		//registered on
		adduser.setRegisteredOn(new Date());
		
		adduser.setRole(userDto.getRole());
		adduser.setUserAddedBy(user);
		
		User saveduser = this.userRepository.save(adduser);
		return this.modelMapper.map(saveduser, UserDto.class);
	}

	@Override
	public List<UserDto> getUsers() {
		List<User> getusersList = this.userRepository.findAll();
		return getusersList.stream().map(u -> this.modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
	}

//	@Override
//	public UserResponse forgetUserPassword(EmployeeRequest user) {
//		User otpUpdated = null;
//		if(user != null) {
//			User existingUser = userRepo.findByEmployeeIdAndContactNo(user.getEmployeeId(),user.getContactNo()).orElseThrow(()->new ResourceNotFoundException("User", " Employee Id ", user.getEmployeeId()));
//			if(existingUser != null) {
//		        Random random = new Random();
//		        // Generate a random 6-digit number
//		        int randomNumber = 100000 + random.nextInt(900000);
//		        System.out.println("randomNumber : "+randomNumber);
//		        existingUser.setOtp(randomNumber);
//		        existingUser.setOtpSentOn(new Date());
//		        otpUpdated = userRepo.save(existingUser);
//		        return this.modelMapper.map(otpUpdated, UserResponse.class);
//			}
//		}
//		return this.modelMapper.map(otpUpdated, UserResponse.class);
//	}	
//	
//	@Override
//	public UserResponse VerifyOtp(OtpRequest otp) {
//		User existingUser = null;
//		if(otp != null) {
//			existingUser = userRepo.findByEmployeeIdAndContactNo(otp.getEmployeeId(),otp.getContactNo()).orElseThrow(()->new ResourceNotFoundException("User", " Employee Id ", otp.getEmployeeId()));
//			if(existingUser != null && existingUser.getOtp() == otp.getOtp()) 
//			{
//				return this.modelMapper.map(existingUser, UserResponse.class);											
//			}
//		}
//		return this.modelMapper.map(existingUser, UserResponse.class);
//	}
//
//	@Override
//	public UserResponse updatePassword(PassKeyRequest password) {
//		User existingUser = null;
//		if(password != null) {
//			existingUser = userRepo.findByEmployeeIdAndContactNo(password.getEmployeeId(),password.getContactNo()).orElseThrow(()->new ResourceNotFoundException("User", " Employee Id ", password.getEmployeeId()));
//			if(existingUser != null && existingUser.getOtp() == password.getOtp())
//			{
//				existingUser.setPassword(this.passwordEncoder.encode(password.getPassword()));
//				User updatedPassword = userRepo.save(existingUser);
//				return this.modelMapper.map(updatedPassword, UserResponse.class);					
//			}
//		}
//		return this.modelMapper.map(existingUser, UserResponse.class);
//	}
//	
//	@Override
//	public String changeUserPassword(ChangePassKey password,Principal principal) {
//		if(password != null && principal !=null) {
//			String empId = principal.getName();
//			User user = userRepo.findByEmployeeId(empId).orElse(null);
//			if(user != null) {
//				 boolean userpassword = passwordEncoder.matches(password.getOldPassword(),user.getPassword());
//                 if (userpassword)
//                 {
//                	 user.setPassword(passwordEncoder.encode(password.getNewPassword()));
//                     userRepo.save(user);
//                     return "Success";
//                 }
//                 else
//                	 return "Incorrect";
//			}
//		}
//		return "Error";
//	}
//
//	@Override
//	public UserDto getUser(String empId) 
//	{
//		return this.modelMapper.map(userRepo.findByEmployeeId(empId).orElse(null), UserDto.class);
//	}
}
