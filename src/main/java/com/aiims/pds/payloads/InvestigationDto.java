package com.aiims.pds.payloads;

import java.time.LocalDate;
import java.util.List;

import com.aiims.pds.modals.CeliacSerology;
import com.aiims.pds.modals.FootExamination;
import com.aiims.pds.modals.FundusExamination;
import com.aiims.pds.modals.HbA1cTable;
import com.aiims.pds.modals.InvestigationTable;
import com.aiims.pds.modals.LipidProfile;
import com.aiims.pds.modals.UrineAlbuminCreatinineRatio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvestigationDto 
{
	private Long id;
	private LocalDate cbcDate;
	private String hb;
	private String tlc;
	private String platelets;
	private LocalDate lftrftDate;
	private String urea;
	private String creatinine;
	private String calcium;
	private String phosphate;
	private String alp;
	private String sgot;
	private String sgpt;
	private String vitd;
	private String remarks;
	private List<HbA1cTable> hbA1ctable;
	private CeliacSerology celiacSerology;
	private UrineAlbuminCreatinineRatio urineAlbuminCreatinineRatio;
	private FundusExamination fundusExamination;
	private FootExamination footExamination;
	private LipidProfile lipidProfile;
}
