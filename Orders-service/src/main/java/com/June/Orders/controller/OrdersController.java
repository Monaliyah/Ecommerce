package com.June.Orders.controller;


import com.June.Feign.Vo.RespBean;
import com.June.Orders.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author June
 * @since 2022-05-22
 */
@Controller
@RequestMapping("/Order")
public class OrdersController {

    @Autowired
    private IOrdersService orderService;

    @RequestMapping(value="/AddOrder",method = RequestMethod.POST)
    @ResponseBody
    public RespBean AddOrder(@RequestHeader("id") Long id, @RequestHeader("token")String token,
                             @RequestParam("goodsid") Long goodsid, @RequestParam("addressid") Integer addressid,
                             @RequestParam("goodsnum") Integer goodsnum, @RequestParam("payprice") BigDecimal payprice){
        return orderService.AddOrder(id,token,goodsid,addressid,goodsnum,payprice);
    }

    @RequestMapping(value="/getOrder",method = RequestMethod.POST)
    @ResponseBody
    public RespBean getOrder(@RequestHeader("id") Long id, @RequestHeader("token")String token){
        return orderService.getOrder(token,id);
    }

}
