package com.mx.probim.projectdocument.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * @Description  
 * @Author  idea
 * @Date 2020-12-11 
 */

@Data
public class DocumentModuleMerge  implements Serializable {

	private static final long serialVersionUID =  3607481467736375761L;

	private Long id;

	private Long documentId;

	private Long moduleId;

}
