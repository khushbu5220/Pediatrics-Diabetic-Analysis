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
import com.aiims.pds.modals.HbA1cTable;
import com.aiims.pds.modals.Investigation;
import com.aiims.pds.modals.InvestigationTable;
import com.aiims.pds.modals.SocioeconomicHistory;
import com.aiims.pds.modals.User;
import com.aiims.pds.payloads.BaselineDto;
import com.aiims.pds.payloads.FamilyHistoryDto;
import com.aiims.pds.payloads.SocioeconomicHistoryDto;
import com.aiims.pds.repository.BaselineRepository;
import com.aiims.pds.repository.FamilyhistoryRepository;
import com.aiims.pds.repository.HbA1cTableRepository;
import com.aiims.pds.repository.InvestigationRepository;
import com.aiims.pds.repository.InvestigationTableRepository;
import com.aiims.pds.repository.SocioeconomicHistoryRepository;
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
				.dAge(baselineDto.getDAge())
				.diagnosis(baselineDto.getDiagnosis())
				.dkaAtDiagnosis(baselineDto.getDkaAtDiagnosis())
				.dMonthYear(baselineDto.getDMonthYear())
				.dob(baselineDto.getDob())
				.fName(baselineDto.getFName())
				.fuAge(baselineDto.getFuAge())
				.fuMonthYear(baselineDto.getFuMonthYear())
				.gender(baselineDto.getGender())
				.status(AppConstants.ACTIVE_USER_STATUS)
				.mName(baselineDto.getMName())
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
				.hBirth(familyHistoryDto.getHBirth())
				.hBirthRemarks(familyHistoryDto.getHBirthRemarks())
				.hDevelopment(familyHistoryDto.getHDevelopment())
				.hDevelopmentRemarks(familyHistoryDto.getHDevelopmentRemarks())
				.hDiabetesFamily(familyHistoryDto.getHDiabetesFamily())
				.hDiabetesMGrandparents(familyHistoryDto.getHDiabetesMGrandparents())
				.hDiabetesParents(familyHistoryDto.getHDiabetesParents())
				.hDiabetesPGrandparents(familyHistoryDto.getHDiabetesPGrandparents())
				.hImmunisation(familyHistoryDto.getHImmunisation())
				.hImmunisationRemarks(familyHistoryDto.getHImmunisationRemarks())
				.build();
	
		var baseline = Baseline.builder()
		.familyHistory(this.familyhistoryRepository.save(familyHistory))
		.id(baselineId)
		.build();
		return this.modelMapper.map(this.baselineRepository.save(baseline), BaselineDto.class);
	}

	@Override
	public BaselineDto createSocioeconomicHistory(Principal principal, Long baselineId,
			SocioeconomicHistoryDto socioeconomicHistoryDto) 
	{
		var socioeconomicHistory = SocioeconomicHistory.builder()
				.abhaHolder(socioeconomicHistoryDto.getAbhaHolder())
				.bplCardHolder(socioeconomicHistoryDto.getBplCardHolder())
				.cghsGovtscheme(socioeconomicHistoryDto.getCghsGovtscheme())
				.familyMemberCount(socioeconomicHistoryDto.getFamilyMemberCount())
				.familyType(socioeconomicHistoryDto.getFamilyType())
				.fIncome(socioeconomicHistoryDto.getFIncome())
				.fOccupation(socioeconomicHistoryDto.getFOccupation())
				.gIncome(socioeconomicHistoryDto.getGIncome())
				.mIncome(socioeconomicHistoryDto.getMIncome())
				.mOccupation(socioeconomicHistoryDto.getMOccupation())
				.remarks(socioeconomicHistoryDto.getRemarks())
				.build();
		
		Baseline baseline = Baseline.builder()
				.socioeconomicHistory(this.socioeconomicHistoryRepository.save(socioeconomicHistory))
				.id(baselineId)
				.build();
			
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
	public BaselineDto createFollowUP(Principal principal, Long baselineId, FollowUp followUp) {
		// TODO Auto-generated method stub
		return null;
	}

}
