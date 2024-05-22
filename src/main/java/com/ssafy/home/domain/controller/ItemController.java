package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.dto.ItemDto;
import com.ssafy.home.domain.request.CoordinateRangeRequest;
import com.ssafy.home.domain.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody ItemDto itemDto, HttpSession session) throws ParseException {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            itemService.inserItem(itemDto, (int)memberId);
            return ResponseEntity.accepted().body("매물 등록 성공");
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> delete(@PathVariable int itemId, HttpSession session) {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            int cnt = itemService.deleteItem(itemId, (int)memberId);
            if(cnt == 1) {
                return ResponseEntity.accepted().body("매물 삭제 성공");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("매물 삭제 실패");
        }
    }

    @PutMapping("/sold/{itemId}")
    public ResponseEntity<?> sold(@PathVariable int itemId, HttpSession session) {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            itemService.sold(itemId, (int)memberId);
            return ResponseEntity.accepted().body("매물 판매 여부 변경");
        }
    }

    @PostMapping("/place")
    public ResponseEntity<?> sold(@RequestBody CoordinateRangeRequest request) {
        return ResponseEntity.accepted().body(itemService.search(request));
    }
}
