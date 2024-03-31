package menurecommendation.menurecommendation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import menurecommendation.menurecommendation.dto.MemberDTO;
import menurecommendation.menurecommendation.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String loginInfo) {
        log.info("login info: " + loginInfo);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }
}
