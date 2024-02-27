package menurecommendation.menurecommendation.service;

import lombok.RequiredArgsConstructor;
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
}
