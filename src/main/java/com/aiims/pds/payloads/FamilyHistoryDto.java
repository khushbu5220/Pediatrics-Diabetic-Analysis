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
	private String hDiabetesMother;
	private String hDiabetesFather;
	private String hDiabetesMGrandMother;
	private String hDiabetesMGrandFather;
	private String hDiabetesFGrandMother;
	private String hDiabetesFGrandFather;
	private String hDiabetesFamily;
	private String hBirthTerm;
	private String hBirthdelivery;
	private String hBirthBW;
	private String hBirthcry;
	private String hBirthRemarks;
	private String hDevelopment;
	private String hDevelopmentRemarks;
	private String hImmunisation;
	private String hImmunisationRemarks;
	
	public String gethDiabetesFamily() {
		return hDiabetesFamily;
	}
	public void sethDiabetesFamily(String hDiabetesFamily) {
		this.hDiabetesFamily = hDiabetesFamily;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String gethDiabetesMother() {
		return hDiabetesMother;
	}
	public void sethDiabetesMother(String hDiabetesMother) {
		this.hDiabetesMother = hDiabetesMother;
	}
	public String gethDiabetesFather() {
		return hDiabetesFather;
	}
	public void sethDiabetesFather(String hDiabetesFather) {
		this.hDiabetesFather = hDiabetesFather;
	}
	public String gethDiabetesMGrandMother() {
		return hDiabetesMGrandMother;
	}
	public void sethDiabetesMGrandMother(String hDiabetesMGrandMother) {
		this.hDiabetesMGrandMother = hDiabetesMGrandMother;
	}
	public String gethDiabetesMGrandFather() {
		return hDiabetesMGrandFather;
	}
	public void sethDiabetesMGrandFather(String hDiabetesMGrandFather) {
		this.hDiabetesMGrandFather = hDiabetesMGrandFather;
	}
	public String gethDiabetesFGrandMother() {
		return hDiabetesFGrandMother;
	}
	public void sethDiabetesFGrandMother(String hDiabetesFGrandMother) {
		this.hDiabetesFGrandMother = hDiabetesFGrandMother;
	}
	public String gethDiabetesFGrandFather() {
		return hDiabetesFGrandFather;
	}
	public void sethDiabetesFGrandFather(String hDiabetesFGrandFather) {
		this.hDiabetesFGrandFather = hDiabetesFGrandFather;
	}
	public String gethBirthTerm() {
		return hBirthTerm;
	}
	public void sethBirthTerm(String hBirthTerm) {
		this.hBirthTerm = hBirthTerm;
	}
	public String gethBirthdelivery() {
		return hBirthdelivery;
	}
	public void sethBirthdelivery(String hBirthdelivery) {
		this.hBirthdelivery = hBirthdelivery;
	}
	public String gethBirthBW() {
		return hBirthBW;
	}
	public void sethBirthBW(String hBirthBW) {
		this.hBirthBW = hBirthBW;
	}
	public String gethBirthcry() {
		return hBirthcry;
	}
	public void sethBirthcry(String hBirthcry) {
		this.hBirthcry = hBirthcry;
	}
}
