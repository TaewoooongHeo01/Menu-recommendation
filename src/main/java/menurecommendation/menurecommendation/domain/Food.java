package menurecommendation.menurecommendation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Food {

    @Id
    @GeneratedValue
    @Column(name = "food_id")
    private Long id;

    private String foodName;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodIngredient> foodIngredients = new ArrayList<>();

    public void addIngredient(FoodIngredient foodIngredient) {
        this.foodIngredients.add(foodIngredient);
        foodIngredient.setFood(this);
    }
}
