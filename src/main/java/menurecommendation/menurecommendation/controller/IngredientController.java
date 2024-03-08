package menurecommendation.menurecommendation.controller;

import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.service.IngredientService;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/")
    public ResponseEntity<String> getAllIngredients() throws IOException, ParseException {
        ingredientService.getIRDNT();
        try {
            String json = ingredientService.getAllIngredientsJson();
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
