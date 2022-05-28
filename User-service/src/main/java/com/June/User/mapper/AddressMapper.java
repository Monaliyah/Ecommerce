package com.June.User.mapper;

import com.June.Common.pojo.Address;
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
public interface AddressMapper extends BaseMapper<Address> {

    Address getAddressById(Long id, Integer defaultaddress);

    List<Address> getAddressesById(Long id);

    Integer getNewAddressid(Long id);
}
