package com.sunseeker.mall.member.dao;

import com.sunseeker.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会员
 * 
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 22:49:02
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {

    void updateMember(MemberEntity member);
}
