package com.streamingplatform.moviestreamingplatform.config;

import com.streamingplatform.moviestreamingplatform.config.filters.CustomAuthenticationFilter;
import com.streamingplatform.moviestreamingplatform.config.filters.CustomAuthorizationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf()
            .disable();
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
            .antMatchers("/login/**", "/users/token/refresh/**")
            .permitAll()
            .antMatchers("/users/**/watchlist/**")
            .permitAll()
            .antMatchers(GET, "/users/**")
            .hasAnyAuthority("ROLE_ADMIN")
            .antMatchers(POST, "/users")
            .hasAnyAuthority("ROLE_ADMIN")
            .antMatchers(DELETE, "/users/**")
            .hasAnyAuthority("ROLE_ADMIN")
            .antMatchers(GET, "/movies/**")
            .permitAll()
            .antMatchers(POST, "/movies")
            .hasAnyAuthority("ROLE_ADMIN")
            .antMatchers(DELETE, "/movies/**")
            .hasAnyAuthority("ROLE_ADMIN")
            .anyRequest()
            .authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
