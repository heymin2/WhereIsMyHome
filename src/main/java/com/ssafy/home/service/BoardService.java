package com.ssafy.home.service;

import com.ssafy.home.dto.BoardDto;
import com.ssafy.home.dto.BoardInfoDto;
import com.ssafy.home.mapper.BoardMapper;
import jdk.jfr.Timestamp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }
    @Timestamp
    public void createBoard(BoardDto boardDto) {
        boardMapper.createBoard(boardDto);
    }

    public List<BoardInfoDto> listBoard() {
        return boardMapper.listBoard();
    }
}
