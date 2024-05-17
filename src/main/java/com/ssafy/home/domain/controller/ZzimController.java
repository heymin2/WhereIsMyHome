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
    public ResponseEntity<?> delete(HttpSession session) {
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
}
