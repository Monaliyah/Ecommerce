package com.June.Goods.controller;


import com.June.Common.Vo.GoodsVo.GoodsForOrder;
import com.June.Common.pojo.Goods;
import com.June.Feign.Vo.RespBean;
import com.June.Goods.service.IGoodsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author June
 * @since 2022-05-20
 */
@Controller
@RequestMapping("/Goods")
public class GoodsController implements InitializingBean {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;

    private Map<Long,Boolean> EmptyStockMap=new HashMap<>();

    @RequestMapping(value="/getGoodsByUserId",method = RequestMethod.POST)
    @ResponseBody
    public RespBean getGoodsByUserId(@RequestHeader("id")Long id) {
        System.out.println(id);
        return goodsService.getGoodsByUserId(id);
    }

    @RequestMapping(value="/getGoodsDetail",method = RequestMethod.POST)
    @ResponseBody
    public RespBean getGoodsDetail(@RequestParam("id")  Long id) {
        return goodsService.getGoodsDetailByGoodsId(id);
    }

    @RequestMapping(value="/decGoodsStock/{id}/{num}",method = RequestMethod.POST)
    @ResponseBody
    public int decGoodsStock(@PathVariable Long id,@PathVariable Integer num){ return goodsService.decGoodsStock(id,num); }

    @RequestMapping(value = "/getGoodsVo/{id}",method = RequestMethod.POST)
    @ResponseBody
    public GoodsForOrder getGoodsVo(@PathVariable Long id){ return goodsService.getGoodsVo(id); }

    @RequestMapping(value = "/getSecKillGoods",method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSecKillGoods(@RequestHeader("id") Long id, @RequestHeader("token")String token,
                                   @RequestParam("goodsid") Long goodsid, @RequestParam("addressid") Integer addressid,
                                   @RequestParam("goodsnum") Integer goodsnum, @RequestParam("payprice") BigDecimal payprice){
        return goodsService.doSecKillGoods(id,token,goodsid,addressid,goodsnum,payprice,EmptyStockMap);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        List<Goods> list = goodsService.getSecKillGoods();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        list.forEach(goods -> {
            redisTemplate.opsForValue().set("GoodsLast:" + goods.getGoodsid(), goods.getGoodsstock());
            EmptyStockMap.put(goods.getGoodsid(), false);
        });
    }
}
