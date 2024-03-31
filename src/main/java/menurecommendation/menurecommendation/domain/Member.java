package menurecommendation.menurecommendation.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String passwd;

    private String username;

    @OneToMany(mappedBy = "member")
    private final List<MemberIngredient> memberIngredients = new ArrayList<>();

    public void addIngredient(MemberIngredient ingredient) {
        this.memberIngredients.add(ingredient);
        ingredient.setMember(this);
    }
}
