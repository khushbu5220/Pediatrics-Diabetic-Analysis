package com.aiims.pds.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aiims.pds.modals.Baseline;
import com.aiims.pds.modals.FamilyHistory;
import com.aiims.pds.modals.FollowUp;
import com.aiims.pds.modals.Investigation;
import com.aiims.pds.modals.SocioeconomicHistory;
import com.aiims.pds.payloads.BaselineDto;
import com.aiims.pds.payloads.FamilyHistoryDto;
import com.aiims.pds.services.UserServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/user-action/")
public class UserController 
{
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/createBaseLine")
	public ResponseEntity<BaselineDto> createBaseLine(Principal principal, @RequestBody BaselineDto baselineDto)
	{
		return new ResponseEntity<>(this.userServices.createBaseline(principal, baselineDto), HttpStatus.CREATED);
	}
	
	@PostMapping("/createFamilyHistory")
	public ResponseEntity<BaselineDto> createFamilyHistory(@RequestParam(name = "baselineId") Long baselineId, @RequestBody FamilyHistoryDto familyHistoryDto)
	{
		BaselineDto baselineDto = this.userServices.createFamilyHistory(baselineId, familyHistoryDto);
		return new ResponseEntity<>(baselineDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/createSocioeconomicHistory")
	public ResponseEntity<BaselineDto> createSocioeconomicHistory(Principal principal, @RequestParam(name = "baselineId") Long baselineId, @RequestBody SocioeconomicHistory socioeconomicHistory)
	{
		BaselineDto baselineDto = this.userServices.createSocioeconomicHistory(principal, baselineId, socioeconomicHistory);
		return new ResponseEntity<>(baselineDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/createInvestigation")
	public ResponseEntity<BaselineDto> createInvestigation(Principal principal, @RequestParam(name = "baselineId") Long baselineId, @RequestBody Investigation investigation)
	{
		BaselineDto baselineDto = this.userServices.createInvestigation(principal, baselineId, investigation);
		return new ResponseEntity<>(baselineDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/createFollowUp")
	public ResponseEntity<BaselineDto> createFollowUp(Principal principal, @RequestParam(name = "baselineId") Long baselineId, @RequestBody FollowUp followUp)
	{
		BaselineDto baselineDto = this.userServices.createFollowUP(principal, baselineId, followUp);
		return new ResponseEntity<>(baselineDto, HttpStatus.CREATED);
	}
	
}