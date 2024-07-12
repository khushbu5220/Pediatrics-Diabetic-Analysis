package com.aiims.pds.modals;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
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
	private LocalDate celiacSerologyDate;
	private String celiacSerologyValue;
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
	private String status;
	private Date cdt;
}
