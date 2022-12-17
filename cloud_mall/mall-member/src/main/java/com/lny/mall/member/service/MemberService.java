package com.lny.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:41:59
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

