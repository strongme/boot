package cn.strongme.bootssl.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.Duration;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2018-12-19 22:37.
 */

@Data
@ConfigurationProperties(prefix = "bootssl")
@Validated
public class BootSslProperties {

    /**
     * 炸弹超时时间
     */
    @NotNull(message = "炸弹超时时间必须配置")
    private Duration timeout;

    /**
     * 炸弹能量
     */
    private DataSize size = DataSize.ofGigabytes(1024);

}
