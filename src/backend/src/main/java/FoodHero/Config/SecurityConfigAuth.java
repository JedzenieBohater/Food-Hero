package FoodHero.Config;

import FoodHero.service.LoginDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfigAuth extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginDetailsService loginDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(loginDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler AccDeniedHandler() {
        return new AccDeniedHandler();
    }

    @Bean
    public LogoutSuccessHandler LogoutSuccHandler(){
        return new LogoutSuccHandler();
    }


    @Bean
    public RestAuthenticationSuccessHandler SuccessAuthHandler()
    {
        return new RestAuthenticationSuccessHandler();
    }

    @Bean
    public RestAuthenticationFailureHandler FailureAuthHandler()
    {
        return new RestAuthenticationFailureHandler();
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(SuccessAuthHandler());
        filter.setAuthenticationFailureHandler(FailureAuthHandler());
        filter.setAuthenticationManager(super.authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().migrateSession();

        httpSecurity
                .authorizeRequests()
                    .antMatchers("/account/").hasAuthority("USER")
                    .antMatchers("/account/*").hasAuthority("ADMIN")
                    .antMatchers("/login*").hasAuthority("ADMIN")
                    .antMatchers("/login/status").hasAuthority("USER")
                    .antMatchers("/login").permitAll()
                .and()
                    .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                .and()
                    .exceptionHandling().accessDeniedHandler(AccDeniedHandler())
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                //.httpBasic()
                //.and()
                .csrf().disable()
                .logout()
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .logoutSuccessHandler(LogoutSuccHandler());
    }
}
