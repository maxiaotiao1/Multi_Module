package com.mx.probim.projectdocument.res;

import com.mx.probim.projectdocument.entity.Document;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DocumentRes {

    private Long id;

    private Long pid;

    private String fileName;

    private String fileSize;

    private Long fileType;

    private String filePath;

    private Date createTime;

    private Date updateTime;

    private List<DocumentRes> children;
}
