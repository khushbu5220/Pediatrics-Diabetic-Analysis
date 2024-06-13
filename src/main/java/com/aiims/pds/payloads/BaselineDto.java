package com.aiims.pds.payloads;

import java.time.LocalDate;
import java.util.List;

import com.aiims.pds.modals.FamilyHistory;
import com.aiims.pds.modals.FollowUp;
import com.aiims.pds.modals.Investigation;
import com.aiims.pds.modals.SocioeconomicHistory;
import com.aiims.pds.modals.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaselineDto 
{
	private Long id;
	private String name;
	private String diagnosis;
	private String gender;
	private int age;
	private LocalDate dob;
	private Long uhid;
	private String mName;
	private String fName;
	private String contactNo;
	private String address;
	private int dAge;
	private LocalDate dMonthYear;
	private int fuAge;
	private LocalDate fuMonthYear;
	private String polyuriaPolydipsia;
	private String polyDuration;
	private String weightLoss;
	private String weightlossDuration;
	private String dkaAtDiagnosis;
	private User user;
	private FamilyHistory familyHistory;
	private SocioeconomicHistory socioeconomicHistory;
	private Investigation investigation;
	private List<FollowUp> followUps;
}
