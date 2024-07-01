package com.aiims.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiims.pds.modals.ThyroidProfile;

public interface ThyroidProfileRepository extends JpaRepository<ThyroidProfile, Long> {

}
