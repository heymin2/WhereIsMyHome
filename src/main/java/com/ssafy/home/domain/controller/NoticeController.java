package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.dto.*;
import com.ssafy.home.domain.service.AuthService;
import com.ssafy.home.domain.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private AuthService authService;

    @PostMapping("")
    public ResponseEntity<?> signUp(@RequestBody NoticeDto noticeDto, HttpSession session) {
        try{
            int memberId = (int) session.getAttribute("session");

            MemberDto memberDto = authService.info(memberId);

            if(memberDto.isAdmin()) {
                noticeDto.setMemberId(memberId);
                noticeService.createNotice(noticeDto);
                return ResponseEntity.accepted().body("공지사항 성공");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("권한 없음");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("글쓰기 실패");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> list(@RequestParam Map<String, Object> paramMap, @PageableDefault(size = 10) Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();

        Page<Map<String, Object>> result = noticeService.list(paramMap, pageable);
        resultMap.put("pages", result);
        resultMap.put("size", pageable.getPageSize());

        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<?> listDetail(@PathVariable int noticeId) {
        NoticeInfoDetailDto info = noticeService.listDetailNotice(noticeId);

        if (info != null) {
            return ResponseEntity.accepted().body(info);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상세 조회 실패");
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<?> deleteBoard(@PathVariable int noticeId, HttpSession session) {
        int memberId = (int) session.getAttribute("session");
        int cnt = noticeService.deleteNotice(noticeId, memberId);

        if (cnt == 1) {
            return ResponseEntity.accepted().body("삭제 성공");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }

    @PutMapping("")
    public ResponseEntity<?> updateBoard(@RequestBody NoticeDto noticeDto, HttpSession session) {
        int memberId = (int) session.getAttribute("session");

        noticeDto.setMemberId(memberId);
        int cnt = noticeService.updateNotice(noticeDto);

        System.out.println(noticeDto);

        if (cnt == 1) {
            return ResponseEntity.accepted().body("수정 성공");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패");
    }
}
