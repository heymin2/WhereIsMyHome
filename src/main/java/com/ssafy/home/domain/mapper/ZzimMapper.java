package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.ZzimDto;
import com.ssafy.home.domain.dto.ZzimInfoDto;
import com.ssafy.home.domain.dto.ZzimItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZzimMapper {

     // 아파트
     void likeApt(@Param("member_id") int memberId, @Param("apt_id") int aptId);
     int check(@Param("member_id") int memberId, @Param("apt_id") int aptId);
     int delete(@Param("member_id") int memberId, @Param("apt_id") int aptId);
     List<ZzimDto> search(@Param("member_id") int memberId);
     int aptCnt(@Param("apt_id") int aptId);

     // 매물
     void likeItem(@Param("member_id") int memberId, @Param("item_id") int itemId);
     int checkItem(@Param("member_id") int memberId, @Param("item_id") int itemId);
     int deleteItem(@Param("member_id") int memberId, @Param("item_id") int itemId);
     List<ZzimItemDto> searchItem(@Param("member_id") int memberId);
     int itemCnt(@Param("item_id") int itemId);
}
