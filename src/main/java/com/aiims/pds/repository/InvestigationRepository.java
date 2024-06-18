package com.aiims.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiims.pds.modals.Investigation;

public interface InvestigationRepository extends JpaRepository<Investigation, Long> 
{

}
