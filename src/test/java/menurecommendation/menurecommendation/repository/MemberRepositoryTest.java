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
}