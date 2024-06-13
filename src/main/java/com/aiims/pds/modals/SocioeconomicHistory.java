package com.aiims.pds.modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_socioeconomicHistory")
public class SocioeconomicHistory 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fOccupation;
	private Long fIncome;
	private String mOccupation;
	private Long mIncome;
	private Long gIncome;
	private String familyType;
	private int familyMemberCount;
	private String bplCardHolder;
	private String abhaHolder;
	private String cghsGovtscheme;
	private String remarks;
}
