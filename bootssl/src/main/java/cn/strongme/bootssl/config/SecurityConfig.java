package cn.strongme.bootssl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2018-12-29 10:09.
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers("/")
                .permitAll()
            .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
