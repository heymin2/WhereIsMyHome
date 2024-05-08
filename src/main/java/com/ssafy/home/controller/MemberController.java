package com.ssafy.home.controller;

import com.ssafy.home.dto.MemberDto;
import com.ssafy.home.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody MemberDto memberDto) {
        try {
            memberService.signUp(memberDto);
            return ResponseEntity.accepted().body("회원가입 성공");
        } catch (Exception e) {
            return new ResponseEntity<>(memberDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto memberDto, HttpSession session) {
        try {
            MemberDto id = memberService.login(memberDto);
            System.out.println(id);
            session.setAttribute("loggedInUser", id);

            if(id != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return ResponseEntity.accepted().body("로그인 실패");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 실패");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.removeAttribute("loggedInUser");
        return ResponseEntity.accepted().body("로그아웃 성공");
    }
}
