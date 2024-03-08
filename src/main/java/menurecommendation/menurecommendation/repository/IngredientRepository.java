package menurecommendation.menurecommendation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.domain.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public Ingredient findByName(String ingredientName) {
        try {
            return em.createQuery("select i from Ingredient i where i.ingredientName = :ingredientName", Ingredient.class)
                    .setParameter("ingredientName", ingredientName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Ingredient> findAll() {
        return em.createQuery("select i from Ingredient i", Ingredient.class)
                .getResultList();
    }

    public void delete(Long ingredientId) {
        Ingredient findOne = em.find(Ingredient.class, ingredientId);
        if (findOne != null) {
            em.remove(findOne);
        }
    }
}
