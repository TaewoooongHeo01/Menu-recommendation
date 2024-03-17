package menurecommendation.menurecommendation.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import menurecommendation.menurecommendation.domain.Food;
import menurecommendation.menurecommendation.domain.FoodIngredient;
import menurecommendation.menurecommendation.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FoodDTO {

    @Id
    private Long id;
    private String foodName;
    private List<String> foodIngredients;

    public FoodDTO(Long id, String foodName, List<String> foodIngredients) {
        this.id = id;
        this.foodName = foodName;
        this.foodIngredients = foodIngredients;
    }

    public FoodDTO() {
    }
}
