package com.aiims.pds.payloads;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private int userId;
	private String fullName;
	private String employeeId;
	private String contactNo;
	private String email;
	private String status;
	private Date registeredOn;
	private Date otpSentOn;
	
}
