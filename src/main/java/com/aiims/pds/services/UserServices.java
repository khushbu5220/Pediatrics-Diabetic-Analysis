package com.aiims.pds.services;

import java.security.Principal;

import com.aiims.pds.modals.Baseline;
import com.aiims.pds.modals.FamilyHistory;
import com.aiims.pds.payloads.BaselineDto;
import com.aiims.pds.payloads.FamilyHistoryDto;

public interface UserServices {

	BaselineDto createBaseline(Principal principal, Baseline baseline);

	FamilyHistoryDto createFamilyHistory(Principal principal, FamilyHistory familyHistory);

}
