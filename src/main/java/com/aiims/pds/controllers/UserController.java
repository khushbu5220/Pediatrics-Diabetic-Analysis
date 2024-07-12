package com.aiims.pds.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aiims.pds.modals.FollowUp;
import com.aiims.pds.modals.Investigation;
import com.aiims.pds.payloads.BaselineDto;
import com.aiims.pds.payloads.FamilyHistoryDto;
import com.aiims.pds.payloads.FollowUpDto;
import com.aiims.pds.payloads.SocioeconomicHistoryDto;
import com.aiims.pds.services.UserServices;

import jakarta.servlet.http.HttpServletResponse;

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
	
	@PostMapping("/createFamilyHistory/{baselineId}")
	public ResponseEntity<BaselineDto> createFamilyHistory(@PathVariable("baselineId") Long baselineId, @RequestBody FamilyHistoryDto familyHistoryDto)
	{
		System.out.println("Line 39 : " +baselineId +"|"+ familyHistoryDto.toString());
		BaselineDto baselineDto = this.userServices.createFamilyHistory(baselineId, familyHistoryDto);
		return new ResponseEntity<>(baselineDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/createSocioeconomicHistory/{baselineId}")
	public ResponseEntity<BaselineDto> createSocioeconomicHistory(Principal principal, @PathVariable("baselineId") Long baselineId, @RequestBody SocioeconomicHistoryDto socioeconomicHistoryDto)
	{
		BaselineDto baselineDto = this.userServices.createSocioeconomicHistory(principal, baselineId, socioeconomicHistoryDto);
		return new ResponseEntity<>(baselineDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/createInvestigation/{baselineId}")
	public ResponseEntity<BaselineDto> createInvestigation(Principal principal, @PathVariable("baselineId") Long baselineId, @RequestBody Investigation investigation)
	{
		System.out.println("investigation : "+investigation.toString());
		BaselineDto baselineDto = this.userServices.createInvestigation(principal, baselineId, investigation);
		return new ResponseEntity<>(baselineDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/createFollowUp/{baselineId}")
	public ResponseEntity<BaselineDto> createFollowUp(Principal principal, @PathVariable("baselineId") Long baselineId, @RequestBody FollowUpDto followUpDto)
	{
		BaselineDto baselineDto = this.userServices.createFollowUP(principal, baselineId, followUpDto);
		return new ResponseEntity<>(baselineDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/submitBaseline/{baselineId}")
	public ResponseEntity<BaselineDto> submitBaseline(Principal principal, @PathVariable("baselineId") Long baselineId)
	{
		BaselineDto baselineDto = this.userServices.submitBaseline(principal, baselineId);
		return new ResponseEntity<>(baselineDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/getBaseline/{baselineId}")
	public ResponseEntity<BaselineDto> getBaseline(Principal principal, @PathVariable("baselineId") Long baselineId)
	{
		BaselineDto baselineDto = this.userServices.getBaseline(principal, baselineId);
		return new ResponseEntity<>(baselineDto, HttpStatus.OK);
	}
	
	@GetMapping("/getXLSPatient/{baselineId}")
	public void getXLSPatient(Principal principal, HttpServletResponse response, @PathVariable("baselineId") Long baselineId) throws IOException
	{
		BaselineDto baselineDto = this.userServices.getBaseline(principal, baselineId);
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename="+baselineDto.getUhid()+"-DiabetesRegistry.xls";
		response.setHeader(headerKey, headerValue);
		this.userServices.generatePatientXLS(response, baselineDto);
	}
}