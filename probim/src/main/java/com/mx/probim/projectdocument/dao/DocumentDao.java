package com.mx.probim.projectdocument.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mx.probim.projectdocument.entity.Document;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DocumentDao extends BaseMapper<Document> {
    //添加文件
    @Insert("insert into document (pid,file_name,file_size,file_path,file_type,update_time,create_time) values(#{pid},#{fileName},#{fileSize},#{filePath},#{fileType},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "id" ,keyColumn = "document.id")
    boolean addDocument(Document document);

    //根据id删除文件 并删除其与项目的关系
    @Delete("delete a,b from document as a left join project_document_merge as b on a.id = b.document_id  where a.id = #{id}")
    boolean deleteDocumentAndMergeById(@Param("id") Long id);

    //根据pid查找出来子文件 id
    @Select("select id from document where pid = #{pid}")
    List<Document> findDocumentIdsByPid(Long pid);

    //根据pid查找子文件
    @Select("select id,pid,file_name,file_type,file_size,file_path,create_time,update_time from document where pid = #{pid}")
    List<Document> findDocumentListByPid(Long pid);

}
