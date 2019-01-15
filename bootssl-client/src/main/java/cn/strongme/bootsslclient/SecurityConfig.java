package cn.strongme.bootsslclient;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2018-12-27 17:43.
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable()
        ;
    }
}
