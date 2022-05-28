package com.June.Orders.service;

import com.June.Common.Vo.MQMessage.doSecKillGoodsMessage;
import com.June.Common.pojo.Orders;
import com.June.Feign.Vo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author June
 * @since 2022-05-22
 */
public interface IOrdersService extends IService<Orders> {

    RespBean AddOrder(Long id, String token, Long goodsid, Integer addressid, Integer goodsnum, BigDecimal payprice);

    RespBean getOrder(String token, Long id);

    void deSecKillGoods(doSecKillGoodsMessage doSecKillGoodsMessage);
}
