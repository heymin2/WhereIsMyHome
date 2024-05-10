package com.ssafy.home.mapper;

import com.ssafy.home.dto.BoardDto;
import com.ssafy.home.dto.BoardInfoDetailDto;
import com.ssafy.home.dto.BoardInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    void createBoard(BoardDto boardDto);
    List<BoardInfoDto> listBoard();
    BoardInfoDetailDto listDetailBoard(int boardId);
}
