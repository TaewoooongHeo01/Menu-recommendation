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
