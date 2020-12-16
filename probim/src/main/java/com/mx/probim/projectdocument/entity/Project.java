package com.mx.probim.projectdocument.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * @Description  
 * @Author  idea
 * @Date 2020-12-11 
 */

@Data
public class Project  implements Serializable {

	private static final long serialVersionUID =  4005542087347983047L;

	private Long id;

	private String projectName;

}
