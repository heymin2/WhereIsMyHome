package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.BoardInfoDetailDto;
import com.ssafy.home.domain.dto.BoardInfoDto;
import com.ssafy.home.domain.dto.BoardDto;
import com.ssafy.home.domain.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }
    @Transactional
    public void createBoard(BoardDto boardDto) {
        boardMapper.createBoard(boardDto);
    }

    public List<BoardInfoDto> listBoard() {
        return boardMapper.listBoard();
    }

    public BoardInfoDetailDto listDetailBoard(int boardId) {
        return boardMapper.listDetailBoard(boardId);
    }

    @Transactional
    public int updateBoard(BoardDto boardDto) {
        return boardMapper.updateBoard(boardDto);
    }

    @Transactional
    public int deleteBoard(int boardId, int memberId) {
        return boardMapper.deleteBoard(boardId, memberId);
    }

}
