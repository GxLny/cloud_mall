package com.lny.mall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.omg.CORBA.IDLType;

/**
 * spu描述信息集
 * 
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:40
 */
@Data
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId(type = IdType.INPUT)
	private Long spuId;
	/**
	 * 描述信息
	 */
	private String decript;

}
