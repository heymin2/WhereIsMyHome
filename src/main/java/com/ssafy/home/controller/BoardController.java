package com.ssafy.home.controller;

import com.ssafy.home.dto.BoardDto;
import com.ssafy.home.dto.BoardInfoDetailDto;
import com.ssafy.home.dto.BoardInfoDto;
import com.ssafy.home.dto.MemberDto;
import com.ssafy.home.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<?> signUp(@RequestBody BoardDto boardDto, HttpSession session) {
        MemberDto member = (MemberDto) session.getAttribute("member");

        if(member != null) {
            boardDto.setMemberId(member.getMemberId());
            boardService.createBoard(boardDto);
            return ResponseEntity.accepted().body("글쓰기 성공");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("글쓰기 실패");
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<BoardInfoDto> board = boardService.listBoard();

        if(board != null) {
            return ResponseEntity.accepted().body(board);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("목록 조회 실패");
    }

    @GetMapping("list/{boardId}")
    public ResponseEntity<?> listDetail(@PathVariable int boardId) {
        BoardInfoDetailDto info = boardService.listDetailBoard(boardId);
        
        if (info != null) {
            return ResponseEntity.accepted().body(info);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상세 조회 실패");
    }
}
