package com.aiims.pds.services;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import com.aiims.pds.modals.Investigation;
import com.aiims.pds.payloads.BaselineDto;
import com.aiims.pds.payloads.FamilyHistoryDto;
import com.aiims.pds.payloads.FollowUpDto;
import com.aiims.pds.payloads.SocioeconomicHistoryDto;

import jakarta.servlet.http.HttpServletResponse;

public interface UserServices {

	BaselineDto createBaseline(Principal principal, Long baselineId, BaselineDto baselineDto);

	BaselineDto createFamilyHistory(Long baselineId, FamilyHistoryDto familyHistoryDto);

	BaselineDto createSocioeconomicHistory(Principal principal, Long baselineId,
			SocioeconomicHistoryDto socioeconomicHistoryDto);

	BaselineDto createInvestigation(Principal principal, Long baselineId, Investigation investigation);

	BaselineDto createFollowUP(Principal principal, Long baselineId, FollowUpDto followUpDto);

	BaselineDto getBaseline(Principal principal, Long baselineId);

	void generatePatientXLS(HttpServletResponse response, BaselineDto baselineDto) throws IOException;

	BaselineDto submitBaseline(Principal principal, Long baselineId);

	List<BaselineDto> getAllBaseline(Principal principal);

	List<FollowUpDto> getFollowUPI(Principal principal);

	List<FollowUpDto> getFollowUPII(Principal principal);

	void getXLSFollowUp(HttpServletResponse response, List<FollowUpDto> followupDto, String num);
}
