package com.lny.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.product.entity.AttrEntity;
import com.lny.mall.product.vo.AttrGroupRelationVo;
import com.lny.mall.product.vo.AttrRespVo;
import com.lny.mall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:41
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存商品属性，并保存关联关系
     * @param attrVo
     */
    void saveAttr(AttrVo attrVo);

    /**
     * 规格参数查询
     * @param params
     * @param catelogId
     * @param attrType
     * @return
     */
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    /**
     * 查询分组信息
     * @param attrId
     * @return
     */
    AttrRespVo getAttrInfo(Long attrId);

    void updateAttrById(AttrVo attr);

    /**
     * 根据分组id找到关联的所有属性
     * @param attrgroupId
     * @return
     */
    List<AttrEntity> getRelationAttr(Long attrgroupId);

    /**
     * 移除属性分组关联关系
     * @param vos
     */
    void deleteRelation(AttrGroupRelationVo[] vos);
}

