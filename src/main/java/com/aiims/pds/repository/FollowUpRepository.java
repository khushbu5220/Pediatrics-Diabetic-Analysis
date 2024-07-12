package com.aiims.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiims.pds.modals.FollowUp;

public interface FollowUpRepository extends JpaRepository<FollowUp, Long>
{
	
}
