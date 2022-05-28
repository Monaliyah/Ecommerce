package com.June.Feign.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private long code;
    private String message;
    private String token;
    private Object obj;

    public static RespBean success(){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMassage(),null,null);
    }
    public static RespBean success(String token) {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMassage(),token,null);
    }
    public static RespBean success(Object obj) {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMassage(),null,obj);
    }
    public static RespBean success(String token,Object obj) {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMassage(),token,obj);
    }


    public static RespBean error(RespBeanEnum respBeanEnum){
        return new RespBean(respBeanEnum.getCode(),respBeanEnum.getMassage(),null,null);
    }

    public static RespBean error(RespBeanEnum respBeanEnum, Object obj){
        return new RespBean(respBeanEnum.getCode(),respBeanEnum.getMassage(),null,obj);
    }
    public static RespBean error(RespBeanEnum respBeanEnum,String token){
        return new RespBean(respBeanEnum.getCode(),respBeanEnum.getMassage(),token,null);
    }
    public static RespBean error(RespBeanEnum respBeanEnum,String token,Object obj){
        return new RespBean(respBeanEnum.getCode(),respBeanEnum.getMassage(),token,obj);
    }

}
