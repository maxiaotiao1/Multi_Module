package com.mx.probim.projectdocument.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * @Description  
 * @Author  idea
 * @Date 2020-12-11 
 */

@Data
public class Module  implements Serializable {

	private static final long serialVersionUID =  1172553484550994960L;

	private Long id;

	private String name;

}
