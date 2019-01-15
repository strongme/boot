package cn.strongme.bootsslclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2018-12-27 17:54.
 */

@Controller
public class HomeController {

    @GetMapping("")
    public String home() {
        return "home";
    }

}
