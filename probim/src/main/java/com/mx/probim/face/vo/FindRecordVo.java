package com.mx.probim.face.vo;

import lombok.Data;

import java.util.List;

@Data
public class FindRecordVo {
    private int page;

    private int pageSize;

    private int count;

    private int result;

    private boolean success;

    private List<RecordData> data;

    private String message;
}
