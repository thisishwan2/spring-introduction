package hello.springintroduction.service;

import hello.springintroduction.domain.Member;
import hello.springintroduction.repository.MemberRepository;
import hello.springintroduction.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest//스프링이 테스트 할때
@Transactional//데이터베이스는 트랜잭션이라는 개념이 있다. 이 어노테이션을 테스트에 걸어놓으면 테스트를 실행할때 트랜잭션션을 먼저 실행하고,디비 데이터를 인서트 퀴리하고 테스트 끝나면 롤백함.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test//테스트 코드는 과감하게 한글로 적어도 된다.
    void  회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);//MemberService의 join메서드

        //then
        Member findMember = memberService.findOne(saveId).get(); //Optional 타입이므로 get메서드로 꺼냄
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}