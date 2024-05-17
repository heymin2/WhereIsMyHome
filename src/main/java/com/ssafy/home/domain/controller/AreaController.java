package com.ssafy.home.domain.controller;

import com.ssafy.home.domain.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("")
    public ResponseEntity<?> findSido(){
        return new ResponseEntity<>(areaService.findSido(), HttpStatus.OK);
    }

    @GetMapping("/{sido}")
    public ResponseEntity<?> findGugun(@PathVariable String sido){
        return new ResponseEntity<>(areaService.findGugun(sido), HttpStatus.OK);
    }

    @GetMapping("/{sido}/{gugun}")
    public ResponseEntity<?> findDong(@PathVariable String sido, @PathVariable String gugun){
        return new ResponseEntity<>(areaService.findDong(sido, gugun), HttpStatus.OK);
    }

    @GetMapping("/{sido}/{gugun}/{dong}")
    public ResponseEntity<?> findArea(@PathVariable String sido, @PathVariable String gugun, @PathVariable String dong){
        return new ResponseEntity<>(areaService.fingArea(sido, gugun, dong), HttpStatus.OK);
    }

    @GetMapping("/apt/{aptId}")
    public ResponseEntity<?> aptInfo(@PathVariable String aptId){
        return new ResponseEntity<>(areaService.aptInfo(aptId), HttpStatus.OK);
    }
}