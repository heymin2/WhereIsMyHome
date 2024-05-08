package com.ssafy.home.controller;

import com.ssafy.home.entity.Member;
import com.ssafy.home.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member member, HttpSession session) {
        try {
            Member id = memberService.login(member);
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
