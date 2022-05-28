package com.June.Orders.rabbitmq;

import com.June.Common.Utils.JsonUtil;
import com.June.Common.Vo.MQMessage.doSecKillGoodsMessage;
import com.June.Orders.service.IOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息队列接收接口
 * June
 */
@Service
@Slf4j
public class MQReceiver {

    @Autowired
    private IOrdersService ordersService;

    /**
     * 接收消息并调用函数写入mysql
     */
    @RabbitListener(queues = "doSecKillGoodsQueue")
    public void Receive_doSecKillGoods(String message) {
        log.info("接收到信息：" + message);
        doSecKillGoodsMessage doSecKillGoodsMessage = JsonUtil.JsonStrToObject(message, doSecKillGoodsMessage.class);
        ordersService.deSecKillGoods(doSecKillGoodsMessage);
    }




}