package com.aiims.pds.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse 
{
	private String role;
	private String token;
	private String username;
}
