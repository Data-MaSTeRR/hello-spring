package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello") // GET요청 /hello 도메인으로 들어왔을 때,
    public String hello(Model model) {
        model.addAttribute("data", "Eric!");
        return "hello"; // hello.html로 가서 렌더링 "View Resolver"
    }

    @GetMapping("hello-mvc") // GET요청 /hello-mvc 도메인으로 들어왔을 때,
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template"; // hello-template.html로 가서 렌더링 "View Resolver"
    }

}
