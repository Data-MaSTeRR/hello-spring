package hello.hello_spring.controller;


import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Controller: 외부 요청 받기
@Controller
public class MemberController {

    private final MemberService memberService;

    // 스프링 컨테이너에서 연관된 객체를 찾아서 넣어준다. / 외부에서 의존관계 넣기 (DI)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
