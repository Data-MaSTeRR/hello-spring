package hello.hello_spring.controller;


import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Controller: 외부 요청 받기
@Controller
public class MemberController {

    private final MemberService memberService;

    // 스프링 컨테이너에서 연관된 객체를 찾아서 넣어준다. / 외부에서 의존관계 넣기 (DI)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 해당 URL에서, POST 요청에 대한 로직
    // => MemberForm 객체로 입력값이 들어옴
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/"; // 회원 등록 후, 홈화면으로 리다이렉트
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // View로 members 넘기기
        return "members/memberList";
    }

}
