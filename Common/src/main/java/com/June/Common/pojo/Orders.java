package com.June.Common.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderid;

    private Long userid;

    private Integer addressid;

    private Long goodsid;

    private Integer goodsnum;

    private String status;

    private Date buydate;

    private Date paytime;

    private BigDecimal payprice;


}
