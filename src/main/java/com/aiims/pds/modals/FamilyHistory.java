package com.aiims.pds.modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_familyHistory")
public class FamilyHistory 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String hDiabetesMother;
	private String hDiabetesFather;
	private String hDiabetesMGrandMother;
	private String hDiabetesMGrandFather;
	private String hDiabetesFGrandMother;
	private String hDiabetesFGrandFather;
	private String hDiabetesFamily;
	private String hBirth;
	private String hBirthRemarks;
	private String hDevelopment;
	private String hDevelopmentRemarks;
	private String hImmunisation;
	private String hImmunisationRemarks;
}
