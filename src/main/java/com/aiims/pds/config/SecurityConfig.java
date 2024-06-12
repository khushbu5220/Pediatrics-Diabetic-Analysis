package com.aiims.pds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.aiims.pds.security.CustomUserDetailService;
import com.aiims.pds.security.JwtAuthenticationEntryPoint;
import com.aiims.pds.security.JwtAuthenticationFilter;
import com.aiims.pds.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String[] PUBLIC_URLS= {"/WEB-INF/**","/views/**","/login","/",
			"/admin-action/superadminregister",
			"/auth/**",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**",
			"/v3/apis/docs",
			"/v2/api-docs",
			"/chartAnalysis-action/**",
			"/system-services/remindersend",
			"/index",
			"/text"			
	};
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(PUBLIC_URLS).permitAll()
		.anyRequest()	
		.authenticated()
		.and()
		.formLogin().loginPage("/loginpage").loginProcessingUrl("/loginpageprocess").defaultSuccessUrl("/loginpage").and()
		.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().sessionManagement().invalidSessionUrl("/login?invalid-session=true")
		.and().csrf().disable();
		
		http
		.cors();
		
		http
		.httpBasic();
//		http.addFilterBefore(jwtAuthenticationFilter, AuthController.class);
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
//	reg_no, dept_reg_no, p_cat_id(1-opd, 2-ipd), dob, p_sex, dept_id, p_fname, p_mname, p_lname,

	
}
