package com.ssafy.home.domain.service;

import com.ssafy.home.domain.mapper.ZzimMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZzimService {

    private final ZzimMapper zzimMapper;

    public ZzimService(ZzimMapper zzimMapper) {
        this.zzimMapper = zzimMapper;
    }

    @Transactional
    public boolean likeApt(int memberId, int aptId) {
        int cnt = zzimMapper.check(memberId, aptId);
        if(cnt == 0){
            zzimMapper.likeApt(memberId, aptId);
            return true;
        }
        return false;
    }

    @Transactional
    public int delete(int memberId, Integer aptId) {
        return zzimMapper.delete(memberId, aptId);
    }
}
