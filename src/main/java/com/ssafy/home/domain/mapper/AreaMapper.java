package com.ssafy.home.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreaMapper {

    List<String> findSido();
    List<String> findGugun(String sido);
    List<String> findDong(String sido, String gugun);
}
