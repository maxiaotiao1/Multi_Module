package com.mx.probim.projectdocument.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * @Description  
 * @Author  idea
 * @Date 2020-12-11 
 */

@Data
public class ProjectDocumentMerge  implements Serializable {

	private static final long serialVersionUID =  6004240845866967114L;

	private Long id;

	private Long projectId;

	private Long documentId;

}
