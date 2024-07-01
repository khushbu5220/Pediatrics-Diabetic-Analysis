package com.aiims.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiims.pds.modals.LipidProfile;

public interface LipidProfileRepository extends JpaRepository<LipidProfile, Long> {

}
