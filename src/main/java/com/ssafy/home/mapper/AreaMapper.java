package com.ssafy.home.mapper;

import com.ssafy.home.dto.AreaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreaMapper {

    List<String> findSido();
    List<String> findGugun(String sido);
    List<String> findDong(String gugun);
}
