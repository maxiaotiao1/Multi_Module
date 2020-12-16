package com.mx.probim.projectdocument.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mx.probim.projectdocument.entity.Document;
import com.mx.probim.projectdocument.res.DocumentRes;

import java.util.List;

public interface DocumentService extends IService<Document> {
    //添加文件
    Boolean addDocument(Document document);

    //递归删除
    boolean recDelDocument(List<DocumentRes> documentRes);

    //删除文件
    boolean delFileById(Document document);

    //递归查找
    List<DocumentRes> recFindDocumentTree(Long id);

    //查找父级文件下子文件
    List<Document> findDocumentListByPid(Long pid);

}
