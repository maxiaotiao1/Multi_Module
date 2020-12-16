package com.mx.common.util;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    /**
     * 多维数组
     */
    public static <V extends Response, T extends BaseNode> List<V> merge(List<T> list, Long pid) {

        List<V> resList = new ArrayList();
        for ( T node: list) {
            V resNode = (V) new Object();
            BeanUtils.copyProperties(node, resNode);
            if (pid.equals(resNode.getPid())) {
                resNode.setChildren(merge(list, resNode.getId()));
                resList.add(resNode);
            }
        }

        return resList;
    }

}

@Data
class Response{
    private Long id;
    private Long pid;
    private List<Response> children;
}

@Data
class BaseNode{
    private Long id;
    private Long pid;
}
