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

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<MemberIngredient> memberIngredients = new ArrayList<>();

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<FoodIngredient> foodIngredients = new ArrayList<>();

    public Ingredient() {};

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public MemberIngredient conversionMemberIngredient() {
        MemberIngredient memberIngredient = new MemberIngredient();
        this.memberIngredients.add(memberIngredient);
        memberIngredient.setIngredient(this);
        return memberIngredient;
    }

    public FoodIngredient conversionFoodIngredient() {
        FoodIngredient foodIngredient = new FoodIngredient();
        this.foodIngredients.add(foodIngredient);
        foodIngredient.setIngredient(this);
        return foodIngredient;
    }
}
