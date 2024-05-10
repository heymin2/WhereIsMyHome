package com.ssafy.home.controller;

import com.ssafy.home.dto.LoginDto;
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

    // 회원관리
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody MemberDto memberDto) {
        try {
            memberService.signUp(memberDto);
            return ResponseEntity.accepted().body("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
        }
    }

    @PostMapping("/update")
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

    // 로그인 관리

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto memberDto, HttpSession session){
        MemberDto member = memberService.login(memberDto);

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

    @PostMapping("/pass")
    public ResponseEntity<?> findByPass(@RequestBody MemberDto memberDto) {
        String pw = memberService.findByPass(memberDto.getId(), memberDto.getName());
        if(pw != null){
            return ResponseEntity.accepted().body(pw);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 찾기 실패");
        }
    }
}
