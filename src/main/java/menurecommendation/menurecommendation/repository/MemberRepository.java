package menurecommendation.menurecommendation.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Member findByEmail(String email) {
//        return em.createQuery("select m from Member m", Member.class)
        return new Member();
    }
}
