package com.aiims.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiims.pds.modals.FootExamination;

public interface CeliacSerologyRepository extends JpaRepository<FootExamination, Long> {

}
