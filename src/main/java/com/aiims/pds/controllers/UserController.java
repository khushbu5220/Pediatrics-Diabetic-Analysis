package com.aiims.pds.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiims.pds.modals.Baseline;
import com.aiims.pds.modals.FamilyHistory;
import com.aiims.pds.payloads.BaselineDto;
import com.aiims.pds.payloads.FamilyHistoryDto;
import com.aiims.pds.repository.UserRepository;
import com.aiims.pds.services.UserServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/user-action/")
public class UserController 
{
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/createBaseLine")
	public ResponseEntity<BaselineDto> createBaseLine(Principal principal, @RequestBody Baseline baseline)
	{
		BaselineDto saveBaseline = this.userServices.createBaseline(principal, baseline);
		return new ResponseEntity<>(saveBaseline, HttpStatus.CREATED);
	}
	
	@PostMapping("/createFamilyHistory")
	public ResponseEntity<FamilyHistoryDto> createFamilyHistory(Principal principal, @RequestBody FamilyHistory familyHistory)
	{
		FamilyHistoryDto familyHistoryDto = this.userServices.createFamilyHistory(principal, familyHistory);
		return new ResponseEntity<>(familyHistoryDto, HttpStatus.CREATED);
	}
}