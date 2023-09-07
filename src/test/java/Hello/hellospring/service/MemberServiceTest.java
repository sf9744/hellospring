package Hello.hellospring.service;

import Hello.hellospring.domain.Member;
import Hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //상황
        Member member = new Member();
        member.setName("spring");
        //실행
        Long saveId = memberService.join(member);
        //결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //상황
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //실행
        memberService.join(member1);
//        memberService.join(member2);
        //assertThrows(입셉션 에러 종류 a, 발생하는 로직 b) :
        //b 로직시에 a 입셉션이 발생하는지 확인
       IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        //결과
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}
