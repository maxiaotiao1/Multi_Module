package com.mx.rfid.datamodule.entity;

import java.math.BigDecimal;
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
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 商品id
     */
      @TableId(value = "prodect_id", type = IdType.AUTO)
    private Integer prodectId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品信息
     */
    private String productInfo;

    /**
     * 单位id
     */
    private String unitId;

    /**
     * 品牌id
     */
    private Integer branchId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 尺寸
     */
    private String size;

    /**
     * 质量
     */
    private String weight;

    /**
     * 规格
     */
    private String standard;

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
