package hello.springintroduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")//localhost8080 들어오면 home이 호출됨.
    public String home(){
        return "home";
    }
}
