package menurecommendation.menurecommendation.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import menurecommendation.menurecommendation.dto.IngredientDTO;
import menurecommendation.menurecommendation.service.IngredientService;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/")
    public ResponseEntity<String> getAllIngredients(
            HttpServletRequest request) throws IOException, ParseException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("memberId".equals(cookie.getName())) {
                    log.info("memberId: " + cookie.getValue());
                }
            }
        } else {
            log.info("no cookie");
        }
        ingredientService.getIRDNT();
        try {
            List<IngredientDTO> ingredientDTOS = ingredientService.getAllIngredientsDTO();
            String json = ingredientService.ingredientDTOtoJSON(ingredientDTOS);
            if (json != null) {
                log.info("Ingredient list API success");
            }
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
