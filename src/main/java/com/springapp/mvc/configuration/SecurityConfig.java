package com.springapp.mvc.configuration;

import com.springapp.mvc.configuration.security.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Иван on 09.11.2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //Todo make config in database
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("superadmin").password("superadmin").roles("SUPERADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/VAADIN/**").antMatchers("/vaadin/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/protected").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/confidential").access("hasRole('ROLE_SUPERADMIN')")
                .and().formLogin().loginPage("/login").successHandler(new CustomLoginSuccessHandler("/security"))
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout.done").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).and().csrf().and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}
