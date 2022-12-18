package com.lny.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.lny.mall.coupon.entity.CouponEntity;
import com.lny.mall.coupon.service.CouponService;
import com.lny.common.utils.PageUtils;
import com.lny.common.utils.R;



/**
 * 优惠券信息
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:54:42
 */
@RestController
@RefreshScope //开启自动刷新配置信息
@RequestMapping("coupon/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 引入依赖
     * 创建bootstarp文件，添加服务器名称，nacos服务地址
     * @RefreshScope 动态获取配置
     * 如果配置中心和当前应用都配置了相同的属性，则优先使用配置中心的
     * @return
     */
    @GetMapping("/serverPort")
    public R getNacosConfig(){

        return R.ok(serverPort);
    }


    /**
     * 测试远程调用接口
     * @return
     */
    @RequestMapping("/feign/list")
    public R feignList(){
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满10减3！！");
        return R.ok().put("coupon",couponEntity);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CouponEntity coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CouponEntity coupon){
		couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CouponEntity coupon){
		couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
