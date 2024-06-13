package com.aiims.pds.modals;

import java.time.LocalDate;

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
@Table(name="tbl_hba1ctable")
public class HbA1cTable 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private LocalDate date;
	private String hba1c;
//	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
//	private Baseline baseline;
}
