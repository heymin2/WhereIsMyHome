package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.dto.LoginDto;
import com.ssafy.home.domain.dto.MemberDto;
import com.ssafy.home.domain.service.AuthService;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto memberDto, HttpSession session){
        MemberDto member = authService.login(memberDto);

        if(member != null) {
            session.setAttribute("session", member.getMemberId());

            LoginDto loginDto = new LoginDto();
            loginDto.update(member);
            return ResponseEntity.ok().body(loginDto);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 사용자 정보가 올바르지 않습니다.");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }

    @PostMapping("/findPw")
    public ResponseEntity<?> findByPass(@RequestBody MemberDto memberDto) {
        String pw = authService.findByPass(memberDto.getId(), memberDto.getName());
        if(pw != null){
            return ResponseEntity.accepted().body(pw);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 찾기 실패");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> info(HttpSession httpSession) {
        try{
            int memberId = (int) httpSession.getAttribute("session");
            return ResponseEntity.accepted().body(authService.info(memberId));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("세션 만료");
        }
    }
}