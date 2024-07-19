package com.aiims.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiims.pds.modals.CeliacSerology;

public interface CeliacSerologyRepository extends JpaRepository<CeliacSerology, Long> {

}
