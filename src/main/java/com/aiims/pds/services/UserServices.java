package com.aiims.pds.services;

import java.security.Principal;

import com.aiims.pds.modals.Baseline;
import com.aiims.pds.modals.FamilyHistory;
import com.aiims.pds.modals.FollowUp;
import com.aiims.pds.modals.Investigation;
import com.aiims.pds.modals.SocioeconomicHistory;
import com.aiims.pds.payloads.BaselineDto;
import com.aiims.pds.payloads.FamilyHistoryDto;

public interface UserServices {

	BaselineDto createBaseline(Principal principal, Baseline baseline);

	BaselineDto createFamilyHistory(Principal principal, Long baselineId, FamilyHistory familyHistory);

	BaselineDto createSocioeconomicHistory(Principal principal, Long baselineId,
			SocioeconomicHistory socioeconomicHistory);

	BaselineDto createInvestigation(Principal principal, Long baselineId, Investigation investigation);

	BaselineDto createFollowUP(Principal principal, Long baselineId, FollowUp followUp);

}
