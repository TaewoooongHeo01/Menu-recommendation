package menurecommendation.menurecommendation.service;

import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.domain.Food;
import menurecommendation.menurecommendation.domain.FoodIngredient;
import menurecommendation.menurecommendation.domain.Ingredient;
import menurecommendation.menurecommendation.repository.FoodRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

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
    public void addIngredient(Long foodId, Ingredient ingredient) {
        Food findFood = foodRepository.findOne(foodId);
        FoodIngredient foodIngredient = ingredient.conversionFoodIngredient();
        findFood.addIngredient(foodIngredient);
        foodIngredient.setFood(findFood);
    }
}
