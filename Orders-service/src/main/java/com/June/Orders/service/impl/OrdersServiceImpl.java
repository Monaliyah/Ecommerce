package com.June.Orders.service.impl;

import com.June.Common.Vo.GoodsVo.GoodsVo;
import com.June.Common.Vo.MQMessage.doSecKillGoodsMessage;
import com.June.Common.Vo.OrderVo.OrderVo;
import com.June.Common.pojo.Orders;
import com.June.Feign.Vo.RespBean;
import com.June.Feign.Vo.RespBeanEnum;
import com.June.Feign.client.GoodsClient;
import com.June.Orders.mapper.OrdersMapper;
import com.June.Orders.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author June
 * @since 2022-05-22
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    @Resource
    private OrdersMapper ordersMapper;
    @Autowired
    private GoodsClient goodsClient;

    @Override
    public RespBean AddOrder(Long id,String token, Long goodsid, Integer addressid, Integer goodsnum, BigDecimal payprice){
        if(goodsClient.decGoodsStock(goodsid,goodsnum)!=1){
            return RespBean.error(RespBeanEnum.ERROR);
        }
        Orders orders =new Orders();
        orders.setOrderid(ordersMapper.getNewOrderid()==null?1000000000: ordersMapper.getNewOrderid()+1);
        orders.setUserid(id);
        orders.setAddressid(addressid);
        orders.setGoodsid(goodsid);
        orders.setGoodsnum(goodsnum);
        orders.setStatus("order");
        orders.setBuydate(new Date());
        orders.setPaytime(new Date());
        orders.setPayprice(payprice);
        ordersMapper.insert(orders);
        return RespBean.success(token,orders.getOrderid());
    }

    @Override
    public RespBean getOrder(String token, Long id){
        List<Orders> orders=ordersMapper.getOrder(id);
        List<OrderVo> orderVos=new ArrayList<>();
        for(int i=0;i<orders.size();i++) {
            GoodsVo goodsVo = goodsClient.getGoodsVo(orders.get(i).getGoodsid());
            OrderVo orderVo = new OrderVo();
            orderVo.setGoodsVo(goodsVo);
            orderVo.setOrders(orders.get(i));
            orderVos.add(orderVo);
        }
        return RespBean.success(token,orderVos);
    }

    @Override
    public void deSecKillGoods(doSecKillGoodsMessage doSecKillGoodsMessage){
        Orders orders =new Orders();
        orders.setOrderid(ordersMapper.getNewOrderid()==null?1000000000: ordersMapper.getNewOrderid()+1);
        orders.setUserid(doSecKillGoodsMessage.getId());
        orders.setAddressid(doSecKillGoodsMessage.getAddressid());
        orders.setGoodsid(doSecKillGoodsMessage.getGoodsid());
        orders.setGoodsnum(doSecKillGoodsMessage.getGoodsnum());
        orders.setStatus("order");
        orders.setBuydate(new Date());
        orders.setPaytime(new Date());
        orders.setPayprice(doSecKillGoodsMessage.getPayprice());
        ordersMapper.insert(orders);
    }
}
