package menurecommendation.menurecommendation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import menurecommendation.menurecommendation.domain.Food;
import menurecommendation.menurecommendation.domain.Ingredient;
import menurecommendation.menurecommendation.dto.IngredientDTO;
import menurecommendation.menurecommendation.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FoodController {

    private final FoodService foodService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/igd")
    public ResponseEntity<String> postIGD(@RequestBody String selectedIngredients) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IngredientDTO> ingredientDTOS = objectMapper.readValue(selectedIngredients, new TypeReference<>() {});
        List<Ingredient> ingredients = foodService.findIngredient(ingredientDTOS);
        Map<Long, Integer> foodCountMap = foodService.findFood(ingredients);
        Food recommendFood = foodService.recommendFood(foodCountMap);
        log.info("추천된 음식: "+recommendFood.getFoodName());
        String foodJson = foodService.foodToJson(recommendFood);
        log.info("추천 음식 재료 정보"+foodJson);
        try {
            return new ResponseEntity<>(foodJson, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
