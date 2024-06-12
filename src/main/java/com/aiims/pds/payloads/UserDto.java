package com.aiims.pds.payloads;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.aiims.pds.modals.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto 
{
	private Long userId;
	@NotEmpty(message="Please provide a User Name.")
	@Pattern(regexp = "^[a-zA-Z'-]+(?:\\s[a-zA-Z'-]+)*$",message = "User Name must be of 6 to 12 length with no special characters.")
	private String fullName;
	@NotEmpty(message="Please provide a Mobile Number.")
	@Pattern(regexp = "^[0-9]{10}$",message = "Mobile Number must be of 10 numbers !!")
	private String contactNo;
	@NotEmpty(message = "Please provide a Email Id.")
	@Email(message = "Email address is not valid.")
	private String email;
	private String status;
	private Boolean enabled;
	@NotEmpty
	private String password;
	private Date registeredOn;
	private Date otpSentOn; 
	private int otp;
	private String role;
	private User userAddedBy;
}
