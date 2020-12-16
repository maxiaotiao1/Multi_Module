package com.mx.probim.projectdocument.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mx.probim.projectdocument.dao.DocumentDao;
import com.mx.probim.projectdocument.entity.Document;
import com.mx.probim.projectdocument.res.DocumentRes;
import com.mx.probim.projectdocument.service.DocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentDao, Document> implements DocumentService {

    @Autowired
    DocumentDao documentDao;

    @Override
    public Boolean addDocument(Document document) {
        return documentDao.addDocument(document);
    }

    @Override
    public boolean recDelDocument(List<DocumentRes> documentRes) {
        //数组不为空的时候
        if (!documentRes.isEmpty()){
            System.out.println("不为空");
            for (DocumentRes documentRes1:documentRes) {
                if (!documentDao.deleteDocumentAndMergeById(documentRes1.getId())) {
                    return false;
                }
                if (!recDelDocument(documentRes1.getChildren())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean delFileById(Document document) {
        return documentDao.deleteDocumentAndMergeById(document.getId());
    }

    @Override
    public List<DocumentRes> recFindDocumentTree(Long id) {
        //创建返回数组对象
        List<DocumentRes> documentResList = new ArrayList<>();
        //获取孩子文件（包括文件夹和文件）id列表
        List<Document> documentList = documentDao.findDocumentIdsByPid(id);
        for (Document document: documentList) {
            DocumentRes documentRes = new DocumentRes();
            BeanUtils.copyProperties(document, documentRes);
            //查找以当前节点为父节点的孩子树
            documentRes.setChildren(recFindDocumentTree(document.getId()));
            documentResList.add(documentRes);
            }
        return documentResList;
    }

    @Override
    public List<Document> findDocumentListByPid(Long pid) {
        return documentDao.findDocumentListByPid(pid);
    }
}
