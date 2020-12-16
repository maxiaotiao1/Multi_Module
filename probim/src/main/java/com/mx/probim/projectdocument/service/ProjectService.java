package com.mx.probim.projectdocument.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mx.probim.projectdocument.entity.Project;

import java.util.List;

public interface ProjectService extends IService<Project> {
    //项目列表
    List<Project> listProject();
}
