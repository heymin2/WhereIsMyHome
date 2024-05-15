package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.dto.BoardDto;
import com.ssafy.home.domain.dto.BoardInfoDetailDto;
import com.ssafy.home.domain.dto.BoardInfoDto;
import com.ssafy.home.domain.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("")
    public ResponseEntity<?> signUp(@RequestBody BoardDto boardDto, HttpSession session) {
        try{
            int memberId = (int) session.getAttribute("session");

            boardDto.setMemberId(memberId);
            boardService.createBoard(boardDto);
            return ResponseEntity.accepted().body("글쓰기 성공");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("글쓰기 실패");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> list() {
        List<BoardInfoDto> board = boardService.listBoard();

        if(board != null) {
            return ResponseEntity.accepted().body(board);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("목록 조회 실패");
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> listDetail(@PathVariable int boardId) {
        BoardInfoDetailDto info = boardService.listDetailBoard(boardId);
        
        if (info != null) {
            return ResponseEntity.accepted().body(info);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상세 조회 실패");
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable int boardId, @RequestBody Map<String, Integer> requestBody) {
        int memberId = requestBody.get("memberId");
        int cnt = boardService.deleteBoard(boardId, memberId);

        if (cnt == 1) {
            return ResponseEntity.accepted().body("삭제 성공");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }

    @PutMapping("update")
    public ResponseEntity<?> updateBoard(@RequestBody BoardDto boardDto) {
        int cnt = boardService.updateBoard(boardDto);

        System.out.println(boardDto);

        if (cnt == 1) {
            return ResponseEntity.accepted().body("수정 성공");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패");
    }
}
