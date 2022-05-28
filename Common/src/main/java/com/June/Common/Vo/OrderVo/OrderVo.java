package com.June.Common.Vo.OrderVo;

import com.June.Common.Vo.GoodsVo.GoodsForOrder;
import com.June.Common.pojo.Orders;
import lombok.Data;

/**
 * June
 */
@Data
public class OrderVo {
    private Orders orders;
    private GoodsForOrder goodsForOrder;
}
