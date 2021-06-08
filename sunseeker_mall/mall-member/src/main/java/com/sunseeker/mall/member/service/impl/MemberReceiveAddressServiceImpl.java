package com.sunseeker.mall.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.Query;

import com.sunseeker.mall.member.dao.MemberReceiveAddressDao;
import com.sunseeker.mall.member.entity.MemberReceiveAddressEntity;
import com.sunseeker.mall.member.service.MemberReceiveAddressService;


@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressDao, MemberReceiveAddressEntity> implements MemberReceiveAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberReceiveAddressEntity> page = this.page(
                new Query<MemberReceiveAddressEntity>().getPage(params),
                new QueryWrapper<MemberReceiveAddressEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MemberReceiveAddressEntity> getAddressByUserId(Long userId) {
        return this.list(new QueryWrapper<MemberReceiveAddressEntity>().eq("member_id", userId));
    }

    @Override
    public List<MemberReceiveAddressEntity> getAddressByCurrentUser(Long userId) {
        return this.list(new QueryWrapper<MemberReceiveAddressEntity>().eq("member_id", userId));
    }


}