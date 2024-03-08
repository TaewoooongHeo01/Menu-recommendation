package menurecommendation.menurecommendation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.domain.Food;
import menurecommendation.menurecommendation.domain.Ingredient;
import menurecommendation.menurecommendation.dto.IngredientDTO;
import menurecommendation.menurecommendation.repository.IngredientRepository;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final FoodService foodService;

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

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Transactional
    public void getIRDNT() throws IOException, org.json.simple.parser.ParseException {
        Reader reader = new FileReader("src/main/java/menurecommendation/menurecommendation/foods.json");
        JSONParser parser = new JSONParser();
        JSONObject dateArray = (JSONObject) parser.parse(reader);
        JSONArray arr = (JSONArray) dateArray.get("TB_INGREDIENT_USAGE");

        String lastFood = "";
        Food food = new Food();
        for (int i = 0; i < arr.size(); i++) {
            JSONObject foodInfo = (JSONObject) arr.get(i);
            String currentFood = String.valueOf(foodInfo.get("FOOD_NM"));
            String ingredientName = String.valueOf(foodInfo.get("FDINGR_NM"));

            //새로운 음식
            if (!currentFood.equals(lastFood)) {
                food = new Food();
                food.setFoodName(currentFood);
                foodService.save(food);
            }

            Ingredient ingredient = ingredientRepository.findByName(ingredientName);
            if (ingredient == null) { //DB 에 없는 재료인 경우
                ingredient = new Ingredient(ingredientName);
            }

            foodService.addIngredient(food.getId(), ingredient);

            lastFood = currentFood;
            ingredientRepository.save(ingredient);
        }
    }

    public String getAllIngredientsJson() throws JsonProcessingException {
        List<Ingredient> ingredients = ingredientRepository.findAll(); // 모든 재료 조회
        List<IngredientDTO> ingredientDTOS = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            IngredientDTO ingredientDTO = new IngredientDTO(ingredients.get(i).getId(), ingredients.get(i).getIngredientName());
            ingredientDTOS.add(ingredientDTO);
        }
        ObjectMapper mapper = new ObjectMapper();
        String ingredientDTOSjson = mapper.writeValueAsString(ingredientDTOS); // List를 JSON 문자열로 변환
        return ingredientDTOSjson;
    }
}
