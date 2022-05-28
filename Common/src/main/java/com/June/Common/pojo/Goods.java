package com.June.Common.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author June
 * @since 2022-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long goodsid;

    private Long userid;

    private String name;

    private String detail;

    private Integer goodsstock;

    private String status;

    private BigDecimal price;


}
