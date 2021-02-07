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
public class Unit implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 单位id
     */
      @TableId(value = "unit_id", type = IdType.AUTO)
    private Integer unitId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 小单位
     */
    private String smallUnit;

    /**
     * 大单位
     */
    private String bigUnit;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 创建时间
     */
    @TableField("updateTime")
    private Date updateTime;


}
