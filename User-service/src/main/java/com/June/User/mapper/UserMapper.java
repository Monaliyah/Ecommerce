package com.June.User.mapper;

import com.June.Common.Vo.UserVo.Owner;
import com.June.Common.Vo.UserVo.UserInfoVo;
import com.June.Common.Vo.UserVo.UserLoginVo;
import com.June.Common.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author June
 * @since 2022-05-19
 */
public interface UserMapper extends BaseMapper<User> {

    UserLoginVo getUserLoginById(Long id);

    User getOneUser(Long id);

    Owner getUserHeader(Long id);

    UserInfoVo getUserInfoVo(Long id);
}
