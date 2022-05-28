package com.June.Common.Vo.GoodsVo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * June
 */
@Data
public class GoodsVo {
    private Long goodsid;

    private Long userid;

    private String name;

    private String detail;

    private BigDecimal price;

}
