package boun.cmpe451.group9.Security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .httpBasic()
        .and()
            .logout()
        .and()
            .authorizeRequests()
                //.antMatchers("/").permitAll()
                .anyRequest().permitAll()
        .and()
            .csrf().disable();//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
