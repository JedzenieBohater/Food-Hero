package FoodHero.Config;

import FoodHero.service.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
    public AuthenticationFailureHandler AuthFailHandler() {
        return new AuthFailHandler();
    }

    @Bean
    public AuthenticationSuccessHandler AuthSuccessHandler() {
        return new AuthSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler AccDeniedHandler() {
        return new AccDeniedHandler();
    }

    @Bean
    public LogoutSuccessHandler LogoutSuccHandler(){
        return new LogoutSuccHandler();
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
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .failureHandler(AuthFailHandler())
                    .successHandler(AuthSuccessHandler())
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .permitAll()
                .and()
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
