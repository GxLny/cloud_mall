package com.lny.mall.member.dao;

import com.lny.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:41:59
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
