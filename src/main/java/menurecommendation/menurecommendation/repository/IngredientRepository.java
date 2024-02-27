package menurecommendation.menurecommendation.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.domain.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IngredientRepository {

    private final EntityManager em;

    public void save(Ingredient ingredient) {
        em.persist(ingredient);
    }

    public Ingredient findOne(Long ingredientId) {
        return em.find(Ingredient.class, ingredientId);
    }
}
