package com.aiims.pds.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getfOccupation() {
		return fOccupation;
	}
	public void setfOccupation(String fOccupation) {
		this.fOccupation = fOccupation;
	}
	public Long getfIncome() {
		return fIncome;
	}
	public void setfIncome(Long fIncome) {
		this.fIncome = fIncome;
	}
	public String getmOccupation() {
		return mOccupation;
	}
	public void setmOccupation(String mOccupation) {
		this.mOccupation = mOccupation;
	}
	public Long getmIncome() {
		return mIncome;
	}
	public void setmIncome(Long mIncome) {
		this.mIncome = mIncome;
	}
	public Long getgIncome() {
		return gIncome;
	}
	public void setgIncome(Long gIncome) {
		this.gIncome = gIncome;
	}
	public String getFamilyType() {
		return familyType;
	}
	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}
	public int getFamilyMemberCount() {
		return familyMemberCount;
	}
	public void setFamilyMemberCount(int familyMemberCount) {
		this.familyMemberCount = familyMemberCount;
	}
	public String getBplCardHolder() {
		return bplCardHolder;
	}
	public void setBplCardHolder(String bplCardHolder) {
		this.bplCardHolder = bplCardHolder;
	}
	public String getAbhaHolder() {
		return abhaHolder;
	}
	public void setAbhaHolder(String abhaHolder) {
		this.abhaHolder = abhaHolder;
	}
	public String getCghsGovtscheme() {
		return cghsGovtscheme;
	}
	public void setCghsGovtscheme(String cghsGovtscheme) {
		this.cghsGovtscheme = cghsGovtscheme;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
