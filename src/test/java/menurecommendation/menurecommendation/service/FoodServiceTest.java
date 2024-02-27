package menurecommendation.menurecommendation.service;

import menurecommendation.menurecommendation.domain.Food;
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
class FoodServiceTest {

    @Autowired
    FoodService foodService;

    @Test
    @DisplayName("음식 조회")
    @Transactional
    void findOne() throws Exception {
        //given
        Food food = new Food();
        foodService.save(food);

        //when
        Food findOne = foodService.findOne(food.getId());

        //then
        assertThat(findOne.getId()).isEqualTo(food.getId());
    }

    @Test
    @DisplayName("재료 추가")
    @Transactional
    void addIngredient() throws Exception {
        //given
        Food food = new Food();
        Ingredient ingredientA = new Ingredient();
        Ingredient ingredientB = new Ingredient();

        //when
        foodService.save(food);
        foodService.addIngredient(food.getId(), ingredientA);
        foodService.addIngredient(food.getId(), ingredientB);
        Food findOne = foodService.findOne(food.getId());

        //then
        assertThat(findOne.getFoodIngredients().size()).isEqualTo(2);
    }
}