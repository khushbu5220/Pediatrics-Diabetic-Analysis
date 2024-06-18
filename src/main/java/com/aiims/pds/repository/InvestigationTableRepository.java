package com.aiims.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiims.pds.modals.InvestigationTable;

public interface InvestigationTableRepository extends JpaRepository<InvestigationTable, Long> 
{

}
