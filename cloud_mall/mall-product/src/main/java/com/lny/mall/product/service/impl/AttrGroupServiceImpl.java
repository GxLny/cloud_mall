package com.lny.mall.product.service.impl;

import com.lny.mall.product.entity.AttrEntity;
import com.lny.mall.product.service.AttrService;
import com.lny.mall.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lny.common.utils.PageUtils;
import com.lny.common.utils.Query;

import com.lny.mall.product.dao.AttrGroupDao;
import com.lny.mall.product.entity.AttrGroupEntity;
import com.lny.mall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Resource
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        //如果id == 0，就是查询所有数据分类
        if(catelogId == 0){
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    new QueryWrapper<AttrGroupEntity>());
            return new PageUtils(page);
        }else{
            // todo 需要优化！目前完成 当点击到三级分类才会触发查询条件，后前改进为可以全局搜索
            QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId);
            //条件查询判断
            String key = (String)params.get("key");
            if(!StringUtils.isEmpty(key)){
                queryWrapper.and((obj)->{
                    obj.eq("attr_group_id",key).or().like("attr_group_name",key);
                });
            }
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    queryWrapper);
            return new PageUtils(page);
        }

    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        //查询出属性分组信息
        List<AttrGroupEntity> groupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        //查询出属性信息, 通过vo返回
        List<AttrGroupWithAttrsVo> attrsVos = groupEntities.stream().map((groupEntity) -> {
            AttrGroupWithAttrsVo withAttrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(groupEntity, withAttrsVo);
            Long attrGroupId = groupEntity.getAttrGroupId();
            List<AttrEntity> relationAttr = attrService.getRelationAttr(groupEntity.getAttrGroupId());
            withAttrsVo.setAttrs(relationAttr);
            return withAttrsVo;
        }).collect(Collectors.toList());

        return attrsVos;
    }

}