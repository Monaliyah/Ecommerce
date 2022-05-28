package com.June.Common.Vo.UserVo;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author June
 * @since 2022-05-12
 */
@Data
public class UserLoginVo {

    private Long id;

    private String nickname;

    private String password;

    private String salt;
}
