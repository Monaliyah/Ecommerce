package com.June.Admin.mapper;

import com.June.Admin.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author June
 * @since 2022-05-12
 */
public interface AdminMapper extends BaseMapper<Admin> {

    Admin getById(Long id);
}
