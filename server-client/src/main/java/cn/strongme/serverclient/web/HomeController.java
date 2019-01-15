package cn.strongme.serverclient.web;

import cn.strongme.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-08 17:57.
 */

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @GetMapping("")
    public String home() {
        return "home";
    }

    @GetMapping("dashboard")
    public String dashboard(Model model) {
        oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("username", "admin");
        oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("password", "123");

        ResponseEntity<Result> result = oAuth2RestTemplate.getForEntity("http://localhost:8081/api", Result.class);
        model.addAttribute("data", result.getBody());
        return "dashboard";
    }

}
