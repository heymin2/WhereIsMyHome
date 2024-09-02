package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.BoardInfoDetailDto;
import com.ssafy.home.domain.dto.BoardDto;
import com.ssafy.home.domain.mapper.BoardMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    public Page<Map<String, Object>> list(Map<String, Object> paramMap, Pageable pageable) {
        paramMap.put("offset", pageable.getOffset());
        paramMap.put("pageSize", pageable.getPageSize());

        List<Map<String, Object>> content = boardMapper.list(paramMap);
        int cnt = boardMapper.getListBoardCount();

        return new PageImpl<>(content, pageable, cnt);
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
