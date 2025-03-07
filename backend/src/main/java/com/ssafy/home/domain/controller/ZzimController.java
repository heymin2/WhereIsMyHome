package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.dto.ZzimDto;
import com.ssafy.home.domain.service.ZzimService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/like")
public class ZzimController {

    @Autowired
    private ZzimService zzimService;

    @PostMapping("")
    public ResponseEntity<?> likeApt(@RequestBody Map<String, Integer> aptIdMap, HttpSession session) {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            Integer aptId = aptIdMap.get("aptId");
            if(zzimService.likeApt((int)memberId, aptId)) {
                return ResponseEntity.accepted().body("관심 목록 추가 성공");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재");
            }
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestBody Map<String, Integer> aptIdMap, HttpSession session) {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            Integer aptId = aptIdMap.get("aptId");
            if(zzimService.delete((int)memberId, aptId) == 1) {
                return ResponseEntity.accepted().body("관심 목록 삭제 성공");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("관심 목록 삭제 실패");
            }
        }
    }

    @GetMapping("")
    public ResponseEntity<?> search(HttpSession session) {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            return ResponseEntity.accepted().body(zzimService.search((int)memberId));
        }
    }

    @GetMapping("/{aptId}")
    public ResponseEntity<?> aptCnt(@PathVariable int aptId) {
        return ResponseEntity.accepted().body(zzimService.aptCnt(aptId));
    }

    // 아이템
    @PostMapping("/item")
    public ResponseEntity<?> likeItem(@RequestBody Map<String, Integer> item, HttpSession session) {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            Integer itemId = item.get("itemId");
            if(zzimService.likeItem((int)memberId, itemId)) {
                return ResponseEntity.accepted().body("관심 목록 추가 성공");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재");
            }
        }
    }

    @DeleteMapping("/item")
    public ResponseEntity<?> deleteItem(@RequestBody Map<String, Integer> item, HttpSession session) {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            Integer itemId = item.get("itemId");
            if(zzimService.deleteItem((int)memberId, itemId) == 1) {
                return ResponseEntity.accepted().body("관심 목록 삭제 성공");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("관심 목록 삭제 실패");
            }
        }
    }

    @GetMapping("/item")
    public ResponseEntity<?> searchItem(HttpSession session) {
        Object memberId = session.getAttribute("session");

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        else {
            return ResponseEntity.accepted().body(zzimService.searchItem((int)memberId));
        }
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<?> itemCnt(@PathVariable int itemId) {
        return ResponseEntity.accepted().body(zzimService.itemCnt(itemId));
    }
}
