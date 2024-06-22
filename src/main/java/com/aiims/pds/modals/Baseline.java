package com.aiims.pds.modals;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_baseline")
public class Baseline 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String diagnosis;
	private String gender;
	private int age;
	private LocalDate dob;
	@Column(nullable=false,unique = true)
	private Long uhid;
	private String mName;
	private String fName;
	@Column(nullable=false,unique = true)
	private String contactNo;
	private String address;
	private int dAge;
	private String dMonthYear;
	private int fuAge;
	private String fuMonthYear;
	private String polyuriaPolydipsia;
	private String polyDuration;
	private String weightLoss;
	private String weightlossDuration;
	private String dkaAtDiagnosis;
	private User user;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private FamilyHistory familyHistory;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private SocioeconomicHistory socioeconomicHistory;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private Investigation investigation;
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private List<FollowUp> followUps;
	private String status;
	private Date cdt;
}
