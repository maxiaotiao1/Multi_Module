package com.mx.probim.projectdocument.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mx.probim.projectdocument.entity.Document;
import com.mx.probim.projectdocument.entity.ProjectDocumentMerge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectDocumentMergeDao extends BaseMapper<ProjectDocumentMerge> {
    //根据项目查询有哪些文件
    @Select("select b.id,b.pid,b.file_name,b.file_type from project_document_merge as a  " +
            "left join document as b on a.document_id = b.id where a.project_id = #{projectId} and b.file_type = 0 ")
    List<Document> findFolderListByProjectId(Long projectId);

    @Select("select b.id,b.pid,b.file_name,b.file_type,b.file_path from project_document_merge as a  " +
            "right join document as b on a.document_id = b.id where a.project_id = #{projectId} and b.pid = 0")
    List<Document> findDocumentListByProjectId(Long projectId);
}
