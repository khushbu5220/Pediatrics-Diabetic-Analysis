package com.aiims.pds.services.Impl;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiims.pds.config.AppConstants;
import com.aiims.pds.exceptions.ResourceNotFoundException;
import com.aiims.pds.modals.Baseline;
import com.aiims.pds.modals.FamilyHistory;
import com.aiims.pds.modals.FollowUp;
import com.aiims.pds.modals.FootExamination;
import com.aiims.pds.modals.FundusExamination;
import com.aiims.pds.modals.HbA1cTable;
import com.aiims.pds.modals.Investigation;
import com.aiims.pds.modals.InvestigationTable;
import com.aiims.pds.modals.LipidProfile;
import com.aiims.pds.modals.SocioeconomicHistory;
import com.aiims.pds.modals.ThyroidProfile;
import com.aiims.pds.modals.UrineAlbuminCreatinineRatio;
import com.aiims.pds.modals.User;
import com.aiims.pds.payloads.BaselineDto;
import com.aiims.pds.payloads.FamilyHistoryDto;
import com.aiims.pds.payloads.FollowUpDto;
import com.aiims.pds.payloads.SocioeconomicHistoryDto;
import com.aiims.pds.repository.BaselineRepository;
import com.aiims.pds.repository.FamilyhistoryRepository;
import com.aiims.pds.repository.FollowUpRepository;
import com.aiims.pds.repository.FootExaminationRepository;
import com.aiims.pds.repository.FundusExaminationRepository;
import com.aiims.pds.repository.HbA1cTableRepository;
import com.aiims.pds.repository.InvestigationRepository;
import com.aiims.pds.repository.InvestigationTableRepository;
import com.aiims.pds.repository.LipidProfileRepository;
import com.aiims.pds.repository.SocioeconomicHistoryRepository;
import com.aiims.pds.repository.ThyroidProfileRepository;
import com.aiims.pds.repository.UrineAlbuminCreatinineRatioRepository;
import com.aiims.pds.repository.UserRepository;
import com.aiims.pds.services.UserServices;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("deprecation")
@Service
public class UserServicesImpl implements UserServices 
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BaselineRepository baselineRepository;
	@Autowired
	private FamilyhistoryRepository familyhistoryRepository;
	@Autowired
	private SocioeconomicHistoryRepository socioeconomicHistoryRepository;
	@Autowired
	private InvestigationRepository investigationRepository;
	@Autowired
	private HbA1cTableRepository hbA1cTableRepository;
	@Autowired
	private InvestigationTableRepository investigationTableRepository;
	@Autowired
	private ThyroidProfileRepository thyroidProfileRepository;
	@Autowired
	private LipidProfileRepository lipidProfileRepository;
	@Autowired
	private UrineAlbuminCreatinineRatioRepository urineAlbuminCreatinineRatioRepository;
	@Autowired
	private FundusExaminationRepository fundusExaminationRepository;
	@Autowired
	private FootExaminationRepository footExaminationRepository;
	@Autowired
	private FollowUpRepository followUpRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public BaselineDto createBaseline(Principal principal, BaselineDto baselineDto) 
	{
		String username = principal.getName();		
		User user = this.userRepository.findByContactNo(username).orElseThrow(() -> new ResourceNotFoundException("Username", "ContactNo", username));
		var baseline = Baseline.builder()
				.address(baselineDto.getAddress())
				.age(baselineDto.getAge())
				.cdt(new Date())
				.contactNo(baselineDto.getContactNo())
				.dAge(baselineDto.getdAge())
				.diagnosis(baselineDto.getDiagnosis())
				.dkaAtDiagnosis(baselineDto.getDkaAtDiagnosis())
				.dMonthYear(baselineDto.getdMonthYear())
				.dob(baselineDto.getDob())
				.fName(baselineDto.getfName())
				.fuAge(baselineDto.getFuAge())
				.fuMonthYear(baselineDto.getFuMonthYear())
				.gender(baselineDto.getGender())
				.status(baselineDto.getStatus())
				.mName(baselineDto.getmName())
				.name(baselineDto.getName())
				.polyDuration(baselineDto.getPolyDuration())
				.polyuriaPolydipsia(baselineDto.getPolyuriaPolydipsia())
				.uhid(baselineDto.getUhid())
				.user(user)
				.weightLoss(baselineDto.getWeightLoss())
				.weightlossDuration(baselineDto.getWeightlossDuration())
				.status("SAVE")
				.build();
				
		return this.modelMapper.map(this.baselineRepository.save(baseline), BaselineDto.class);
	}

	@Override
	public BaselineDto createFamilyHistory(Long baselineId, FamilyHistoryDto familyHistoryDto) 
	{		
		var familyHistory = FamilyHistory.builder()
				.hBirth(familyHistoryDto.gethBirth())
				.hBirthRemarks(familyHistoryDto.gethBirthRemarks())
				.hDevelopment(familyHistoryDto.gethDevelopment())
				.hDevelopmentRemarks(familyHistoryDto.gethDevelopmentRemarks())
				.hDiabetesFamily(familyHistoryDto.gethDiabetesFamily())
				.hDiabetesMGrandMother(familyHistoryDto.gethDiabetesMGrandMother())
				.hDiabetesMGrandFather(familyHistoryDto.gethDiabetesMGrandFather())
				.hDiabetesMother(familyHistoryDto.gethDiabetesMother())
				.hDiabetesFather(familyHistoryDto.gethDiabetesFather())
				.hDiabetesFGrandMother(familyHistoryDto.gethDiabetesFGrandMother())
				.hDiabetesFGrandFather(familyHistoryDto.gethDiabetesFGrandFather())
				.hImmunisation(familyHistoryDto.gethImmunisation())
				.hImmunisationRemarks(familyHistoryDto.gethImmunisationRemarks())
				.build();
		Baseline baseline = this.baselineRepository.findById(baselineId).orElseThrow(() -> new ResourceNotFoundException("Baseline", "BaselineId", baselineId));
		baseline.setFamilyHistory(this.familyhistoryRepository.save(familyHistory));
		
		return this.modelMapper.map(this.baselineRepository.save(baseline), BaselineDto.class);
	}

	@Override
	public BaselineDto createSocioeconomicHistory(Principal principal, Long baselineId,
			SocioeconomicHistoryDto socioeconomicHistoryDto) 
	{
		Baseline baseline = this.baselineRepository.findById(baselineId).orElseThrow(() -> new ResourceNotFoundException("Baseline", "BaselineId", baselineId));
		
		var socioeconomicHistory = SocioeconomicHistory.builder()
				.abhaHolder(socioeconomicHistoryDto.getAbhaHolder())
				.bplCardHolder(socioeconomicHistoryDto.getBplCardHolder())
				.cghsGovtscheme(socioeconomicHistoryDto.getCghsGovtscheme())
				.familyMemberCount(socioeconomicHistoryDto.getFamilyMemberCount())
				.familyType(socioeconomicHistoryDto.getFamilyType())
				.fIncome(socioeconomicHistoryDto.getfIncome())
				.fOccupation(socioeconomicHistoryDto.getfOccupation())
				.gIncome(socioeconomicHistoryDto.getgIncome())
				.mIncome(socioeconomicHistoryDto.getmIncome())
				.mOccupation(socioeconomicHistoryDto.getmOccupation())
				.remarks(socioeconomicHistoryDto.getRemarks())
				.build();
		
		baseline.setSocioeconomicHistory(this.socioeconomicHistoryRepository.save(socioeconomicHistory));
			
		return this.modelMapper.map(this.baselineRepository.save(baseline), BaselineDto.class);
	}

	@Override
	public BaselineDto createInvestigation(Principal principal, Long baselineId, Investigation investigation) 
	{
		List<HbA1cTable> hba1ctable = investigation.getHbA1ctable();
		for(HbA1cTable h : hba1ctable)
			this.hbA1cTableRepository.save(h);
		List<InvestigationTable> investigationTable = investigation.getInvestigationTable();
		for(InvestigationTable i : investigationTable)
			this.investigationTableRepository.save(i);
		Investigation savedInvestigation = this.investigationRepository.save(investigation);
		Baseline baseline = this.baselineRepository.getOne(baselineId);
		baseline.setInvestigation(savedInvestigation);
		Baseline b = this.baselineRepository.save(baseline);
		return this.modelMapper.map(b, BaselineDto.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaselineDto createFollowUP(Principal principal, Long baselineId, FollowUpDto followUpDto) 
	{
		HbA1cTable hbA1cTable = hbA1cTableRepository.save(followUpDto.getHba1ctable());
		ThyroidProfile thyroidProfile = thyroidProfileRepository.save(followUpDto.getThyroidProfile());
		LipidProfile lipidProfile = lipidProfileRepository.save(followUpDto.getLipidProfile());
		UrineAlbuminCreatinineRatio urineAlbuminCreatinineRatio = urineAlbuminCreatinineRatioRepository.save(followUpDto.getUrineAlbuminCreatinineRatio());
		FundusExamination fundusExamination = fundusExaminationRepository.save(followUpDto.getFundusExamination());
		FootExamination footExamination = footExaminationRepository.save(followUpDto.getFootExamination());
		
		FollowUp followUp = this.modelMapper.map(followUpDto, FollowUp.class);
		followUp.setFootExamination(footExamination);
		followUp.setFundusExamination(fundusExamination);
		followUp.setHba1ctable(hbA1cTable);
		followUp.setLipidProfile(lipidProfile);
		followUp.setThyroidProfile(thyroidProfile);
		followUp.setUrineAlbuminCreatinineRatio(urineAlbuminCreatinineRatio);
		followUp.setCdt(new Date());
		followUp.setStatus(AppConstants.ACTIVE_USER_STATUS);
		FollowUp savedFollowUp = this.followUpRepository.save(followUp);
		Baseline baseline = this.baselineRepository.getOne(baselineId);
		baseline.setFollowUps((List<FollowUp>) savedFollowUp);
		Baseline b = this.baselineRepository.save(baseline);
		return this.modelMapper.map(b, BaselineDto.class);
	}
	
	@Override
	public BaselineDto submitBaseline(Principal principal, Long baselineId)  
	{		
		Baseline baseline = this.baselineRepository.findById(baselineId).orElseThrow(() -> new ResourceNotFoundException("Baseline", "BaselineId", baselineId));
		baseline.setStatus(AppConstants.ACTIVE_USER_STATUS);
		return this.modelMapper.map(this.modelMapper.map(this.baselineRepository.save(baseline), BaselineDto.class), BaselineDto.class);
	}
	
	@Override
	public BaselineDto getBaseline(Principal principal, Long baselineId)  
	{		
		Baseline baseline = this.baselineRepository.findById(baselineId).orElseThrow(() -> new ResourceNotFoundException("Baseline", "BaselineId", baselineId));
		return this.modelMapper.map(baseline, BaselineDto.class);
	}

	@Override
	public void generatePatientXLS(HttpServletResponse response, BaselineDto baselineDto) 
	{
		try
		(
			HSSFWorkbook workbook = new HSSFWorkbook();
			ServletOutputStream os = response.getOutputStream()
		)
		{
			HSSFSheet sheet = workbook.createSheet("Diabetes Registry of "+baselineDto.getUhid());
			HSSFRow headerRow = sheet.createRow(0);
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(HSSFColor.HSSFColorPredefined.DARK_BLUE.getIndex());
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(headerFont);
			
			headerRow.createCell(0).setCellValue("Baseline Details of "+ baselineDto.getName()+" ("+baselineDto.getUhid()+")");
			
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,46)); 
			for (Cell cell : headerRow)
			    cell.setCellStyle(cellStyle);
			
			HSSFRow row = sheet.createRow(2);
			row.createCell(0).setCellValue("S.No.");
			row.createCell(1).setCellValue("Name");
			row.createCell(2).setCellValue("UHID");
			row.createCell(3).setCellValue("Age");
			row.createCell(4).setCellValue("Sex");
			row.createCell(5).setCellValue("Diagnosis");
			row.createCell(6).setCellValue("DOB");
			row.createCell(7).setCellValue("Father's Name");
			row.createCell(8).setCellValue("Mother's Name");
			row.createCell(9).setCellValue("Contact No.");
			row.createCell(10).setCellValue("Address");
			row.createCell(11).setCellValue("Age of Diagnosis");
			row.createCell(12).setCellValue("Month and Year of Diagnosis");
			row.createCell(13).setCellValue("Since when following up in AIIMS");
			row.createCell(14).setCellValue("Presenting Complaint at Diagnosis");
			row.createCell(15).setCellValue("DKA at Diagnosis(Y/N)");
			row.createCell(16).setCellValue("Family History of Diabetes");
			row.createCell(17).setCellValue("History of Diabetes in Mother(Y/N)");
			row.createCell(18).setCellValue("History of Diabetes in Father(Y/N)");
			row.createCell(19).setCellValue("History of Diabetes in Grandmother(Y/N)");
			row.createCell(20).setCellValue("History of Diabetes in Grandfather(Y/N)");
			row.createCell(21).setCellValue("Birth History");
			row.createCell(22).setCellValue("Development History");
			row.createCell(23).setCellValue("Immunisation History(complete/incomplete)");
			row.createCell(24).setCellValue("Socioeconomic History");
			row.createCell(25).setCellValue("Examination");
			row.createCell(26).setCellValue("Investigations");
			row.createCell(27).setCellValue("Hb");
			row.createCell(28).setCellValue("TLC");
			row.createCell(29).setCellValue("Platelets");
			row.createCell(30).setCellValue("Ur");
			row.createCell(31).setCellValue("Cr");
			row.createCell(32).setCellValue("Ca");
			row.createCell(33).setCellValue("Phosphate");
			row.createCell(34).setCellValue("ALP");
			row.createCell(35).setCellValue("SGOT");
			row.createCell(36).setCellValue("SGPT");
			row.createCell(37).setCellValue("Vit D");
			row.createCell(38).setCellValue("HbA1c");
			row.createCell(39).setCellValue("Celiac Serology");
			row.createCell(40).setCellValue("Urine Albumin/Cr ratio");
			row.createCell(41).setCellValue("Fundus Examination");
			row.createCell(42).setCellValue("Foot Examination");
			row.createCell(43).setCellValue("LDL");
			row.createCell(44).setCellValue("HDL");
			row.createCell(45).setCellValue("TC");
			row.createCell(46).setCellValue("TG");
			
			Font rowFont = workbook.createFont();
			rowFont.setBold(true);
			CellStyle rowCellStyle = workbook.createCellStyle();
			rowCellStyle.setFont(rowFont);
			for (Cell cell1 : row) {
			    cell1.setCellStyle(rowCellStyle);
			    cell1.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
			}

			HSSFRow dataRow = sheet.createRow(3);
			dataRow.createCell(0).setCellValue("1");
			dataRow.createCell(1).setCellValue(baselineDto.getName());
			dataRow.createCell(2).setCellValue(String.valueOf(baselineDto.getUhid()));
			dataRow.createCell(3).setCellValue(baselineDto.getAge());
			dataRow.createCell(4).setCellValue(baselineDto.getGender());
			dataRow.createCell(5).setCellValue(baselineDto.getDiagnosis());
			dataRow.createCell(6).setCellValue(String.valueOf(baselineDto.getDob()));
			dataRow.createCell(7).setCellValue(baselineDto.getfName());
			dataRow.createCell(8).setCellValue(baselineDto.getmName());
			dataRow.createCell(9).setCellValue(baselineDto.getContactNo());
			dataRow.createCell(10).setCellValue(baselineDto.getAddress());
			dataRow.createCell(11).setCellValue(baselineDto.getdAge());
			dataRow.createCell(12).setCellValue(baselineDto.getdMonthYear());
			dataRow.createCell(13).setCellValue(baselineDto.getFuMonthYear()+" "+baselineDto.getFuAge());
			dataRow.createCell(14).setCellValue("");
			dataRow.createCell(15).setCellValue(baselineDto.getDkaAtDiagnosis());
			dataRow.createCell(16).setCellValue("");
			dataRow.createCell(17).setCellValue(baselineDto.getFamilyHistory().getHDiabetesFamily());
			dataRow.createCell(18).setCellValue(baselineDto.getFamilyHistory().getHDiabetesMother());
			dataRow.createCell(19).setCellValue(baselineDto.getFamilyHistory().getHDiabetesFather());
			dataRow.createCell(20).setCellValue(("yes".equalsIgnoreCase(baselineDto.getFamilyHistory().getHDiabetesMGrandMother()) || "yes".equalsIgnoreCase(baselineDto.getFamilyHistory().getHDiabetesFGrandMother())) ? "Yes" : "No");
			dataRow.createCell(21).setCellValue(("yes".equalsIgnoreCase(baselineDto.getFamilyHistory().getHDiabetesMGrandFather()) || "yes".equalsIgnoreCase(baselineDto.getFamilyHistory().getHDiabetesFGrandFather())) ? "Yes" : "No");
			dataRow.createCell(22).setCellValue(baselineDto.getFamilyHistory().getHBirth());
			dataRow.createCell(23).setCellValue(baselineDto.getFamilyHistory().getHDevelopment());
			dataRow.createCell(24).setCellValue(baselineDto.getFamilyHistory().getHImmunisation());
			dataRow.createCell(25).setCellValue("");
			dataRow.createCell(26).setCellValue(baselineDto.getSocioeconomicHistory().getRemarks());
			dataRow.createCell(27).setCellValue("");
			dataRow.createCell(28).setCellValue(baselineDto.getInvestigation().getHb());
			dataRow.createCell(29).setCellValue(baselineDto.getInvestigation().getTlc());
			dataRow.createCell(30).setCellValue(baselineDto.getInvestigation().getPlatelets());
			dataRow.createCell(31).setCellValue(baselineDto.getInvestigation().getUrea());
			dataRow.createCell(32).setCellValue(baselineDto.getInvestigation().getCreatinine());
			dataRow.createCell(33).setCellValue(baselineDto.getInvestigation().getCalcium());
			dataRow.createCell(34).setCellValue(baselineDto.getInvestigation().getPhosphate());
			dataRow.createCell(35).setCellValue(baselineDto.getInvestigation().getAlp());
			dataRow.createCell(36).setCellValue(baselineDto.getInvestigation().getSgot());
			dataRow.createCell(37).setCellValue(baselineDto.getInvestigation().getSgpt());
			dataRow.createCell(38).setCellValue(baselineDto.getInvestigation().getVitd());
			dataRow.createCell(39).setCellValue(baselineDto.getInvestigation().getHbA1ctable().toString());
			dataRow.createCell(40).setCellValue("baselineDto.getInvestigation().getInvestigationTable()");
			dataRow.createCell(41).setCellValue(baselineDto.getName());
			dataRow.createCell(42).setCellValue(baselineDto.getUhid());
			dataRow.createCell(43).setCellValue(baselineDto.getName());
			dataRow.createCell(44).setCellValue(baselineDto.getUhid());
			dataRow.createCell(45).setCellValue(baselineDto.getName());
			dataRow.createCell(46).setCellValue(baselineDto.getUhid());
			
			for (Cell cell1 : dataRow)
			    cell1.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
			
			HSSFRow frow = sheet.createRow(6);
			frow.createCell(0).setCellValue("Follow Details");
			
			sheet.addMergedRegion(new CellRangeAddress(6,6,0,20)); 
			
			HSSFRow row1 = sheet.createRow(8);
			row1.createCell(0).setCellValue("Follow Up Visit");
			row1.createCell(1).setCellValue("Name");
			row1.createCell(2).setCellValue("UHID");
			row1.createCell(3).setCellValue("Age");
			row1.createCell(4).setCellValue("Sex");
			row1.createCell(5).setCellValue("Weight");
			row1.createCell(6).setCellValue("Height");
			row1.createCell(7).setCellValue("BP");
			row1.createCell(8).setCellValue("SMR");
			row1.createCell(9).setCellValue("Lipodystrophy");
			row1.createCell(10).setCellValue("HbA1c");
			row1.createCell(11).setCellValue("T3");
			row1.createCell(12).setCellValue("T4");
			row1.createCell(13).setCellValue("TSH");
			row1.createCell(14).setCellValue("FT4");
			row1.createCell(15).setCellValue("Celiac Serology");
			row1.createCell(16).setCellValue("Date of Last Visit in the Multidisciplinary Clinic");
			row1.createCell(17).setCellValue("Insulin Dose written or not");
			row1.createCell(18).setCellValue("Total Daily Dose of Insulin");
			row1.createCell(19).setCellValue("Basal Insulin Dose");
			row1.createCell(20).setCellValue("Bolus Insulin Dose");
			
			for (Cell cell1 : row1) {
			    cell1.setCellStyle(rowCellStyle);
			    cell1.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
			}
			
			int dataRowIndex = 8;
			
			for(FollowUp followUp : baselineDto.getFollowUps())
			{
				HSSFRow fdataRow = sheet.createRow(dataRowIndex++);
				fdataRow.createCell(0).setCellValue(followUp.getCdt());
				fdataRow.createCell(1).setCellValue(baselineDto.getName());
				fdataRow.createCell(2).setCellValue(String.valueOf(baselineDto.getUhid()));
				fdataRow.createCell(3).setCellValue(baselineDto.getAge());
				fdataRow.createCell(4).setCellValue(baselineDto.getGender());
				fdataRow.createCell(5).setCellValue(followUp.getWeight());
				fdataRow.createCell(6).setCellValue(followUp.getHeight());
				fdataRow.createCell(7).setCellValue(followUp.getBp());
				fdataRow.createCell(8).setCellValue(followUp.getSmr());
				fdataRow.createCell(9).setCellValue(followUp.getLipodystrophy());
				fdataRow.createCell(10).setCellValue(followUp.getHba1ctable().getHba1c());
				fdataRow.createCell(11).setCellValue(followUp.getThyroidProfile().getT3());
				fdataRow.createCell(12).setCellValue(followUp.getThyroidProfile().getT4());
				fdataRow.createCell(13).setCellValue(followUp.getThyroidProfile().getTsh());
				fdataRow.createCell(14).setCellValue(followUp.getThyroidProfile().getFt4());
				fdataRow.createCell(15).setCellValue(followUp.getCeliacSerologyValue());
				fdataRow.createCell(16).setCellValue(String.valueOf(followUp.getLastVisitMultiClinicDate()));
				fdataRow.createCell(17).setCellValue(followUp.getInsulineDoseWritten());
				fdataRow.createCell(18).setCellValue(followUp.getTotDailyDoseInsulin());
				fdataRow.createCell(19).setCellValue(followUp.getBasalInsulineDose());
				fdataRow.createCell(20).setCellValue(followUp.getBolusInsuline());
			}
			
			for(int i = 0; i<=46; i++)
				sheet.autoSizeColumn(i);
			
			workbook.write(os);	
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
