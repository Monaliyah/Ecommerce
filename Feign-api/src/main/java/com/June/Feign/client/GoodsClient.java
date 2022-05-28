package com.June.Feign.client;

import com.June.Common.Vo.GoodsVo.GoodsVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("goodsservice")
public interface GoodsClient {

    @RequestMapping(value="/Goods/decGoodsStock/{id}/{num}",method = RequestMethod.POST)
    int decGoodsStock(@PathVariable Long id,@PathVariable Integer num);

    @RequestMapping(value = "/Goods/getGoodsVo/{id}",method = RequestMethod.POST)
    GoodsVo getGoodsVo(@PathVariable Long id);
}
