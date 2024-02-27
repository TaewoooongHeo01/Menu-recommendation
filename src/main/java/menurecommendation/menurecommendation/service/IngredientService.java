package menurecommendation.menurecommendation.service;

import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.domain.Ingredient;
import menurecommendation.menurecommendation.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Transactional
    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public Ingredient findOne(Long ingredientId) {
        return ingredientRepository.findOne(ingredientId);
    }

    @Transactional
    public void delete(Long ingredientId) {
        ingredientRepository.delete(ingredientId);
    }
}
