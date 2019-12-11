package FoodHero.Config;

import FoodHero.service.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfigAuth extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginDetailsService loginDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(loginDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
       /* httpSecurity
                .authorizeRequests()
                .antMatchers("/account/").hasAuthority("USER")
                .antMatchers("/").hasAuthority("ADMIN")
                .and()
                .formLogin();
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().migrateSession();*/
    }
}
