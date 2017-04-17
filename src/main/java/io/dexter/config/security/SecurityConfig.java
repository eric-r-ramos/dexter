package io.dexter.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



/**
 * 
 * Spring config WebSecurityConfigurerAdapter specialized for Dexter Application
 * Config the secutiry layer of application including the filters for requests and specific 
 * secutiry handlers
 * 
 * @author ericramos
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
    	return new RestAuthenticationEntryPoint();
    }

    
    @Bean
    public RestAuthenticationFailureHandler restAuthenticationFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }
    
    @Bean
    public RestLogoutSuccessHandler restLogoutSuccessHandler() {
        return new RestLogoutSuccessHandler();
    }
    
    @Bean
    public RestAuthenticationSuccessHandler restAuthenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler();
    }
    
	
    /**
     * use this method to configure security layes behavior 
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf()
        		.disable()
        		.exceptionHandling()
        		.authenticationEntryPoint(restAuthenticationEntryPoint())
        	.and()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/bower_components/**").permitAll()
                .antMatchers("/content/**").permitAll()
                .antMatchers("/app/js/**").permitAll()
                .antMatchers("/api/atm").authenticated()
                .antMatchers("/api/atm/listCities/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/user").permitAll()
            .and()
            	.formLogin()
            	.successHandler(restAuthenticationSuccessHandler())
            	.failureHandler(restAuthenticationFailureHandler())
        	.and()
        		.logout()
                .logoutSuccessHandler(restLogoutSuccessHandler())
                .permitAll()
        	.and()
                .httpBasic();
        
    }


    /**
     * use this method to configure user/pass of application
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    	//using auth.inMemory. If you want to improve the source of of user/pass, you should change it here
    	//consider use auth.ldapAuthentication() or auth.jdbcAuthentication() 

        auth.inMemoryAuthentication()
          .withUser("temporary").password("temporary").roles("ADMIN")
        .and()
          .withUser("user").password("userPass").roles("USER");
    }
    
}