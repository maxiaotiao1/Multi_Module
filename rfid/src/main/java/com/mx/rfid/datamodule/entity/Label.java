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
public class Label implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 标签类型id
     */
      @TableId(value = "label_type_id", type = IdType.AUTO)
    private Integer labelTypeId;

    /**
     * 标签值
     */
    private String labelTypeValue;

    /**
     * 标签信息
     */
    private String labelTypeInfo;

    /**
     * 更新时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;


}
