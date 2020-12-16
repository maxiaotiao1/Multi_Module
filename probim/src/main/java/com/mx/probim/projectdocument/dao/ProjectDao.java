package com.mx.probim.projectdocument.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mx.probim.projectdocument.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectDao extends BaseMapper<Project> {
    //查询所有项目
    @Select("select id,project_name from project")
    List<Project> listProject();
}
