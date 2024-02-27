package menurecommendation.menurecommendation.repository;

import menurecommendation.menurecommendation.domain.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FoodRepositoryTest {

    @Autowired
    FoodRepository foodRepository;

    @Test
    @DisplayName("음식 찾기")
    @Transactional
    void findOne() throws Exception {
        //given
        Food food = new Food();
        foodRepository.save(food);

        //when
        Food findFood = foodRepository.findOne(food.getId());

        //then
        assertThat(findFood.getId()).isEqualTo(food.getId());
    }

}