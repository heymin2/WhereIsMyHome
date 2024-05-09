package com.ssafy.home.controller;

import com.ssafy.home.dto.BoardDto;
import com.ssafy.home.dto.MemberDto;
import com.ssafy.home.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<?> signUp(@RequestBody BoardDto boardDto, HttpSession session) {
        MemberDto member = (MemberDto) session.getAttribute("member");

        if(member != null) {
            boardDto.setMember_id(member.getMember_id());
            boardService.createBoard(boardDto);
            return ResponseEntity.accepted().body("글쓰기 성공");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("글쓰기 실패");
    }
}
