package com.mx.common.datamodule.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 定时任务调度日志表
 * </p>
 *
 * @author mx
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 任务日志ID
     */
      @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /**
     * 请求目标
     */
    private String target;

    /**
     * 请求数据
     */
    private String data;

    /**
     * 访问令牌
     */
    private String token;

    /**
     * 备注
     */
    private String remark;

    /**
     * 日志信息
     */
    private String message;

    /**
     * 执行状态（0正常 1失败）
     */
    private String status;

    /**
     * 异常信息
     */
    private String exceptionInfo;

    /**
     * 创建时间
     */
    private Date createTime;


}
