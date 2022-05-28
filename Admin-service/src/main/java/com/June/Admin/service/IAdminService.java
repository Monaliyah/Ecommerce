package com.June.Admin.service;

import com.June.Admin.pojo.AA;
import com.June.Admin.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author June
 * @since 2022-05-12
 */
public interface IAdminService extends IService<Admin> {

    AA getAA(Long id);
}
