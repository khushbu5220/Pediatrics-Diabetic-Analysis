package com.aiims.pds.payloads;

import java.time.LocalDate;
import java.util.Date;

import com.aiims.pds.modals.FootExamination;
import com.aiims.pds.modals.FundusExamination;
import com.aiims.pds.modals.HbA1cTable;
import com.aiims.pds.modals.InvestigationTable;
import com.aiims.pds.modals.LipidProfile;
import com.aiims.pds.modals.ThyroidProfile;
import com.aiims.pds.modals.UrineAlbuminCreatinineRatio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowUpDto 
{
	private Long id;
	private LocalDate date;
	private String height;
	private String weight;
	private String bp;
	private String smrBreastStage;
	private String smrPubicHairStage;
	private String lipodystrophy;
	private HbA1cTable hba1ctable;
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
	private LipidProfile lipidProfile;
	private UrineAlbuminCreatinineRatio urineAlbuminCreatinineRatio;
	private FundusExamination fundusExamination;
	private FootExamination footExamination;
	private String remarks;
	private String status;
	private Date cdt;
}
