package com.sunseeker.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.member.entity.MemberEntity;
import com.sunseeker.mall.member.vo.MemberLoginVo;
import com.sunseeker.mall.member.vo.MemberRegisterVo;

import java.util.Map;

/**
 * 会员
 *
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 22:49:02
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void register(MemberRegisterVo registerVo);

    MemberEntity login(MemberLoginVo loginVo);

    void updateMember(MemberEntity member);
}

