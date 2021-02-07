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
public class Chip implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 电子标签id
     */
      @TableId(value = "chip_id", type = IdType.AUTO)
    private Integer chipId;

    /**
     * 芯片id
     */
    private String chipTid;

    /**
     * 标签类型id
     */
    private String labelTypeId;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 状态（0已入库，1已出库，-1异常）
     */
    private Integer status;

    /**
     * 商品数量
     */
    private Integer productCount;

    /**
     * 生成时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;


}
