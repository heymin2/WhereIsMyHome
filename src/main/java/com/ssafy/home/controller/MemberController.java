package com.ssafy.home.controller;

import com.ssafy.home.dto.MemberDto;
import com.ssafy.home.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody MemberDto memberDto, HttpSession session) {
        MemberDto s = (MemberDto) session.getAttribute("member");

        if(s.getId().equals(memberDto.getId())) {
            int cnt = memberService.updateMember(memberDto);

            if (cnt == 1) {
                return ResponseEntity.accepted().body("수정 성공");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패");
    }
}
