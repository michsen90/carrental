package com.app.car.rental.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserPrincipalDetailsService userPrincipalDetailsService;

    public SecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService){
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
                /**.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin123"))
                .authorities("ACCESS_TEST_1", "ACCESS_TEST_2", "ROLE_ADMIN")
                .and()
                .withUser("michal").password(passwordEncoder().encode("michal123"))
                .authorities("ACCESS_TEST_1", "ROLE_USER");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/clients/**").hasRole("ADMIN")
                .antMatchers("/cars/**").hasRole("ADMIN")
                .antMatchers("/booking/**").hasRole("ADMIN")
                .antMatchers( "/basic").hasAnyRole("ADMIN", "USER")
                .antMatchers("/basic/getUser/**").permitAll()
                .anyRequest()
                .authenticated().and().httpBasic();

        http.csrf().disable();


        /** .antMatchers( "/test/all").permitAll()
         .antMatchers("/test/**").hasRole("ADMIN")
         .antMatchers("/test/user").hasAnyRole("ADMIN", "USER")
         .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST_1")
         .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST_2")
         .antMatchers(HttpMethod.OPTIONS,"/api/public/users").hasRole("ADMIN")
         .antMatchers(HttpMethod.OPTIONS,"/api/public/clients").hasRole("ADMIN")*/


    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
