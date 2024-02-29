package menurecommendation.menurecommendation.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.domain.Food;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FoodRepository {

    private final EntityManager em;

    public void save(Food food) {
        em.persist(food);
    }

    public Food findByName(String foodName) {
        try {
            return em.createQuery("select f from Food f where f.foodName = :foodName", Food.class)
                    .setParameter("foodName", foodName).getSingleResult();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Food findOne(Long foodId) {
        return em.find(Food.class, foodId);
    }
}
