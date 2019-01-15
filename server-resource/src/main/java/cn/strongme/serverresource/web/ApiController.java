package cn.strongme.serverresource.web;

import cn.strongme.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-09 13:41.
 */

@RestController
@Slf4j
public class ApiController {

    @GetMapping("api")
    public Result<String> api(Authentication authentication) {
        log.info(authentication.getName());
        return new Result<>(HttpStatus.OK, "调用成功", "我是"+authentication.getName());
    }

}
