package com.June.Admin.pojo;

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
 * @since 2022-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String password;

    private String salt;


}
