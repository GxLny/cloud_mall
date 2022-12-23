package com.lny.mall.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.lny.common.valid.AddGroup;
import com.lny.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lny.mall.product.entity.BrandEntity;
import com.lny.mall.product.service.BrandService;
import com.lny.common.utils.PageUtils;
import com.lny.common.utils.R;

import javax.validation.Valid;


/**
 * 品牌
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:23:40
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     * @Valid 开启规则校验功能
     * @Validated(AddGroup.class) 校验分组，在新增时进行校验
     */
    @RequestMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody BrandEntity brand){
        //通过统一异常类管理
//BindingResult result
//         Map<String,String> map = new HashMap<>();
//         if (result.hasErrors()) {
//             //获取效验错误结果
//             result.getFieldErrors().forEach((item)-> {
//                 //获取到错误提示
//                 String message = item.getDefaultMessage();
//                 //获取错误的属性的名字
//                 String field = item.getField();
//                 map.put(field,message);
//             });
//             return R.error(400,"提交的数据不合法").put("data",map);
//         } else {
//             brandService.save(brand);
//         }
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     * 校验分组
     */
    @RequestMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
