package com.June.Common.Vo.MQMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * June
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class doSecKillGoodsMessage {
    private Long id;
    private String token;
    private Long goodsid;
    private Integer addressid;
    private Integer goodsnum;
    private BigDecimal payprice;
}
