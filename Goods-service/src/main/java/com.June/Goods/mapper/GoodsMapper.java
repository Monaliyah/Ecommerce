package com.June.Goods.mapper;

import com.June.Common.Vo.GoodsVo.GoodsForOrder;
import com.June.Common.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author June
 * @since 2022-05-20
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    Goods getGoodsByGoodsId(Long id);

    GoodsForOrder getGoodsVo(Long id);

    List<Goods> getSecKillGoods();

    List<Goods> getGoodsByUserId(Long id);
}
