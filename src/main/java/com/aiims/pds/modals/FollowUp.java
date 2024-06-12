package com.aiims.pds.modals;

import java.time.LocalDate;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_followup")
public class FollowUp 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	private String height;
	private String weight;
	private String bp;
	private String smr;
	private String lipodystrophy;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private HbA1cTable hba1ctable;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private ThyroidProfile thyroidProfile;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private InvestigationTable celiacSerology;
	private String multiClinicVisit;
	private LocalDate lastVisitMultiClinicDate;
	private String insulineDoseWritten;
	private String totDailyDoseInsulin;
	private String basalInsulineDose;
	private String bolusInsuline;
	private String investigationNextOPD;
	private String nextIvestigationGiven;
	private String nextInvestigationDate;
	private String freqBgPerDay;
	private String freqBgPerMonth;
	private String physicalActivity;
	private String insulinStorage;
	private String insulinRotation;
	private String correctionCorrectly;
	private String diabetesDuration;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private LipidProfile lipidProfile;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private UrineAlbuminCreatinineRatio urineAlbuminCreatinineRatio;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private FundusExamination fundusExamination;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private FootExamination footExamination;
	private String remarks;
}
