package com.aiims.pds.services;

import java.util.List;

import com.aiims.pds.modals.User;
import com.aiims.pds.payloads.UserDto;

import jakarta.validation.Valid;

public interface AdminServices 
{
	UserDto registerSuperAdminUser(@Valid UserDto userDto);
	UserDto registerNewUser(User user, UserDto adduser);

	List<UserDto> getUsers();
	
//	//forget password
//	UserResponse forgetUserPassword(EmployeeRequest user);
//	//forget password
//	UserResponse VerifyOtp(OtpRequest otp);
//	//forget password
//	UserResponse updatePassword(PassKeyRequest password);	
//	
//	//change password
//	String changeUserPassword(ChangePassKey password,Principal principal);
//	UserDto getUser(String empId);
}
