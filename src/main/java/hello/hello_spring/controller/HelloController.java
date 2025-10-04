package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody // 따로 html을 내려주는 것이 아니라, return 값을 api로 응답 (String => stringConverter)
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // 따로 html을 내려주는 것이 아니라, return 값을 api로 응답 (객체 => jsonConverter)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    public static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
