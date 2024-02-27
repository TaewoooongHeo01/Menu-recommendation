package menurecommendation.menurecommendation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    @OneToMany(mappedBy = "member")
    private List<MemberIngredient> memberIngredients = new ArrayList<>();

    public void addIngredient(MemberIngredient ingredient) {
        this.memberIngredients.add(ingredient);
        ingredient.setMember(this);
    }
}
