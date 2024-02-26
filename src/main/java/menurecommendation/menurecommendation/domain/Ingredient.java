package menurecommendation.menurecommendation.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
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
