package menurecommendation.menurecommendation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private Long id;

    private String ingredientName;

    @OneToMany(mappedBy = "ingredient")
    private List<MemberIngredient> memberIngredients = new ArrayList<>();

    @OneToMany(mappedBy = "ingredient")
    private List<FoodIngredient> foodIngredients = new ArrayList<>();
}
