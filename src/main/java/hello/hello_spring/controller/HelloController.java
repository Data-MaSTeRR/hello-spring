package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // GET요청 /hello 도메인으로 들어왔을 때,
    public String hello(Model model) {
        model.addAttribute("data", "Eric!");
        return "hello"; // hello.html로 가서 렌더링 "View Resolver"
    }

}
