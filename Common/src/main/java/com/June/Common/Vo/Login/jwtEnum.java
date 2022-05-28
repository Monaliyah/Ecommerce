package com.June.Common.Vo.Login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * June
 */
@Getter
@ToString
@AllArgsConstructor
public enum jwtEnum {

    TRUE(200),

    NULL_TOKEN(401),//无token
    EXPIRE(402),//过期
    CHANGED(403),//不一致
    UNKNOWN(404);

    private int state;

}