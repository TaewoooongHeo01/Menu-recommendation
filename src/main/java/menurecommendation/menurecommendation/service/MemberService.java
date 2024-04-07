package menurecommendation.menurecommendation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import menurecommendation.menurecommendation.domain.Ingredient;
import menurecommendation.menurecommendation.domain.Member;
import menurecommendation.menurecommendation.domain.MemberIngredient;
import menurecommendation.menurecommendation.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void addIngredient(Long memberId, Ingredient ingredient) {
        Member findMember = memberRepository.findOne(memberId);
        MemberIngredient memberIngredient = ingredient.conversionMemberIngredient();
        findMember.addIngredient(memberIngredient);
        memberIngredient.setMember(findMember);
    }

    public Member login(String email, String passwd) {
        Member findMember = memberRepository.login(email, passwd);
        if(findMember == null) {
            return null;
        }
//        session.setAttribute("member", String.valueOf(findMember.getId()));
        return findMember;
    }

    public boolean emailCheck(String email) {
        Member findMember = memberRepository.emailCheck(email);
        log.info("email check member: "+findMember);
        if(findMember == null) {
            return true;
        }
        return false;
    }

    public String memberToJson(Member member) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(member);
    }
}
