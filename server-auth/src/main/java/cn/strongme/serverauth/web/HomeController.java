package cn.strongme.serverauth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-08 17:57.
 */

@Controller
public class HomeController {

    @GetMapping("")
    public String home() {
        return "home";
    }

}
