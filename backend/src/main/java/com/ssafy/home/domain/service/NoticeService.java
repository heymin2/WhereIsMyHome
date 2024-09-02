package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.NoticeDto;
import com.ssafy.home.domain.dto.NoticeInfoDetailDto;
import com.ssafy.home.domain.mapper.NoticeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }
    @Transactional
    public void createNotice(NoticeDto noticeDto) {
        noticeMapper.createNotice(noticeDto);
    }

    public Page<Map<String, Object>> list(Map<String, Object> paramMap, Pageable pageable) {
        paramMap.put("offset", pageable.getOffset());
        paramMap.put("pageSize", pageable.getPageSize());

        List<Map<String, Object>> content = noticeMapper.list(paramMap);
        int cnt = noticeMapper.getListNoticeCount();

        return new PageImpl<>(content, pageable, cnt);
    }

    public NoticeInfoDetailDto listDetailNotice(int noticeId) {
        return noticeMapper.listDetailNotice(noticeId);
    }

    @Transactional
    public int updateNotice(NoticeDto noticeDto) {
        return noticeMapper.updateNotice(noticeDto);
    }

    @Transactional
    public int deleteNotice(int noticeId, int memberId) {
        return noticeMapper.deleteNotice(noticeId, memberId);
    }
}
