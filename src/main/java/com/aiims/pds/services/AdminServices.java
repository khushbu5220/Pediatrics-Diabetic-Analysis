package com.aiims.pds.services;

import java.security.Principal;
import java.util.List;

import com.aiims.pds.modals.User;
import com.aiims.pds.payloads.ChangePassKey;
import com.aiims.pds.payloads.EmployeeRequest;
import com.aiims.pds.payloads.JwtAuthResponse;
import com.aiims.pds.payloads.OtpRequest;
import com.aiims.pds.payloads.PassKeyRequest;
import com.aiims.pds.payloads.UserDto;
import com.aiims.pds.payloads.UserResponse;

import jakarta.validation.Valid;

public interface AdminServices 
{
	UserDto registerSuperAdminUser(@Valid UserDto userDto);
	UserDto registerNewUser(User user, UserDto adduser);

	List<UserDto> getUsers();
	UserDto updateUser(User user, @Valid UserDto userDto);
	
	//forget password
	UserResponse forgetUserPassword(EmployeeRequest user);
	//forget password
	UserResponse VerifyOtp(OtpRequest otp);
	//forget password
	UserResponse updatePassword(PassKeyRequest password);	
	
	//change password
	String changeUserPassword(ChangePassKey password,Principal principal);
//	UserDto getUser(String empId);
}
