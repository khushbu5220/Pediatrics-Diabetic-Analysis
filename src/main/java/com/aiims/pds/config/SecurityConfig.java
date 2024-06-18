package com.aiims.pds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.aiims.pds.security.CustomUserDetailService;
import com.aiims.pds.security.JwtAuthenticationEntryPoint;
import com.aiims.pds.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String[] PUBLIC_URLS = {
//            "/WEB-INF/**", "/views/**", "/login",
//            "/admin-action/superadminregister",
            "/auth/**"
//            "/swagger-resources/**",
//            "/swagger-ui/**", "/webjars/**",
//            "/v3/apis/docs", "/v2/api-docs",
//            "/chartAnalysis-action/**",
//            "/system-services/remindersend",
//            "/index", "/text"
    };

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	 http.csrf().disable()
         .authorizeHttpRequests()
             .requestMatchers("/auth/**").permitAll()
             .anyRequest().authenticated();
//             .and()
//             .formLogin();
         
//         .sessionManagement(session -> session
//             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//         );

//     http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    	
    	//        http
//            .csrf().disable()
//            .authorizeHttpRequests((requests) -> requests
//            		.requestMatchers(PUBLIC_URLS).permitAll()
//            		.anyRequest().authenticated())
//            .formLogin((form) -> form.loginPage("/login")
//            		.permitAll()
//            		.defaultSuccessUrl("/index", true))
//            .logout((logout) -> logout.permitAll());
            
            
//            .requestMatchers(PUBLIC_URLS).permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin().loginPage("/loginpage").loginProcessingUrl("/loginpageprocess").defaultSuccessUrl("/loginpage").and()
//            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and().sessionManagement().invalidSessionUrl("/login?invalid-session=true")
//            .and().csrf().disable();

//        http.cors();
//
//        http.httpBasic();

//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//    	UserDetails userDetails = User.withUsername("9013882267")
//    			.password("Aiims@123").build();
//    
//    	InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(userDetails);
//    	return inMemoryUserDetailsManager;
//    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//    }
}
