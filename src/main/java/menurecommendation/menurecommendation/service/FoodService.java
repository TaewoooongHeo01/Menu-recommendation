package menurecommendation.menurecommendation.service;

import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.domain.Food;
import menurecommendation.menurecommendation.domain.FoodIngredient;
import menurecommendation.menurecommendation.domain.Ingredient;
import menurecommendation.menurecommendation.dto.IngredientDTO;
import menurecommendation.menurecommendation.repository.FoodRepository;
import menurecommendation.menurecommendation.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public void save(Food food) {
        foodRepository.save(food);
    }

    public Food findOne(Long foodId) {
        return foodRepository.findOne(foodId);
    }

    public Food findByName(String foodName) {
        return foodRepository.findByName(foodName);
    }

    @Transactional
    public FoodIngredient ConnectIngredient(Long foodId, Ingredient ingredient) {
        Food findFood = foodRepository.findOne(foodId);
        FoodIngredient foodIngredient = ingredient.conversionFoodIngredient();
        findFood.addIngredient(foodIngredient);
        return foodIngredient;
    }

    public List<Ingredient> findIngredient(List<IngredientDTO> ingredientDTOS) {
        //ingredientDTOS 로 ingredient 찾기.
        List<Ingredient> foodIngredients = new ArrayList<>();
        for(int i = 0; i < ingredientDTOS.size(); i++) {
            Ingredient findIngredient = ingredientRepository.findOne(ingredientDTOS.get(i).getId());
            foodIngredients.add(findIngredient);
        }
        return foodIngredients;
    }

    //각 음식 당 선택된 재료들이 얼마나 들어가는지 확인.
    public Map<Food, Integer> findFood(List<Ingredient> ingredients) {
        Map<Food, Integer> foodCountMap = new HashMap<>();
        for (Ingredient ingredient : ingredients) {
            List<FoodIngredient> foods = ingredient.getFoodIngredients();
            for(int j = 0; j < foods.size(); j++) {
                Food findFood = foods.get(j).getFood();
                if (foodCountMap.containsKey(findFood)) {
                    Integer count = foodCountMap.get(findFood);
                    foodCountMap.put(findFood, count + 1);
                    continue;
                }
                foodCountMap.put(findFood, 1);
            }
        }
        return foodCountMap;
    }


}
