package com.June.Common.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author June
 * @since 2022-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

//    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickname;

    private String password;

    private String salt;

    private String role;

    private String name;

    private String headphoto;

    private String sex;

    private Integer defaultaddress;

    private Long telephone;


}
