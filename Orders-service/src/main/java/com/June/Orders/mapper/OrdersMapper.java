package com.June.Orders.mapper;

import com.June.Common.pojo.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author June
 * @since 2022-05-22
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    Long getNewOrderid();

    List<Orders> getOrder(Long id);
}
