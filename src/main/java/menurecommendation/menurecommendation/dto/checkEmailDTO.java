package menurecommendation.menurecommendation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class checkEmailDTO {

    private String email;

    public checkEmailDTO() {}

    public checkEmailDTO(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "checkEmailDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
