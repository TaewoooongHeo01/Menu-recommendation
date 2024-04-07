package menurecommendation.menurecommendation.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import menurecommendation.menurecommendation.domain.Member;
import menurecommendation.menurecommendation.dto.MemberDTO;
import menurecommendation.menurecommendation.dto.checkEmailDTO;
import menurecommendation.menurecommendation.service.MemberService;
import menurecommendation.menurecommendation.validation.LoginForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping ("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO memberDTO) {
        log.info("memberDTO: " + memberDTO.toString());
        try {
            memberService.join(memberDTO.toEntity());
            return ResponseEntity.ok().body("server: 회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server: 회원가입 중 에러 발생");
        }
    }

    @PostMapping("/emailCheck")
    public ResponseEntity<String> checkEmail(@RequestBody checkEmailDTO checkEmailDTO) {
        log.info("checkEmailDTO: " + checkEmailDTO.toString());
        boolean check = memberService.emailCheck(checkEmailDTO.getEmail());
        log.info("check: " + check);
        if (check) {
            return ResponseEntity.ok().body("이메일 사용가능");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("이메일 사용불가");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginForm loginForm,
            HttpSession httpSession) {
        log.info("login email: " + loginForm.getEmail());
        log.info("login passwd: " + loginForm.getPasswd());
        //email 로 member 찾고, member id 로 쿠키 생성해서 보내기
        Member findMember = memberService.login(loginForm.getEmail(), loginForm.getPasswd());
        log.info("findMember: " + findMember);
        if (findMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }
}
