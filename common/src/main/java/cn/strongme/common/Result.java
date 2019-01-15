package cn.strongme.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-10 10:46.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private HttpStatus code;
    private String msg;
    private T data;

}
