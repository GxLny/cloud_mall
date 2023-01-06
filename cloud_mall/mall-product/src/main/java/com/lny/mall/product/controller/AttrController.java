package com.lny.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lny.mall.product.entity.ProductAttrValueEntity;
import com.lny.mall.product.service.ProductAttrValueService;
import com.lny.mall.product.vo.AttrRespVo;
import com.lny.mall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lny.mall.product.entity.AttrEntity;
import com.lny.mall.product.service.AttrService;
import com.lny.common.utils.PageUtils;
import com.lny.common.utils.R;



/**
 * 商品属性
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:23:40
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    /**
     * 格参数查询
     */
    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseList(@RequestParam Map<String, Object> params,
                      @PathVariable("catelogId") Long catelogId,
                      @PathVariable("attrType") String attrType){

        PageUtils page = attrService.queryBaseAttrPage(params,catelogId,attrType);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
//		AttrEntity attr = attrService.getById(attrId);
        AttrRespVo attr = attrService.getAttrInfo(attrId);
        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody AttrVo attrVo){
        attrService.saveAttr(attrVo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr){
		attrService.updateAttrById(attr);

        return R.ok();
    }

    /**
     * 更新规格参数
     * @param spuId
     * @param entities
     * @return
     */
    @PostMapping("/update/{spuId}")
    //@RequiresPermissions("product:attr:update")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities){

        productAttrValueService.updateSpuAttr(spuId,entities);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
