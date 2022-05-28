package com.June.User.controller;


import com.June.Common.Vo.UserVo.Owner;
import com.June.Feign.Vo.RespBean;
import com.June.User.service.IAddressService;
import com.June.User.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author June
 * @since 2022-05-12
 */
@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IAddressService addressService;

    @RequestMapping(value="/toLogin",method = RequestMethod.POST)
    @ResponseBody
    public RespBean toLogin(@RequestParam("id")  Long id, @RequestParam("password") String password){ return userService.doLogin(id,password); }

    @RequestMapping(value="/getUserHeader",method = RequestMethod.POST)
    @ResponseBody
    public RespBean getUserHeader(@RequestHeader("id")Long id, @RequestHeader("token")String token){ return userService.getUserHeader(token,id); }

    @RequestMapping(value="/getOwner/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Owner getOwner(@PathVariable Long id){
        return userService.getOwner(id);
    }

    @RequestMapping(value="/getAddresses",method = RequestMethod.POST)
    @ResponseBody
    public RespBean getAddresses(@RequestHeader("id")Long id, @RequestHeader("token")String token){ return addressService.getAddresses(token,id); }

    @RequestMapping(value="/getUserInfoVo",method = RequestMethod.POST)
    @ResponseBody
    public RespBean getUserInfoVo(@RequestHeader("id")Long id, @RequestHeader("token")String token){ return userService.getUserInfoVo(token,id); }

    @RequestMapping(value="/modifyAddress",method = RequestMethod.POST)
    @ResponseBody
    public RespBean modifyAddress(@RequestHeader("id")Long id,@RequestHeader("token")String token,
                                  @RequestParam("addressid")Integer addressid,@RequestParam("address")String address,
                                  @RequestParam("telephone")Long telephone, @RequestParam("username")String username){
        return addressService.modifyAddress(id,token,addressid,address,telephone,username);
    }

    @RequestMapping(value="/addAddress",method = RequestMethod.POST)
    @ResponseBody
    public RespBean addAddress(@RequestHeader("id")Long id,@RequestHeader("token")String token,
                                  @RequestParam("address")String address,
                                  @RequestParam("telephone")Long telephone, @RequestParam("username")String username){
        return addressService.addAddress(id,token,address,telephone,username);
    }
}