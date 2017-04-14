package io.dexter.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    
    @Bean
    public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
        return new AjaxAuthenticationFailureHandler();
    }
    
    @Bean
    public AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler() {
        return new AjaxLogoutSuccessHandler();
    }
    
    @Bean
    public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
        return new AjaxAuthenticationSuccessHandler();
    }
    
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf()
        		.disable()
        		.exceptionHandling()
        		.authenticationEntryPoint(restAuthenticationEntryPoint)
        	.and()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/bower_components/**").permitAll()
                .antMatchers("/content/**").permitAll()
                .antMatchers("/app/js/**").permitAll()
                .antMatchers("/api/atm/**").authenticated()
                .antMatchers("/api/city/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/user").permitAll()
            .and()
            	.formLogin()
            	.successHandler(ajaxAuthenticationSuccessHandler())
            	.failureHandler(ajaxAuthenticationFailureHandler())
        	.and()
        		.logout()
                .logoutSuccessHandler(ajaxLogoutSuccessHandler())
                .permitAll()
        	.and()
                .httpBasic();
        
    }


    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
  
        auth.inMemoryAuthentication()
          .withUser("temporary").password("temporary").roles("ADMIN")
        .and()
          .withUser("user").password("userPass").roles("USER");
    }
    
}