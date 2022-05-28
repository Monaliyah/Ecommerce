package com.June.Admin.service.impl;

import com.June.Admin.mapper.AdminMapper;
import com.June.Admin.pojo.AA;
import com.June.Admin.pojo.Admin;
import com.June.Admin.service.IAdminService;
import com.June.Feign.client.UserClient;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author June
 * @since 2022-05-12
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {


    @Autowired
    @Resource
    private AdminMapper adminMapper;
    @Autowired
    private UserClient userClient;

    @Override
    public AA getAA(Long id) {

        Long uid = 1908010108L;
//        String url = "http://userservice/User/" + uid;
//        User user = restTemplate.getForObject(url, User.class);
//        User user=userClient.getById(uid);
//        AA aa=new AA();
//        aa.setAdmin(adminMapper.getById(id));
//        aa.setUser(user);
        return null;
    }

}
