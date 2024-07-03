package com.aiims.pds.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtpRequest {
		
	@NotEmpty(message="Please provide a Employee Id.")
	@Pattern(regexp = "^[a-zA-Z]{1}[0-9]{6}$",message = "Employee Id must be of 6 length with no special characters and first letter must be alphabet.")	
	private String employeeId;
		
	@NotEmpty(message="Please provide a Mobile Number.")
	@Pattern(regexp = "^[0-9]{10}$",message = "Mobile Number must be of 10 numbers !!")
	private String contactNo;
	
	@NotNull(message = "Please provide sent Otp !!")
//	@Size(min = 0 ,max = 6 ,message = "Please provide sent Otp !!")
	private int otp;
	
}
