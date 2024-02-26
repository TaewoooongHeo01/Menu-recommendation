package menurecommendation.menurecommendation.Repository;

import menurecommendation.menurecommendation.domain.Ingredient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    @DisplayName("재료 찾기")
    @Transactional
    void findOne() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredientRepository.save(ingredient);

        //when
        Ingredient findIngredient = ingredientRepository.findOne(ingredient.getId());

        //then
        assertThat(findIngredient.getId()).isEqualTo(ingredient.getId());
    }
}