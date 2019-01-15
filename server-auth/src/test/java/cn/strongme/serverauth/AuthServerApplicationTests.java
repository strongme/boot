package cn.strongme.serverauth;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class AuthServerApplicationTests {


    @Test
    public void contextLoads() throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        log.info(passwordEncoder.encode("123"));
    }

}

