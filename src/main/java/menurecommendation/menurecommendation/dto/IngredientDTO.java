package menurecommendation.menurecommendation.dto;

import lombok.Getter;

@Getter
public class IngredientDTO {
    private Long id;
    private String ingredientName;

    public IngredientDTO(Long id, String ingredientName) {
        this.id = id;
        this.ingredientName = ingredientName;
    }

    public IngredientDTO() {
    }
}
