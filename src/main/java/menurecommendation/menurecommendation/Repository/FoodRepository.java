package menurecommendation.menurecommendation.Repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.domain.Food;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FoodRepository {

    private final EntityManager em;

    public void save(Food food) {
        em.persist(food);
    }

    public Food findOne(Long foodId) {
        return em.find(Food.class, foodId);
    }
}
