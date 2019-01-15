package cn.strongme.bootssl.web;

import cn.strongme.bootssl.properties.BootSslProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2018-12-16 17:08.
 */

@Controller
@RequestMapping
@Slf4j
public class HomeController {

    @Value("${name}")
    private String name;

    private BootSslProperties bootSslProperties;

    public HomeController(BootSslProperties bootSslProperties) {
        this.bootSslProperties = bootSslProperties;
    }

    @GetMapping("")
    public String home(Model model) {
        log.info("timeout: {}s. size: {}MB", bootSslProperties.getTimeout().getSeconds(), bootSslProperties.getSize().toMegabytes());
        model.addAttribute("msg", "Boot Self:" + name);
        return "home";
    }


}
