/** Copyright 2020 bejson.com */
package com.lny.common.to;


import lombok.Data;

import java.math.BigDecimal;

/**
 * 会员价
 */

@Data
public class MemberPrice {

  private Long id;
  private String name;
  private BigDecimal price;

}
