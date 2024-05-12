package com.ssafy.home.controller;

import com.ssafy.home.dto.AreaResultDto;
import com.ssafy.home.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/do")
    public ResponseEntity<?> findSido(){
        List<String> list = areaService.findSido();
        AreaResultDto dto = new AreaResultDto();
        dto.setSido(list);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/sido")
    public ResponseEntity<?> findGugun(@RequestBody Map<String, String> sido){
        List<String> list = areaService.findGugun(sido.get("sido"));
        AreaResultDto dto = new AreaResultDto();
        dto.setGugun(list);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/dong")
    public ResponseEntity<?> findDong(@RequestBody Map<String, String> gugun){
        List<String> list = areaService.findDong(gugun.get("gugun"));
        AreaResultDto dto = new AreaResultDto();
        dto.setDong(list);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}