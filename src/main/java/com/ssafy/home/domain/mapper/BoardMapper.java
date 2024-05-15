package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.BoardDto;
import com.ssafy.home.domain.dto.BoardInfoDetailDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    void createBoard(BoardDto boardDto);
    List<Map<String, Object>> list(Map<String, Object> paramMap);
    int getListBoardCount();
    BoardInfoDetailDto listDetailBoard(int boardId);
    int updateBoard(BoardDto boardDto);
    int deleteBoard(int boardId, int memberId);
}
