package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("")
    public ResponseEntity<?> findSido(){
        List<String> list = areaService.findSido();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{sido}")
    public ResponseEntity<?> findGugun(@PathVariable String sido){
        List<String> list = areaService.findGugun(sido);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{sido}/{gugun}")
    public ResponseEntity<?> findDong(@PathVariable String sido, @PathVariable String gugun){
        List<String> list = areaService.findDong(sido, gugun);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}