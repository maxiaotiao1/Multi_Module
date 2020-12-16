package com.mx.probim.projectdocument.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mx.probim.projectdocument.dao.ProjectDao;
import com.mx.probim.projectdocument.entity.Project;
import com.mx.probim.projectdocument.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao,Project> implements ProjectService {

    @Autowired
    ProjectDao projectDao;

    @Override
    public List<Project> listProject() {
        return projectDao.listProject();
    }
}
