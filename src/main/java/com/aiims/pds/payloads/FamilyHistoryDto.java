package com.aiims.pds.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
	public String gethDiabetesParents() {
		return hDiabetesParents;
	}
	public void sethDiabetesParents(String hDiabetesParents) {
		this.hDiabetesParents = hDiabetesParents;
	}
	public String gethDiabetesMGrandparents() {
		return hDiabetesMGrandparents;
	}
	public void sethDiabetesMGrandparents(String hDiabetesMGrandparents) {
		this.hDiabetesMGrandparents = hDiabetesMGrandparents;
	}
	public String gethDiabetesPGrandparents() {
		return hDiabetesPGrandparents;
	}
	public void sethDiabetesPGrandparents(String hDiabetesPGrandparents) {
		this.hDiabetesPGrandparents = hDiabetesPGrandparents;
	}
	public String gethDiabetesFamily() {
		return hDiabetesFamily;
	}
	public void sethDiabetesFamily(String hDiabetesFamily) {
		this.hDiabetesFamily = hDiabetesFamily;
	}
	public String gethBirth() {
		return hBirth;
	}
	public void sethBirth(String hBirth) {
		this.hBirth = hBirth;
	}
	public String gethBirthRemarks() {
		return hBirthRemarks;
	}
	public void sethBirthRemarks(String hBirthRemarks) {
		this.hBirthRemarks = hBirthRemarks;
	}
	public String gethDevelopment() {
		return hDevelopment;
	}
	public void sethDevelopment(String hDevelopment) {
		this.hDevelopment = hDevelopment;
	}
	public String gethDevelopmentRemarks() {
		return hDevelopmentRemarks;
	}
	public void sethDevelopmentRemarks(String hDevelopmentRemarks) {
		this.hDevelopmentRemarks = hDevelopmentRemarks;
	}
	public String gethImmunisation() {
		return hImmunisation;
	}
	public void sethImmunisation(String hImmunisation) {
		this.hImmunisation = hImmunisation;
	}
	public String gethImmunisationRemarks() {
		return hImmunisationRemarks;
	}
	public void sethImmunisationRemarks(String hImmunisationRemarks) {
		this.hImmunisationRemarks = hImmunisationRemarks;
	}
}
