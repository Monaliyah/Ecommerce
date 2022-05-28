package com.June.Feign.Vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 公共返回对象
 * 枚举
 * June
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    /**
     * 通用
     */
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),

    /**
     * 登录
     */
    NULL_USER(500001,"用户不存在"),
    PASSWORD_ERROR(500002,"密码错误"),

    /**
     * Goods
     */
    NULL_GOODS(500011,"商品不存在")

    ;

    private final Integer code;
    private final String massage;
}
