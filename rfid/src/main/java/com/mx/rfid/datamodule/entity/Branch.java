package com.mx.rfid.datamodule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Branch implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 品牌id
     */
      @TableId(value = "branch_id", type = IdType.AUTO)
    private Integer branchId;

    /**
     * 品牌名
     */
    private String branchName;

    /**
     * 品牌介绍
     */
    private String branchInfo;


}
