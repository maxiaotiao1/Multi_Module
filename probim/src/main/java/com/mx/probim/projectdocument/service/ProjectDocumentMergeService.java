package com.mx.probim.projectdocument.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mx.probim.projectdocument.entity.Document;
import com.mx.probim.projectdocument.entity.ProjectDocumentMerge;

import java.util.List;

public interface ProjectDocumentMergeService extends IService<ProjectDocumentMerge> {

    //根据项目Id获取项目所有文件夹
    List<Document> findFolderListByProjectId(Long projectId);

    //根据项目Id获取项目文件（文件夹和文件）
    List<Document> findRootDocumentListByProjectId(Long projectId);
}
