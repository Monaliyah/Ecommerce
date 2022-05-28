package com.June.User.service.impl;

import com.June.Common.Utils.MD5Util;
import com.June.Common.Utils.jwtUtil;
import com.June.Common.Vo.UserVo.Owner;
import com.June.Common.Vo.UserVo.UserInfoVo;
import com.June.Common.Vo.UserVo.UserLoginVo;
import com.June.Common.pojo.User;
import com.June.Feign.Vo.RespBean;
import com.June.Feign.Vo.RespBeanEnum;
import com.June.User.mapper.AddressMapper;
import com.June.User.mapper.UserMapper;
import com.June.User.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author June
 * @since 2022-05-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    @Resource
    private UserMapper userMapper;
    @Autowired
    @Resource
    private AddressMapper addressMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(Long id,String password){
        UserLoginVo userLoginVo = userMapper.getUserLoginById(id);
        if(null== userLoginVo){
            return RespBean.error(RespBeanEnum.NULL_USER);
        }
        if(!userLoginVo.getPassword().equals(MD5Util.SecondCode(password, userLoginVo.getSalt()))){
            return RespBean.error(RespBeanEnum.PASSWORD_ERROR);
        }
        Long permission= userLoginVo.getId();
        String token=jwtUtil.getJwtToken(permission);
        redisTemplate.opsForValue().set("token:"+token,permission,100, TimeUnit.MINUTES);
        return RespBean.success(token);
    }



    @Override
    public RespBean getUserHeader(String token, Long id){
        Owner owner=userMapper.getUserHeader(id);
        return RespBean.success(token,owner);
    }

    @Override
    public RespBean getUserInfoVo(String token, Long id){
        UserInfoVo userInfoVo=userMapper.getUserInfoVo(id);
        return RespBean.success(token,userInfoVo);
    }

    @Override
    public Owner getOwner(Long id){
        Owner owner=userMapper.getUserHeader(id);
        return owner;
    }
}
