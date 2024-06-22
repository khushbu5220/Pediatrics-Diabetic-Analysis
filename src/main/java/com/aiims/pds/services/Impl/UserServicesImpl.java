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
				.user(this.userRepository.findByContactNo(username).orElseThrow(() -> new ResourceNotFoundException("Username", "ContactNo", username)))
				.weightLoss(baselineDto.getWeightLoss())
				.weightlossDuration(baselineDto.getWeightlossDuration())
				.build();
				
		return this.modelMapper.map(this.baselineRepository.save(baseline), BaselineDto.class);
	}

	@Override
	public BaselineDto createFamilyHistory(Principal principal, Long baselineId, FamilyHistory familyHistory) {
		FamilyHistory savedFamilyHistory = this.familyhistoryRepository.save(familyHistory);
		Baseline baseline = this.baselineRepository.getOne(baselineId);
		baseline.setFamilyHistory(savedFamilyHistory);
		Baseline b = this.baselineRepository.save(baseline);
		return this.modelMapper.map(b, BaselineDto.class);
	}

	@Override
	public BaselineDto createSocioeconomicHistory(Principal principal, Long baselineId,
			SocioeconomicHistory socioeconomicHistory) {
		SocioeconomicHistory savedSocioeconomicHistory = this.socioeconomicHistoryRepository.save(socioeconomicHistory);
		Baseline baseline = this.baselineRepository.getOne(baselineId);
		baseline.setSocioeconomicHistory(savedSocioeconomicHistory);
		Baseline b = this.baselineRepository.save(baseline);
		return this.modelMapper.map(b, BaselineDto.class);
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
