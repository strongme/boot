package cn.strongme.bootssl.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.unit.DataSize;

import java.time.Duration;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2018-12-19 22:45.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimerBomb {

    /**
     * 炸弹超时时间
     */
    private Duration timeout;

    /**
     * 炸弹能量
     */
    private DataSize size;
}
