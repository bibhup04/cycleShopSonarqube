// package com.talentsprint.cycleshop;

// import static org.springframework.security.config.Customizer.withDefaults;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// import com.talentsprint.cycleshop.service.CustomUserDetailsService;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration {

// @Autowired
// private CustomUserDetailsService customUserDetailsService;

// @Autowired
// public void configureGlobal(AuthenticationManagerBuilder auth) throws
// Exception {
// auth.userDetailsService(customUserDetailsService);
// }

// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http
// .authorizeHttpRequests((requests) -> requests
// .requestMatchers("/register").permitAll()
// .anyRequest().authenticated())
// .logout(withDefaults())
// .formLogin((login) ->
// login.loginProcessingUrl("/login").defaultSuccessUrl("/cycle/list", true));

// return http.build();
// }

// }
