package menurecommendation.menurecommendation.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Food {

    @Id
    @GeneratedValue
    @Column(name = "food_id")
    private Long id;

    private String foodName;

    @OneToMany(mappedBy = "food")
    private List<FoodIngredient> foodIngredients = new ArrayList<>();
}
