package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.dto.LoginDto;
import com.ssafy.home.domain.request.FindPwRequest;
import com.ssafy.home.domain.request.LoginRequest;
import com.ssafy.home.domain.dto.MemberDto;
import com.ssafy.home.domain.service.AuthService;
import com.ssafy.home.domain.service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session){
        LoginDto member = authService.login(request.getId(), request.getPw());

        if(member != null) {
            session.setAttribute("session", member.getMemberId());

            return ResponseEntity.ok().body(member);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 사용자 정보가 올바르지 않습니다.");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }

    @PostMapping("/findPw")
    public ResponseEntity<?> findByPass(@RequestBody FindPwRequest request) throws Exception {
        if(emailService.sendEmailMessage(request.getId(), request.getName())) {
            return ResponseEntity.accepted().body("비밀번호 찾기 성공");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 찾기 실패");
    }

    @GetMapping("")
    public ResponseEntity<?> info(HttpSession httpSession) {
        try{
            int memberId = (int) httpSession.getAttribute("session");
            LoginDto member = authService.info(memberId);
            return ResponseEntity.ok().body(member);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("세션 만료");
        }
    }
}