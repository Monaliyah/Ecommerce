package com.June.User.service;

import com.June.Common.pojo.Address;
import com.June.Feign.Vo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author June
 * @since 2022-05-22
 */
public interface IAddressService extends IService<Address> {

    RespBean getAddresses(String token, Long id);

    RespBean modifyAddress(Long id, String token, Integer addressid, String address ,Long telephone, String username);

    RespBean addAddress(Long id, String token, String address, Long telephone, String username);
}
