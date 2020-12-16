package com.mx.probim.projectdocument.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  idea
 * @Date 2020-12-11 
 */

@Data
public class Document  implements Serializable {

	private static final long serialVersionUID =  6753087618670907855L;

	private Long id;

	private Long pid;

	private String fileName;

	private String fileSize;

	private Long fileType;

	private String filePath;

	private Date createTime;

	private Date updateTime;

}
