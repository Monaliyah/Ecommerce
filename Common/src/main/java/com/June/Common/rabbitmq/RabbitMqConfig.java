package com.June.Common.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列定义&&交换机绑定
 * June
 */
@Configuration
public class RabbitMqConfig {

    private static final String QUEUE_doSecKillGoods = "doSecKillGoodsQueue";
    private static final String EXCHANGE_doSecKillGoods = "doSecKillGoodsExchange";

    /**
     * 获取队列
     */
    @Bean
    public Queue Queue_doSecKillGoods() {
        return new Queue(QUEUE_doSecKillGoods);
    }
    /**
     * 获取交换机
     */
    @Bean
    public TopicExchange TopicExchange_doSecKillGoods() {return new TopicExchange(EXCHANGE_doSecKillGoods);}

    /**
     * 绑定交换机
     */
    @Bean
    public Binding Binding_OptCoursesChoose() {
        return BindingBuilder.bind(Queue_doSecKillGoods()).to(TopicExchange_doSecKillGoods()).with("doSecKillGoods.#");
    }
}
