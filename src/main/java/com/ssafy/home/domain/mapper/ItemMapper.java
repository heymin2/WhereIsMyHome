package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMapper {

    void insertItem(ItemDto itemDto);
    void insertImg(@Param("item_id") int itemId, @Param("url") String url);
    int deleteItem(@Param("item_id") int itemId, @Param("member_id") int memberId);
    ItemDto search(@Param("item_id") int itemId, @Param("member_id") int memberId);
    void sold(@Param("item_id") int itemId, @Param("member_id") int memberId, @Param("sold") boolean sold);
}
