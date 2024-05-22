package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.ItemDto;
import com.ssafy.home.domain.dto.ItemCoordDto;
import com.ssafy.home.domain.request.CoordinateRangeRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

    void insertItem(ItemDto itemDto);
    void insertImg(@Param("item_id") int itemId, @Param("url") String url);
    int deleteItem(@Param("item_id") int itemId, @Param("member_id") int memberId);
    ItemDto soldSearch(@Param("item_id") int itemId, @Param("member_id") int memberId);
    void sold(@Param("item_id") int itemId, @Param("member_id") int memberId, @Param("sold") boolean sold);
    List<ItemCoordDto> search(CoordinateRangeRequest request);
    List<ItemCoordDto> myItem(int itemId);
    List<ItemDto> searchDetail(int itemId);
}
