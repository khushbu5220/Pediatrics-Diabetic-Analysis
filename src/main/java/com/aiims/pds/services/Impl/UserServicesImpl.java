package com.aiims.pds.services.Impl;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiims.pds.exceptions.ResourceNotFoundException;
import com.aiims.pds.modals.Baseline;
import com.aiims.pds.modals.FamilyHistory;
import com.aiims.pds.modals.User;
import com.aiims.pds.payloads.BaselineDto;
import com.aiims.pds.payloads.FamilyHistoryDto;
import com.aiims.pds.repository.BaselineRepository;
import com.aiims.pds.repository.FamilyhistoryRepository;
import com.aiims.pds.repository.UserRepository;
import com.aiims.pds.services.UserServices;

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
	private ModelMapper modelMapper;
	
	@Override
	public BaselineDto createBaseline(Principal principal, Baseline baseline) 
	{
		String username = principal.getName();
		User user = this.userRepository.findByContactNo(username).orElseThrow(() -> new ResourceNotFoundException("Username", "ContactNo", username));
		baseline.setUser(user);
		Baseline b = this.baselineRepository.save(baseline);
		return this.modelMapper.map(b, BaselineDto.class);
	}

	@Override
	public FamilyHistoryDto createFamilyHistory(Principal principal, FamilyHistory familyHistory) {
		String username = principal.getName();
		User user = this.userRepository.findByContactNo(username).orElseThrow(() -> new ResourceNotFoundException("Username", "ContactNo", username));
		FamilyHistory savedFamilyHistory = this.familyhistoryRepository.save(familyHistory);
		
		return null;
	}

}
