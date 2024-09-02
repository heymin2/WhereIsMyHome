package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.dto.LoginDto;
import com.ssafy.home.domain.dto.MemberDto;
import com.ssafy.home.domain.service.MemberService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequestMapping("/member")
@Tag(name="회원관리 컨트롤러", description = "회원가입, 회원수정, 아이디 중복 관리")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Operation(summary = "회원가입")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "404", description = "회원가입 실패"),
            @ApiResponse(responseCode = "500", description = "서버 에러") })
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "등록할 회원정보", required = true, content = @Content(schema = @Schema(implementation = MemberDto.class)))
            @RequestBody MemberDto memberDto) {
        try {
            memberService.signUp(memberDto);
            return ResponseEntity.accepted().body("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
        }
    }

    @Operation(summary = "회원수정")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(implementation = MemberDto.class))),
            @ApiResponse(responseCode = "404", description = "수정 실패")})
    @PutMapping("")
    public ResponseEntity<?> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정할 회원정보", required = true, content = @Content(schema = @Schema(implementation = MemberDto.class)))
            @RequestBody MemberDto memberDto, HttpSession session) {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            memberDto.setMemberId((int) memberId);
            LoginDto member =  memberService.updateMember(memberDto);
            if(member == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패");
            }
            return ResponseEntity.accepted().body(member);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> validateId(@PathVariable String id) {
        int cnt = memberService.getMemberById(id);

        if(cnt == 0) {
            return ResponseEntity.accepted().body("아이디 없음");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디 존재");
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> validateEmail(@PathVariable String email) {
        int cnt = memberService.getMemberByEmail(email);

        if(cnt == 0) {
            return ResponseEntity.accepted().body("이메일 없음");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일 존재");
    }
}