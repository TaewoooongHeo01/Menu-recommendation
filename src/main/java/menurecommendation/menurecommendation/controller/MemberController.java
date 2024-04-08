package menurecommendation.menurecommendation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
        try {
            memberService.join(memberDTO.toEntity());
            return ResponseEntity.ok().body("server: 회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server: 회원가입 중 에러 발생");
        }
    }

    @PostMapping("/emailCheck")
    public ResponseEntity<String> checkEmail(@RequestBody checkEmailDTO checkEmailDTO) {
        boolean check = memberService.emailCheck(checkEmailDTO.getEmail());
        if (check) {
            return ResponseEntity.ok().body("이메일 사용가능");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("이메일 사용불가");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginForm loginForm,
            HttpServletResponse res,
            HttpSession httpSession) throws JsonProcessingException {
        Member findMember = memberService.login(loginForm.getEmail(), loginForm.getPasswd());
        if (findMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }

        Cookie cookie = new Cookie("memberId", String.valueOf(findMember.getId()));
        cookie.setPath("/"); // 쿠키의 유효 경로 설정
        cookie.setHttpOnly(true); // JavaScript를 통한 접근 방지
        cookie.setMaxAge(7 * 24 * 60 * 60); // 쿠키의 유효 시간 설정 (예: 1주일)
        cookie.setSecure(true); // SameSite=None을 사용하기 위해 필요
        cookie.setMaxAge(60 * 60 * 24); // 1일

        String cookieValue = "key=value; Path=/; HttpOnly; Secure; SameSite=None";
        res.addHeader("Set-Cookie", cookieValue);
        res.addCookie(cookie);
        String memberInfo = memberService.memberToJson(findMember);
        log.info("memberJson: "+memberInfo);
        return ResponseEntity.status(HttpStatus.OK).body(memberInfo);
    }
}
