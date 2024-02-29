package menurecommendation.menurecommendation.repository;

import menurecommendation.menurecommendation.domain.Ingredient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DisplayName("이름으로 조회")
    @Transactional
    void findByName() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("고추장");
        ingredientRepository.save(ingredient);

        //when
        Ingredient findIngredient = ingredientRepository.findByName("고추장");

        //then
        assertThat(ingredient.getIngredientName()).isEqualTo(findIngredient.getIngredientName());
    }
}