package com.June.User.service;

import com.June.Common.Vo.UserVo.Owner;
import com.June.Common.pojo.User;
import com.June.Feign.Vo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author June
 * @since 2022-05-19
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(Long id, String password);



    RespBean getUserHeader(String token, Long id);

    RespBean getUserInfoVo(String token, Long id);

    Owner getOwner(Long id);
}
