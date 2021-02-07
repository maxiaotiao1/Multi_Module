package com.mx.common.res;

import lombok.Data;

import java.util.List;

/**
 * 简单的分页返回对象
 */
@Data
public class PageSimpleResponse<T> {
    // 总数
    private Long total;
    // 列表
    private List<T> list;

    public Long getTotal() {
        return total;
    }

    public List<T> getList() {
        return list;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
