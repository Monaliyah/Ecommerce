package com.June.Admin.pojo;

import com.June.Feign.pojo.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * June
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AA {

    private Admin admin;
    private User user;
}
