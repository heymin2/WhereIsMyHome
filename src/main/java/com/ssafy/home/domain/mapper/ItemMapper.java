package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface ItemMapper {

    void insertItem(ItemDto itemDto);
    void insertImg(@Param("item_id") int itemId, @Param("url") String url);
}
