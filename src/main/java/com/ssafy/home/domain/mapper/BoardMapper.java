package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.BoardDto;
import com.ssafy.home.domain.dto.BoardInfoDetailDto;
import com.ssafy.home.domain.dto.BoardInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    void createBoard(BoardDto boardDto);
    List<BoardInfoDto> listBoard();
    BoardInfoDetailDto listDetailBoard(int boardId);
    int updateBoard(BoardDto boardDto);
    int deleteBoard(int boardId, int memberId);
}
