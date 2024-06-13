package com.aiims.pds.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyHistoryDto 
{
	private Long id;
	private String hDiabetesParents;
	private String hDiabetesMGrandparents;
	private String hDiabetesPGrandparents;
	private String hDiabetesFamily;
	private String hBirth;
	private String hBirthRemarks;
	private String hDevelopment;
	private String hDevelopmentRemarks;
	private String hImmunisation;
	private String hImmunisationRemarks;
}
