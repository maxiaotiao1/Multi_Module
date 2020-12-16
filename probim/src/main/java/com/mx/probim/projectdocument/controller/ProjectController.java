package com.mx.probim.projectdocument.controller;

import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.mx.common.enums.ResultEnum;
import com.mx.common.res.BaseResponse;
import com.mx.common.res.ResultVOUtils;
import com.mx.common.util.TreeUtils;
import com.mx.probim.projectdocument.dao.ProjectDao;
import com.mx.probim.projectdocument.entity.Document;
import com.mx.probim.projectdocument.entity.ProjectDocumentMerge;
import com.mx.probim.projectdocument.req.AddFolderReq;
import com.mx.probim.projectdocument.req.ReNameDocumentReq;
import com.mx.probim.projectdocument.res.DocumentRes;
import com.mx.probim.projectdocument.service.DocumentService;
import com.mx.probim.projectdocument.service.ProjectDocumentMergeService;
import com.mx.probim.projectdocument.service.ProjectService;
import com.mx.probim.projectdocument.util.AliyunOSSClientUtil;
import com.mx.probim.projectdocument.util.DocumentTreeUtils;
import com.sun.tools.internal.ws.processor.model.Response;
import org.apache.commons.io.FilenameUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ProjectController{

    @Autowired
    ProjectDao projectDao;
    @Autowired
    DocumentService documentService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectDocumentMergeService projectDocumentMergeService;

    /**
     * 获取项目列表
     * @return
     */
    @GetMapping("/getProjectList")
    public BaseResponse getProjectList(){
        return ResultVOUtils.success(projectService.listProject());
    }

    /**
     * 新建一个文件
     */
    @PostMapping("/addFolder")
    public BaseResponse addFolder(@RequestBody AddFolderReq addFolderReq){
        System.out.println(addFolderReq);
        Document document = new Document();
        BeanUtils.copyProperties(addFolderReq,document);
        //创建文件
        if (documentService.addDocument(document)){
            ProjectDocumentMerge projectDocumentMerge = new ProjectDocumentMerge();
            projectDocumentMerge.setDocumentId(document.getId());
            projectDocumentMerge.setProjectId(addFolderReq.getProjectId());
            //建立文件和项目联系
            projectDocumentMergeService.save(projectDocumentMerge);
        }else {
            return ResultVOUtils.error(ResultEnum.OPTION_FALL,"新增失败");
        }
        return ResultVOUtils.success();
    }

    /**
     * 文件树
     */
    @GetMapping("/getFolderTree")
    public BaseResponse getFolderTree(@RequestParam Long id){

        //获取本项目下的所有文件
        List<Document> root = projectDocumentMergeService.findRootFolderListByProjectId(id);
        List<DocumentRes> tree = DocumentTreeUtils.merge(root,0L);
        System.out.println(tree);
        Map<String, Object> restMap = new HashMap<>();
        restMap.put("list", tree);
        return ResultVOUtils.success(restMap);
    }

    /**
     *重命名文件夹
     */
    @PostMapping("/reNameFolder")
    public BaseResponse reNameFolder(@RequestBody ReNameDocumentReq reNameDocumentReq){
        Document document = new Document();
        BeanUtils.copyProperties(reNameDocumentReq,document);
        System.out.println(document);
        return documentService.updateById(document)?  ResultVOUtils.success(): ResultVOUtils.error(ResultEnum.OPTION_FALL,"重命名失败");
    }

    //删除文件或文件夹
    @GetMapping("/delDocument")
    public BaseResponse delDocument(@RequestParam Long id){
        //获取文件树
        Document document = documentService.getById(id);
        if (document == null){
            return ResultVOUtils.error(ResultEnum.DATA_NOT);
        }
        if(document.getFileType().equals(1)){
            //TODO 删除文件还需要把真实文件也删除
            return documentService.delFileById(document)? ResultVOUtils.success() : ResultVOUtils.error(ResultEnum.OPTION_FALL);
        }else {
            DocumentRes documentRes = new DocumentRes();
            BeanUtils.copyProperties(document, documentRes);
            List<DocumentRes> documents = documentService.recFindDocumentTree(id);
            documentRes.setChildren(documents);
            List<DocumentRes> documentRes1 = new ArrayList<>();
            documentRes1.add(documentRes);
            System.out.println(documentRes1);
            //删除文件树
            return documentService.recDelDocument(documentRes1)? ResultVOUtils.success(): ResultVOUtils.error(ResultEnum.OPTION_FALL, "删除文件夹失败");
        }
    }

    //查看父级文件下的子文件（文件夹和文件）
    @GetMapping("/getDocumentByPid")
    public BaseResponse getDocumentByPid(@RequestParam Long id){
        List<Document> documentListByPid = documentService.findDocumentListByPid(id);
        Map<String, Object> restMap = new HashMap<>();
        restMap.put("list", documentListByPid);
        return ResultVOUtils.success(restMap);
    }

    //查看项目下的子文件（文件和文档）
    @GetMapping("/getDocumentByProjectId")
    public BaseResponse getDocumentByProjectId(@RequestParam Long projectId){
        List<Document> documentList = projectDocumentMergeService.findRootDocumentListByProjectId(projectId);
        Map<String, Object> restMap = new HashMap<>();
        restMap.put("list", documentList);
        return ResultVOUtils.success(restMap);
    }

    //移动文件
    @PostMapping("/moveDocument")
    public BaseResponse moveDocument(@RequestParam Long id,Long pid){
        Document document = new Document();
        document.setId(id);
        document.setPid(pid);
        return documentService.updateById(document)?ResultVOUtils.success():ResultVOUtils.error(ResultEnum.OPTION_FALL);
    }

    //上传文件的处理 并保持文件信息保存到数据库
    @PostMapping("/upload")
    public BaseResponse upload(MultipartFile file, Long pid ,Long projectId) throws Exception {

        Document document = new Document();
        document.setPid(pid);
        document.setFileType(1L);

        //获取文件的原始名称
        String oldFileName = file.getOriginalFilename();
        document.setFileName(oldFileName);
        byte[] bytes = file.getBytes();
        InputStream in = new ByteArrayInputStream(bytes);

        //云存储文件
        document.setFilePath(AliyunOSSClientUtil.uploadImg2Oss(in,oldFileName));

//        //获取文件的后缀
//        String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
//        //生成新的文件名称
//        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-","").substring(6) + extension;
        //获取文件大小
        long size = file.getSize();
        document.setFileSize(String.valueOf(size));
//        //文件类型
//        String type = file.getContentType();
        documentService.addDocument(document);
        System.out.println("保存后的信息:"+document);
        ProjectDocumentMerge projectDocumentMerge = new ProjectDocumentMerge();
        projectDocumentMerge.setDocumentId(document.getId());
        projectDocumentMerge.setProjectId(projectId);
        projectDocumentMergeService.save(projectDocumentMerge);


        return ResultVOUtils.success();
    }

//    //文件下载
//    @GetMapping("/download")
//    public void download(Integer id,String openStyle,HttpServletResponse response) throws IOException {
//        //获取文件信息
//        UserFile userfile = userFileService.getFilesById(id);
//        //判断用户是在线打开还是下载
//        openStyle = openStyle == null ? "attachment" : openStyle;
//        if ("attachment".equals(openStyle)){
//            //更新文件下载次数
//            userfile.setDownCounts(userfile.getDownCounts()+1);
//            userFileService.update(userfile);
//        }
//        //根据文件信息中文件的名字和文件存储的路径获取文件输入流
//        String realpath = ResourceUtils.getURL("classpath:").getPath() + "/static" + userfile.getPath();
//        //获取文件输入流
//        FileInputStream is = new FileInputStream(new File(realpath, userfile.getNewFileName()));
//        //附件下载
//        response.setHeader("content-disposition",openStyle+";fileName="+ URLEncoder.encode(userfile.getOldFileName(),"UTF-8"));
//        //获取响应输出流
//        ServletOutputStream os = response.getOutputStream();
//        //文件拷贝
//        IOUtils.copy(is,os);
//        IOUtils.closeQuietly(is);
//        IOUtils.closeQuietly(os);
//    }
}
