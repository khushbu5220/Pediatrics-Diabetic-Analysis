package com.aiims.pds.modals;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_familyHistory")
public class FamilyHistory 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
