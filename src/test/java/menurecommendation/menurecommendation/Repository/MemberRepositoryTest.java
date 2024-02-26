package menurecommendation.menurecommendation.Repository;

import menurecommendation.menurecommendation.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("회원 이름 조회")
    @Transactional
    void findByName() throws Exception {
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();
        member1.setUsername("memberA");
        member2.setUsername("memberA");
        member3.setUsername("memberB");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //when
        List<Member> members = memberRepository.findByName("memberA");

        //then
        assertThat(members.size()).isEqualTo(2);
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
}