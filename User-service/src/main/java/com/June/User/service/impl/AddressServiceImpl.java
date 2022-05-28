package com.June.User.service.impl;

import com.June.Common.pojo.Address;
import com.June.Feign.Vo.RespBean;
import com.June.Feign.Vo.RespBeanEnum;
import com.June.User.mapper.AddressMapper;
import com.June.User.service.IAddressService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Autowired
    private IAddressService addressService;
    @Autowired
    @Resource
    private AddressMapper addressMapper;

    @Override
    public RespBean getAddresses(String token, Long id){
        List<Address> addresses=addressMapper.getAddressesById(id);
        return RespBean.success(token,addresses);
    }

    @Override
    public RespBean modifyAddress(Long id, String token, Integer addressid, String address,Long telephone, String username){

        boolean result = addressService.update(new UpdateWrapper<Address>().setSql("address=" + address)
                .setSql("telephone=" + telephone).setSql("username="+username)
                .eq("userid",id).eq("addressid", addressid));
        if(!result){
            return RespBean.error(RespBeanEnum.ERROR);
        }
        return RespBean.success(token);
    }

    @Override
    public RespBean addAddress(Long id, String token, String address, Long telephone, String username){
        Address newaddress=new Address();
        newaddress.setUserid(id);
        newaddress.setAddressid(addressMapper.getNewAddressid(id)==null?1:addressMapper.getNewAddressid(id)+1);
        newaddress.setAddress(address);
        newaddress.setTelephone(telephone);
        newaddress.setUsername(username);
        addressMapper.insert(newaddress);
        return RespBean.success(token,addressMapper.getNewAddressid(id));
    }
}
