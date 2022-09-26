package com.casestudy.springsecurity.configs;

import com.casestudy.springsecurity.service.CustomUserDetailsService;
import com.casestudy.springsecurity.util.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfigurer  {
    @Bean
    public static PasswordEncoder byCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers("/auth/login","/auth/register","/manage/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/hello").hasAuthority("ADMIN")
                .antMatchers("/admins/**").hasAuthority("ADMIN")
                .antMatchers("/users/**").hasAuthority("USER")
                .antMatchers("/orders/**").hasAuthority("ADMIN")
                .antMatchers("/washers/**").hasAuthority("WASHER")
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //.and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorisedEntryPoint());
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
