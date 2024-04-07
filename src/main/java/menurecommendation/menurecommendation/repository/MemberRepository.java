package menurecommendation.menurecommendation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import menurecommendation.menurecommendation.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
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

    public Member login(String email, String passwd) {
        try {
            return em.createQuery("SELECT m FROM Member m where m.email = :email AND m.passwd = :passwd", Member.class)
                    .setParameter("email", email)
                    .setParameter("passwd", passwd)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Member emailCheck(String email) {
        log.info("email: " + email);
        try {
            Member findMember = em.createQuery("SELECT m FROM Member m where m.email = :email", Member.class)
                    .setParameter("email", email)
                    .getSingleResult();
            System.out.println(findMember);
            return findMember;
        } catch (NoResultException e) {
            return null;
        }
    }
}
