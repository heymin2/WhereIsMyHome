package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.dto.ItemDto;
import com.ssafy.home.domain.dto.LoginDto;
import com.ssafy.home.domain.dto.NoticeDto;
import com.ssafy.home.domain.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
