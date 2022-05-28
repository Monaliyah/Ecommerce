package com.June.Feign.client;


import com.June.Common.Vo.UserVo.Owner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("userservice")
public interface UserClient {

    @RequestMapping(value="/User/getOwner/{id}",method = RequestMethod.POST)
    Owner getOwner(@PathVariable Long id);
}
