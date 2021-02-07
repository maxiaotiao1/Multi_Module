package com.mx.common.datamodule.log.service.impl;

import com.mx.common.datamodule.log.entity.SysLog;
import com.mx.common.datamodule.log.mapper.SysLogMapper;
import com.mx.common.datamodule.log.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务调度日志表 服务实现类
 * </p>
 *
 * @author mx
 * @since 2021-01-28
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
