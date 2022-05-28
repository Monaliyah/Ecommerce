package com.June.Common.Vo.OrderVo;

import com.June.Common.Vo.GoodsVo.GoodsVo;
import com.June.Common.pojo.Orders;
import lombok.Data;

/**
 * June
 */
@Data
public class OrderVo {
    private Orders orders;
    private GoodsVo goodsVo;
}
