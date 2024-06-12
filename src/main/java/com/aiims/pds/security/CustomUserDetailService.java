package com.aiims.pds.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aiims.pds.config.AppConstants;
import com.aiims.pds.exceptions.ApiException;
import com.aiims.pds.exceptions.ResourceNotFoundException;
import com.aiims.pds.modals.User;
import com.aiims.pds.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user form database by user name
		User user = this.userRepository.findByContactNo(username).orElseThrow(()-> new ResourceNotFoundException("User", "Employee Id : "+username,0));
		if(user.getStatus().equalsIgnoreCase("NEW")) {
			user.setStatus(AppConstants.ACTIVE_USER_STATUS);
			this.userRepository.save(user);
		}
		if(user.getEnabled().equals(true)) {
			return user;			
		}else {
			throw new ApiException("Invalid username or password. Contact to your Admin!!");
		}
		
	}
}
