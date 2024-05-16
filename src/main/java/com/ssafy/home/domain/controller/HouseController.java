package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.dto.HouseInfoDto;
import com.ssafy.home.domain.dto.HouseRequestDto;
import com.ssafy.home.domain.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @PostMapping("")
    public ResponseEntity<?> houseList(@RequestBody HouseRequestDto houseRequestDto) {
        List<HouseInfoDto> list = houseService.houseList(houseRequestDto);
        return ResponseEntity.accepted().body(list);
    }
}
