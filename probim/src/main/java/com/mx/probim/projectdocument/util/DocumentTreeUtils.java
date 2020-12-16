package com.mx.probim.projectdocument.util;

import com.mx.probim.projectdocument.entity.Document;
import com.mx.probim.projectdocument.res.DocumentRes;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限规则生成树形节点工具类
 */
public class DocumentTreeUtils {

    /**
     * 多维数组
     */
    public static List<DocumentRes> merge(List<Document> documentList,
                                          Long pid) {

        //创建返回数组对象
        List<DocumentRes> documentResList = new ArrayList<>();
        //遍历原数组
        for (Document document : documentList) {
            DocumentRes documentRes = new DocumentRes();
            //将原数组的值复制给现在的数组
            BeanUtils.copyProperties(document, documentRes);
            //判断其父节点
            if (pid.equals(document.getPid())) {
                //如果是则，将其添加到当前，并继续查询其子孩子
                documentRes.setChildren(merge(documentList, document.getId()));
                documentResList.add(documentRes);
            }
        }

        return documentResList;
    }


}
