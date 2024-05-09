package com.ssafy.home.mapper;

import com.ssafy.home.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    public void createBoard(BoardDto boardDto);
}
