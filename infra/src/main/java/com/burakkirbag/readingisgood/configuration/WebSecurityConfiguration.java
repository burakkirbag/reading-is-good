package com.burakkirbag.readingisgood.configuration;

import com.burakkirbag.readingisgood.security.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfiguration {
    private final JwtAuthFilter jwtAuthFilter;

    public WebSecurityConfiguration(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/swagger-ui/**", "/v2/api-docs/**").permitAll()
                .and().authorizeRequests().antMatchers("/api/v1/auth").permitAll()
                .and().authorizeRequests().antMatchers("/api/v1/book").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/order/**").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/order").authenticated()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/customer").permitAll()
                .and().authorizeRequests().antMatchers("/api/v1/customer/**").authenticated()
                .and().authorizeRequests().antMatchers("/api/v1/statistic/**").authenticated()
                .and() .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
