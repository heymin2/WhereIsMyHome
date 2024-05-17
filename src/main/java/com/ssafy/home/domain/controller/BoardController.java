package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.dto.BoardDto;
import com.ssafy.home.domain.dto.BoardInfoDetailDto;
import com.ssafy.home.domain.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;

import java.util.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("")
    public ResponseEntity<?> signUp(@RequestBody BoardDto boardDto, HttpSession session) {
        Object memberId = session.getAttribute("session");
        
        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            boardDto.setMemberId((int)memberId);
            boardService.createBoard(boardDto);
            return ResponseEntity.accepted().body("글쓰기 성공");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> list(@RequestParam Map<String, Object> paramMap, @PageableDefault(size = 10) Pageable pageable, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();

        Page<Map<String, Object>> result = boardService.list(paramMap, pageable);
        resultMap.put("pages", result);
        resultMap.put("size", pageable.getPageSize());

        return ResponseEntity.ok(resultMap);
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
    public ResponseEntity<?> deleteBoard(@PathVariable int boardId, HttpSession session) {
        int memberId = (int) session.getAttribute("session");
        int cnt = boardService.deleteBoard(boardId, memberId);

        if (cnt == 1) {
            return ResponseEntity.accepted().body("삭제 성공");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }

    @PutMapping("")
    public ResponseEntity<?> updateBoard(@RequestBody BoardDto boardDto, HttpSession session) {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션 만료");
        }
        else {
            if(boardService.updateBoard(boardDto) == 1) {
                return ResponseEntity.accepted().body("수정 성공");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패");
        }
    }
}
