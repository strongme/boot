package cn.strongme.serverresource.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-09 10:46.
 */


@RestController
public class HomeController {

    @GetMapping("")
    public String home() {
        return "home";
    }

}
