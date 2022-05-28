package com.June.Common.Vo.UserVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * June
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Owner {

    private Long id;

    private String nickname;

    private String headphoto;

    private String role;

}
