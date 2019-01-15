package cn.strongme.bootssl;

import cn.strongme.bootssl.properties.BootSslProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
@EnableAdminServer
@EnableConfigurationProperties(BootSslProperties.class)
@Slf4j
public class BootsslApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(BootsslApplication.class)
                .listeners((ApplicationListener<ApplicationEvent>) event -> {
                    log.debug("EVENT:{}", event.toString());
                })
                .run();
    }


}

