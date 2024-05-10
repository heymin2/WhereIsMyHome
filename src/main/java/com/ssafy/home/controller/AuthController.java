package com.ssafy.home.controller;

import com.ssafy.home.dto.LoginDto;
import com.ssafy.home.dto.MemberDto;
import com.ssafy.home.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            loginDto.setMemberId(member.getMemberId());
            loginDto.setName(member.getName());
            loginDto.setId(member.getId());
            loginDto.setAdmin(member.getAdmin());
            loginDto.setEmail(member.getEmail());

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
}