package com.aiims.pds.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocioeconomicHistoryDto 
{
	private Long id;
	private String fOccupation;
	private Long fIncome;
	private String mOccupation;
	private Long mIncome;
	private Long gIncome;
	private String familyType;
	private int familyMemberCount;
	private String bplCardHolder;
	private String abhaHolder;
	private String cghsGovtscheme;
	private String remarks;
}
