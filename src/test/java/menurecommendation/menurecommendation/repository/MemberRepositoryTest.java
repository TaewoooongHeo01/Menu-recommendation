package menurecommendation.menurecommendation.repository;

import menurecommendation.menurecommendation.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 조회")
    @Transactional
    void findOne() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");
        memberRepository.save(member);

        //when
        Member findMember = memberRepository.findOne(member.getId());

        //then
        assertThat(member.getUsername()).isEqualTo(findMember.getUsername());
    }

    @Test
    @DisplayName("전체 회원 조회")
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
    @Transactional
    void login() throws Exception {
        //given
        Member member = new Member();
        member.setEmail("123@123");
        member.setPasswd("123");
        memberRepository.save(member);

        //when
        Member loginMember = memberRepository.login(member.getEmail(), member.getPasswd());

        //then
        assertThat(loginMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(loginMember.getPasswd()).isEqualTo(member.getPasswd());
    }

    @Test
    @Transactional
    @DisplayName("이메일 중복 체크 -> 사용가능")
    void emailCheckPossible() throws Exception {
        //given
        Member member = new Member();
        member.setEmail("123@123");

        //when
        Member findMember = memberRepository.emailCheck(member.getEmail());

        //then
        assertThat(findMember).isEqualTo(null);
    }

    @Test
    @Transactional
    @DisplayName("이메일 중복 체크 -> 사용 불가")
    void emailCheckNotPossible() throws Exception {
        //given
        Member memberA = new Member();
        memberA.setEmail("123@123");
        memberRepository.save(memberA);
        Member memberB = new Member();
        memberB.setEmail("123@123");

        //when
        Member findMember = memberRepository.emailCheck(memberB.getEmail());

        //then
        assertThat(findMember).isEqualTo(memberA);
    }
}