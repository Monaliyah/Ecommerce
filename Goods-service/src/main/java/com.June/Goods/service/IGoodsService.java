package com.June.Goods.service;

import com.June.Common.Vo.GoodsVo.GoodsForOrder;
import com.June.Common.pojo.Goods;
import com.June.Feign.Vo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author June
 * @since 2022-05-20
 */
public interface IGoodsService extends IService<Goods> {

    RespBean getGoodsByUserId(Long id);

    RespBean getGoodsDetailByGoodsId(Long id);

    int decGoodsStock(Long id,Integer num);

    GoodsForOrder getGoodsVo(Long id);

    List<Goods> getSecKillGoods();

    RespBean doSecKillGoods(Long id, String token, Long goodsid, Integer addressid, Integer goodsnum, BigDecimal payprice, Map<Long, Boolean> emptyStockMap);

}