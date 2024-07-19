package com.aiims.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiims.pds.modals.Baseline;
import com.aiims.pds.modals.FollowUp;
import com.aiims.pds.modals.User;

public interface BaselineRepository extends JpaRepository<Baseline, Long> 
{

	List<Baseline> findByUser(User user);

	Baseline findByFollowUps(FollowUp followUp);

}
