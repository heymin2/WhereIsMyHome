package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.NoticeInfoDetailDto;
import com.ssafy.home.domain.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    void createNotice(NoticeDto noticeDto);
    List<Map<String, Object>> list(Map<String, Object> paramMap);
    int getListNoticeCount();
    NoticeInfoDetailDto listDetailNotice(int noticeId);
    int updateNotice(NoticeDto noticeDto);
    int deleteNotice(int noticeId, int memberId);
}

