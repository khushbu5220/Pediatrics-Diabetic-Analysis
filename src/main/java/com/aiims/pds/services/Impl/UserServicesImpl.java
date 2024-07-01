package com.aiims.pds.services.Impl;

import java.security.Principal;
import java.util.Date;
import java.util.List;

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
				.status(AppConstants.ACTIVE_USER_STATUS)
				.mName(baselineDto.getmName())
				.name(baselineDto.getName())
				.polyDuration(baselineDto.getPolyDuration())
				.polyuriaPolydipsia(baselineDto.getPolyuriaPolydipsia())
				.uhid(baselineDto.getUhid())
				.user(user)
				.weightLoss(baselineDto.getWeightLoss())
				.weightlossDuration(baselineDto.getWeightlossDuration())
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
				.hDiabetesMGrandparents(familyHistoryDto.gethDiabetesMGrandparents())
				.hDiabetesParents(familyHistoryDto.gethDiabetesParents())
				.hDiabetesPGrandparents(familyHistoryDto.gethDiabetesPGrandparents())
				.hImmunisation(familyHistoryDto.gethImmunisation())
				.hImmunisationRemarks(familyHistoryDto.gethImmunisationRemarks())
				.build();
		Baseline baseline = this.baselineRepository.findById(baselineId).orElseThrow(() -> new ResourceNotFoundException("Baseline", "BaselineId", baselineId));
		System.out.println(familyHistoryDto.toString());
//		FamilyHistory familyHistory = this.modelMapper.map(familyHistoryDto, FamilyHistory.class);
//		FamilyHistory savedFamilyHistory = ;
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

}
