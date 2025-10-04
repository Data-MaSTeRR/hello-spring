package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    // DI(의존성 주입)
    // 서비스에서 만들어진 것을 쓰는 것이 아니라, 내가 만들어서 쓴다.
    // 테스트 할 때, 새로운 객체를 만드는 것이 운영 메모리에 영향이 없다.
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("Eric");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("Eric");

        Member member2 = new Member();
        member2.setName("Eric");

        // when + then (예외를 기대해야 테스트가 통과)
        memberService.join(member1);
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> memberService.join(member2)
        );
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}