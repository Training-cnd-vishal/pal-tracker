package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${welcome.message}")
    private final String msg;

    public WelcomeController( @Value("${welcome.message}") String s)
    {
            this.msg=s;
    }

    @GetMapping("/")
    public String sayHello() {
        return msg;
    }
}