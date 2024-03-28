package menurecommendation.menurecommendation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import menurecommendation.menurecommendation.domain.Food;
import menurecommendation.menurecommendation.domain.FoodIngredient;
import menurecommendation.menurecommendation.domain.Ingredient;
import menurecommendation.menurecommendation.dto.FoodDTO;
import menurecommendation.menurecommendation.dto.IngredientDTO;
import menurecommendation.menurecommendation.repository.FoodRepository;
import menurecommendation.menurecommendation.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
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

    //각 음식 당 선택된 재료들이 얼마나 들어가는지 확인
    public Map<Long, Integer> findFood(List<Ingredient> ingredients) {
        Map<Long, Integer> foodCountMap = new HashMap<>();
        for (Ingredient ingredient : ingredients) {
            List<FoodIngredient> foods = ingredient.getFoodIngredients();
            for (FoodIngredient food : foods) {
                Food findFood = food.getFood();
                if (foodCountMap.containsKey(findFood.getId())) {
                    Integer count = foodCountMap.get(findFood.getId());
                    foodCountMap.put(findFood.getId(), count + 1);
                    continue;
                }
                foodCountMap.put(findFood.getId(), 1);
            }
        }
        return foodCountMap;
    }

    public Food recommendFood(Map<Long, Integer> foodCountMap) {
        List<Food> randomList = new ArrayList<>();
        List<Food> recommendList = new ArrayList<>();
        foodCountMap.forEach((key, value) -> {
            Food findFood = foodRepository.findOne(key);
            double foodIngredientCount = findFood.getFoodIngredients().size();
            double currentIngredientCount = foodCountMap.get(key);
            randomList.add(findFood);
            if((currentIngredientCount / foodIngredientCount) >= 0.7) {
                log.info("추가된 음식: "+findFood.getFoodName());
                recommendList.add(findFood);
            }
        });
        if (recommendList.size() == 0) {
            Random rn = new Random();
            int randomIdx = rn.nextInt(randomList.size() - 1);
            return randomList.get(randomIdx);
        }
        Random rn = new Random();
        int randomIdx = rn.nextInt(recommendList.size() - 1);
        return recommendList.get(randomIdx);
    }

    public String foodToJson(Food food) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> foodIngredientName = new ArrayList<>();
        for(int i = 0; i < food.getFoodIngredients().size(); i++) {
            foodIngredientName.add(food.getFoodIngredients().get(i).getIngredient().getIngredientName());
        }
        FoodDTO foodDTO = new FoodDTO(food.getId(), food.getFoodName(), foodIngredientName);
        return objectMapper.writeValueAsString(foodDTO);
    }
}
