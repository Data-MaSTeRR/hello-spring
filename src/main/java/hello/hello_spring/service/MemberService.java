package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    // 다형성을 만족시키는 객체, 직접 접근 못하고, 한번 넣으면 수정못하게 함
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // @ 회원 가입
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름의 중복회원 안된다는 로직
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // @ 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // @ 단일 회원 id로 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
