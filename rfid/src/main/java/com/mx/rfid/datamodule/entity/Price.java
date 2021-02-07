package com.mx.rfid.datamodule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mx
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Price implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 价格
     */
      @TableId(value = "price_id", type = IdType.AUTO)
    private Integer priceId;

    /**
     * 价格值
     */
    private String priceValue;

    /**
     * 价格描述
     */
    private String priceInfo;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;


}
