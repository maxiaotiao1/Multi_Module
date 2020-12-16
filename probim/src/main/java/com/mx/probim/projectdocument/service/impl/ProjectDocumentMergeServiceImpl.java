package com.mx.probim.projectdocument.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mx.probim.projectdocument.dao.ProjectDocumentMergeDao;
import com.mx.probim.projectdocument.entity.Document;
import com.mx.probim.projectdocument.entity.ProjectDocumentMerge;
import com.mx.probim.projectdocument.service.ProjectDocumentMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectDocumentMergeServiceImpl extends ServiceImpl<ProjectDocumentMergeDao, ProjectDocumentMerge> implements ProjectDocumentMergeService {

    @Autowired
    ProjectDocumentMergeDao projectDocumentMergeDao;

    @Override
    public List<Document> findRootFolderListByProjectId(Long projectId) {
        return projectDocumentMergeDao.findFolderListByProjectId(projectId);
    }

    @Override
    public List<Document> findRootDocumentListByProjectId(Long projectId) {
        return projectDocumentMergeDao.findDocumentListByProjectId(projectId);
    }

}
