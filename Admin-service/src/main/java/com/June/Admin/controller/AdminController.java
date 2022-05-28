package com.June.Admin.controller;


import com.June.Admin.pojo.AA;
import com.June.Admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author June
 * @since 2022-05-12
 */
@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;



    @RequestMapping("/{id}")
    public AA getAdmin(@PathVariable Long id){

        AA aa=adminService.getAA(id);
        return aa;
    }
}
