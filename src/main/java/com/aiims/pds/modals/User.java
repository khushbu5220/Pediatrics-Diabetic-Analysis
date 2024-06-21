package com.aiims.pds.modals;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@Column(nullable=false)
	private String fullName;
	@Column(nullable=false,unique = true)
	private String contactNo;
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private String status;
	@Column(nullable=false)
	private Boolean enabled;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private Date registeredOn;
	private Date otpSentOn; 
	private int otp;
	@Column(nullable=false)
	private String role;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
	private User userAddedBy;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public String getUsername() {
		return contactNo;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
