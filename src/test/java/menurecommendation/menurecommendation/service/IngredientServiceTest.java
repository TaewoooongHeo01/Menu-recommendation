package menurecommendation.menurecommendation.service;

import menurecommendation.menurecommendation.domain.Food;
import menurecommendation.menurecommendation.domain.Ingredient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class
IngredientServiceTest {

    @Autowired
    IngredientService ingredientService;

    @Autowired
    FoodService foodService;

    @Test
    @DisplayName("재료 찾기")
    @Transactional
    void findOne() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredientService.save(ingredient);

        //when
        Ingredient findIngredient = ingredientService.findOne(ingredient.getId());

        //then
        assertThat(findIngredient.getId()).isEqualTo(ingredient.getId());
    }

    @Test
    @DisplayName("재료 삭제")
    @Transactional
    void delete() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredientService.save(ingredient);
        Ingredient findIngredient = ingredientService.findOne(ingredient.getId());
        assertThat(ingredient.getId()).isEqualTo(findIngredient.getId());

        //when
        ingredientService.delete(ingredient.getId());

        //then
//        assertThrows(NullPointerException.class,
//                () -> ingredientService.findOne(ingredient.getId()));
        Ingredient findOne = ingredientService.findOne(ingredient.getId());
        assertNull(findOne);
    }

    @Test
    @DisplayName("JSON -> 재료, 음식 등록")
    @Transactional
    void jsonToIngredientAndFood() throws Exception {
        //given
        ingredientService.getIRDNT();
        Food findFood = foodService.findByName("연두부달걀찜");

        for (int i = 0; i < findFood.getFoodIngredients().size(); i++) {
            System.out.println(findFood.getFoodIngredients().get(i).getIngredient().getIngredientName());
        }
    }
}