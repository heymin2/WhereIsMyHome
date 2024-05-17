package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.request.CoordinateRangeRequest;
import com.ssafy.home.domain.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @PostMapping("")
    public ResponseEntity<?> houseList(@RequestBody CoordinateRangeRequest request) {
        return ResponseEntity.accepted().body(houseService.houseList(request));
    }

    @PostMapping("/area")
    public ResponseEntity<?> dongList(@RequestBody CoordinateRangeRequest request) {
        return ResponseEntity.accepted().body(houseService.dongList(request));
    }

    @PostMapping("/bus")
    public ResponseEntity<?> busList(@RequestBody CoordinateRangeRequest request) {
        return ResponseEntity.accepted().body(houseService.busList(request));
    }
}
