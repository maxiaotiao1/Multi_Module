package com.mx.probim.projectdocument.req;

import lombok.Data;

@Data
public class AddFolderReq {

    private Long pid;

    private String fileName;

    private Long fileType;

    private Long projectId;

}
