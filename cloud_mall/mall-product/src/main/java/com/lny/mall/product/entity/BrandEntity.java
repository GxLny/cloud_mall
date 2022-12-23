package com.lny.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.lny.common.valid.AddGroup;
import com.lny.common.valid.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:41
 *
 * 默认没有指定分组的校验注解@NotEmpty，在分组校验情况下不生效，只会在@Validated生效
 *
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 * groups = {UpdateGroup.class} 在修改时校验规则
	 */
	@NotNull(message = "新增不能指定id",groups = {UpdateGroup.class})
	@Null(message = "修改必须指定品牌id",groups = {AddGroup.class})
	@TableId
	private Long brandId;
	/**
	 * 品牌名
	 * 不能为空
	 */
	@NotEmpty
	@NotBlank(message = "品牌名不能为空",groups = {UpdateGroup.class,AddGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotEmpty(groups = {AddGroup.class})
	@URL(message = "图片地址必须是一个合法的url",groups = {UpdateGroup.class,AddGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotEmpty(groups = {AddGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母只能有一个")
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(groups = {AddGroup.class})
	@Min(value = 0,message = "排序必须大于等于0")
	private Integer sort;

}
