package menurecommendation.menurecommendation.service;

import menurecommendation.menurecommendation.domain.Ingredient;
import menurecommendation.menurecommendation.domain.Member;
import menurecommendation.menurecommendation.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("MemberService Test")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 조회")
    @Transactional
    void findOne() throws Exception {
        //given
        Member member = new Member();
        memberService.join(member);

        //when
        Member findMember = memberService.findOne(member.getId());

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("회원 전체 조회")
    @Transactional
    void findAll() throws Exception {
        //given
        Member memberA = new Member();
        Member memberB = new Member();
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        //when
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("재료 추가")
    @Transactional
    void addIngredient() throws Exception {
        //given
        Member member = new Member();
        Ingredient ingredientA = new Ingredient();
        Ingredient ingredientB = new Ingredient();

        //when
        memberService.join(member);
        memberService.addIngredient(member.getId(), ingredientA);
        memberService.addIngredient(member.getId(), ingredientB);
        Member findMember = memberService.findOne(member.getId());

        //then
        assertThat(findMember.getMemberIngredients().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @DisplayName("이메일 중복 체크 -> 사용가능")
    void emailCheckPossible() throws Exception {
        //given
        Member member = new Member();
        member.setEmail("123@123");

        //when
        boolean check = memberService.emailCheck(member.getEmail());

        //then
        assertThat(check).isTrue();
    }

    @Test
    @Transactional
    @DisplayName("이메일 중복 체크 -> 사용 불가")
    void emailCheckNotPossible() throws Exception {
        //given
        Member memberB = new Member();
        memberB.setEmail("123@123");
        memberService.join(memberB);
        Member memberA = new Member();
        memberA.setEmail("123@123");

        //when
        boolean check = memberService.emailCheck(memberA.getEmail());

        //then
        assertThat(check).isFalse();
    }
}