package com.lny.mall.ware.feign;

import com.lny.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mall-product")
public interface ProductFeignService {
    /**
     *      /product/skuinfo/info/{skuId}
     *
     *  远程调用服务用法：
     *   1)、让所有请求过网关；
     *          1、@FeignClient("mall-gateway")：给mall-gateway所在的机器发请求
     *          2、/api/product/skuinfo/info/{skuId}
     *   2）、直接让后台指定服务处理
     *          1、@FeignClient("mall-product")
     *          2、/product/skuinfo/info/{skuId}
     *
     * @return
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId);
}
