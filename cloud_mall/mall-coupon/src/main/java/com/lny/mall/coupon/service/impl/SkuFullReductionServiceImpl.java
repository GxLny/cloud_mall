package com.lny.mall.coupon.service.impl;

import com.lny.common.to.MemberPrice;
import com.lny.common.to.SkuReductionTo;
import com.lny.mall.coupon.entity.MemberPriceEntity;
import com.lny.mall.coupon.entity.SkuLadderEntity;
import com.lny.mall.coupon.service.MemberPriceService;
import com.lny.mall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lny.common.utils.PageUtils;
import com.lny.common.utils.Query;

import com.lny.mall.coupon.dao.SkuFullReductionDao;
import com.lny.mall.coupon.entity.SkuFullReductionEntity;
import com.lny.mall.coupon.service.SkuFullReductionService;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {
    @Autowired
    private SkuLadderService skuLadderService;

    @Autowired
    private MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkuReductionTo skuReductionTo) {
        //1、保存满减打折、会员价
        //1、1）、sku的优惠、满减等信息：gulimall_sms--->sms_sku_ladder、sms_sku_full_reduction、sms_member_price
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        BeanUtils.copyProperties(skuReductionTo,skuLadderEntity);
        skuLadderEntity.setAddOther(skuReductionTo.getCountStatus());
        //如果有满减、打折才保存
        if (skuReductionTo.getFullCount() > 0) {
            skuLadderService.save(skuLadderEntity);
        }

        //2、sms_sku_full_reduction
        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuReductionTo,skuFullReductionEntity);
        if (skuFullReductionEntity.getFullPrice().compareTo(BigDecimal.ZERO) == 1) {
            this.save(skuFullReductionEntity);
        }

        //3、sms_member_price
        List<MemberPrice> memberPrice = skuReductionTo.getMemberPrice();

        List<MemberPriceEntity> collect = memberPrice.stream().map(mem -> {
            MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
            memberPriceEntity.setSkuId(skuReductionTo.getSkuId());
            memberPriceEntity.setMemberLevelId(mem.getId());
            memberPriceEntity.setMemberLevelName(mem.getName());
            memberPriceEntity.setMemberPrice(mem.getPrice());
            memberPriceEntity.setAddOther(1);
            return memberPriceEntity;
        }).filter(item -> {
            //过滤没有填入的会员价格
            return item.getMemberPrice().compareTo(BigDecimal.ZERO) == 1;
        }).collect(Collectors.toList());

        memberPriceService.saveBatch(collect);
    }

}