package com.June.Common.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author June
 * @since 2022-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer addressid;

    private Long userid;

    private String address;

    private Long telephone;

    private String username;


}
