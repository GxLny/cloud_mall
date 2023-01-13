package com.lny.mall.product.web;

import com.lny.mall.product.entity.CategoryEntity;
import com.lny.mall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;


    /**
     * 跳转到首页
     * @GetMapping({"/","index.html"}) 多个请求路径
     * @return
     */
    @GetMapping({"/","index.html"})
    public String getIndexPages(Model model){
        //查出所有一级分类
      List<CategoryEntity> categoryEntityList = categoryService.getLevel1Categorys();
      //储存到model中并返回到前端页面
        model.addAttribute("categories",categoryEntityList);
        //跳转到index页面
        return "index";
    }


}
