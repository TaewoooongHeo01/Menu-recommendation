package menurecommendation.menurecommendation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import menurecommendation.menurecommendation.domain.Member;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberDTO {

    private Long id;
    private String email;
    private String passwd;
    private String username;

    @Builder
    public MemberDTO(String email, String passwd, String username) {
        this.email = email;
        this.passwd = passwd;
        this.username = username;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .passwd(passwd)
                .username(username).build();
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
