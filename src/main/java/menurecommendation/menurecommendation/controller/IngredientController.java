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
import menurecommendation.menurecommendation.service.IngredientService;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class IngredientController {

    private final IngredientService ingredientService;
    private final FoodService foodService;

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/")
    public ResponseEntity<String> getAllIngredients() throws IOException, ParseException {
        ingredientService.getIRDNT();
        try {
            List<IngredientDTO> ingredientDTOS = ingredientService.getAllIngredientsDTO();
            String json = ingredientService.ingredientDTOtoJSON(ingredientDTOS);
            if (json != null) {
                log.info("Ingredient list API success");
            }
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/igd")
    public void postIGD(@RequestBody String selectedIngredients) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IngredientDTO> ingredientDTOS = objectMapper.readValue(selectedIngredients, new TypeReference<>() {});
        List<Ingredient> ingredients = foodService.findIngredient(ingredientDTOS);
        Map<Food, Integer> foodCountMap = foodService.findFood(ingredients);
        //비율에 따라 음식 추천해주는 메서드

    }
}
