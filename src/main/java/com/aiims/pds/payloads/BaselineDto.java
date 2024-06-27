package com.aiims.pds.payloads;

import java.time.LocalDate;
import java.util.List;

import com.aiims.pds.modals.FamilyHistory;
import com.aiims.pds.modals.FollowUp;
import com.aiims.pds.modals.Investigation;
import com.aiims.pds.modals.SocioeconomicHistory;
import com.aiims.pds.modals.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class BaselineDto 
{
	private Long id;
	private String name;
	private String diagnosis;
	private String gender;
	private int age;
	private LocalDate dob;
	private Long uhid;
	private String mName;
	private String fName;
	private String contactNo;
	private String address;
	private int dAge;
	private String dMonthYear;
	private int fuAge;
	private String fuMonthYear;
	private String polyuriaPolydipsia;
	private String polyDuration;
	private String weightLoss;
	private String weightlossDuration;
	private String dkaAtDiagnosis;
	private User user;
	private FamilyHistory familyHistory;
	private SocioeconomicHistory socioeconomicHistory;
	private Investigation investigation;
	private List<FollowUp> followUps;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Long getUhid() {
		return uhid;
	}
	public void setUhid(Long uhid) {
		this.uhid = uhid;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getdAge() {
		return dAge;
	}
	public void setdAge(int dAge) {
		this.dAge = dAge;
	}
	public String getdMonthYear() {
		return dMonthYear;
	}
	public void setdMonthYear(String dMonthYear) {
		this.dMonthYear = dMonthYear;
	}
	public int getFuAge() {
		return fuAge;
	}
	public void setFuAge(int fuAge) {
		this.fuAge = fuAge;
	}
	public String getFuMonthYear() {
		return fuMonthYear;
	}
	public void setFuMonthYear(String fuMonthYear) {
		this.fuMonthYear = fuMonthYear;
	}
	public String getPolyuriaPolydipsia() {
		return polyuriaPolydipsia;
	}
	public void setPolyuriaPolydipsia(String polyuriaPolydipsia) {
		this.polyuriaPolydipsia = polyuriaPolydipsia;
	}
	public String getPolyDuration() {
		return polyDuration;
	}
	public void setPolyDuration(String polyDuration) {
		this.polyDuration = polyDuration;
	}
	public String getWeightLoss() {
		return weightLoss;
	}
	public void setWeightLoss(String weightLoss) {
		this.weightLoss = weightLoss;
	}
	public String getWeightlossDuration() {
		return weightlossDuration;
	}
	public void setWeightlossDuration(String weightlossDuration) {
		this.weightlossDuration = weightlossDuration;
	}
	public String getDkaAtDiagnosis() {
		return dkaAtDiagnosis;
	}
	public void setDkaAtDiagnosis(String dkaAtDiagnosis) {
		this.dkaAtDiagnosis = dkaAtDiagnosis;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public FamilyHistory getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(FamilyHistory familyHistory) {
		this.familyHistory = familyHistory;
	}
	public SocioeconomicHistory getSocioeconomicHistory() {
		return socioeconomicHistory;
	}
	public void setSocioeconomicHistory(SocioeconomicHistory socioeconomicHistory) {
		this.socioeconomicHistory = socioeconomicHistory;
	}
	public Investigation getInvestigation() {
		return investigation;
	}
	public void setInvestigation(Investigation investigation) {
		this.investigation = investigation;
	}
	public List<FollowUp> getFollowUps() {
		return followUps;
	}
	public void setFollowUps(List<FollowUp> followUps) {
		this.followUps = followUps;
	}
}
