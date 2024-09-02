package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.request.CoordinateRangeRequest;
import com.ssafy.home.domain.service.HouseService;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<?> houseList(@RequestBody CoordinateRangeRequest request, HttpSession session) {
        Object memberId = session.getAttribute("session");
        return ResponseEntity.accepted().body(houseService.houseList(request, memberId));
    }

    @PostMapping("/area")
    public ResponseEntity<?> dongList(@RequestBody CoordinateRangeRequest request) {
        return ResponseEntity.accepted().body(houseService.dongList(request));
    }

    @PostMapping("/bus")
    public ResponseEntity<?> busList(@RequestBody CoordinateRangeRequest request) {
        return ResponseEntity.accepted().body(houseService.busList(request));
    }

    @PostMapping("/food")
    public ResponseEntity<?> foodList(@RequestBody CoordinateRangeRequest request) {
        return ResponseEntity.accepted().body(houseService.foodList(request));
    }

    @PostMapping("/cafe")
    public ResponseEntity<?> cafeList(@RequestBody CoordinateRangeRequest request) {
        return ResponseEntity.accepted().body(houseService.cafeList(request));
    }
}
