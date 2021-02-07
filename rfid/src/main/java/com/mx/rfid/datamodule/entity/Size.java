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
public class Size implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 尺寸id
     */
      @TableId(value = "size_id", type = IdType.AUTO)
    private Integer sizeId;

    /**
     * 宽
     */
    private String sizeWidth;

    /**
     * 宽
     */
    private String sizeLength;

    /**
     * 高
     */
    private String sizeHeight;

    /**
     * 备注
     */
    private String sizeInfo;

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
