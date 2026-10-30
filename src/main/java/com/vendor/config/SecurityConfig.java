package com.vendor.config;

import com.vendor.entity.PatientEntity;
import com.vendor.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService userDetailsService(PatientRepository patients, PasswordEncoder encoder,
            @Value("${app.admin.username:admin}") String adminUsername,
            @Value("${app.admin.password:}") String adminPassword) {
        if (adminPassword.length() < 12) {
            throw new IllegalStateException("Set ADMIN_PASSWORD to a secret with at least 12 characters before starting CareCover.");
        }
        String adminPasswordHash = encoder.encode(adminPassword);
        return username -> {
            if (username.equalsIgnoreCase(adminUsername)) {
                return User.withUsername(adminUsername).password(adminPasswordHash).roles("ADMIN").build();
            }
            PatientEntity patient = patients.findByEmailIgnoreCase(username.trim())
                    .filter(PatientEntity::isPortalEnabled)
                    .filter(p -> p.getPasswordHash() != null && !p.getPasswordHash().isBlank())
                    .orElseThrow(() -> new UsernameNotFoundException("Account not found"));
            return User.withUsername(patient.getEmail()).password(patient.getPasswordHash()).roles("PATIENT").build();
        };
    }

    @Bean
    AuthenticationSuccessHandler roleBasedLoginRedirect() {
        return (request, response, authentication) -> {
            boolean patient = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PATIENT"));
            response.sendRedirect(request.getContextPath() + (patient ? "/portal" : "/"));
        };
    }

    @Bean
    SecurityFilterChain appSecurity(HttpSecurity http, AuthenticationSuccessHandler roleBasedLoginRedirect) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/images/**", "/error").permitAll()
                .requestMatchers("/portal/**").hasRole("PATIENT")
                .anyRequest().hasRole("ADMIN"))
            .formLogin(form -> form.loginPage("/login").successHandler(roleBasedLoginRedirect).permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/login?logout").invalidateHttpSession(true).clearAuthentication(true))
            .exceptionHandling(errors -> errors.accessDeniedPage("/login?denied"));
        return http.build();
    }
}
