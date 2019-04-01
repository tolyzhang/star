package cn.stylefeng.star.modular.welcome.controller;


import cn.stylefeng.star.core.util.FileUtil;
import cn.stylefeng.star.modular.bussines.entity.TUser;
import cn.stylefeng.star.modular.bussines.service.TUserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;


@Slf4j
public class registController {
    @Autowired
    private TUserService tUserService;

    private String PREFIX = "/welcome/";




    @RequestMapping(value="upload")
    public String upload(){
        return PREFIX+"upload.html";
    }




    @RequestMapping(value="/makete",method = RequestMethod.POST)
    public @ResponseBody void  makePdf(HttpServletRequest request,@RequestParam("companyName") String companyName) {
        // 模板路径
        String templatePath = "C:/test/creative.pdf";
        // 生成的新文件路径
        String newPDFPath = "C:/myuser/1.newPdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;

        try {
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            form.setField("companyName","abcsss");
            form.setField("orgNo","1111111");
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }
    }


  /*  @RequestMapping(value="/uploads",method = RequestMethod.POST)
    public String uploads(HttpServletRequest request){
        return "";
    }

    @RequestMapping(value="/uploads/batch",method = RequestMethod.POST)
    public String uploadBath(HttpServletRequest request){
        return "";

    }


    //多文件上传
    @RequestMapping(value = "/uploads/batch", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }
        }
        return "upload successful";
    }


    //文件上传相关代码
    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("test") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "C://test//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }*/



   /* //处理文件上传
    @RequestMapping(value="/uploads", method = RequestMethod.POST)
    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String pathRoot = "c:/test/";
        String info = null;
        //获取所有注册信息表单,提交后上传图片信息,保存到user表中
        try {
            info = FileUtil.uploadFile(file);
            info = pathRoot+info;
            log.info("保存数据库上传信息:{}",info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }*/

    //处理多文件上传
    @RequestMapping(value="/upload/batch", method = RequestMethod.POST)
    @ResponseBody
    public String multipleFilesUpload(HttpServletRequest request){
        String pathRoot = "C:/test/";
        //获取上传的文件数组
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        //遍历处理文件
        String info = null;
        String filePath = "c:/test/";
        for (MultipartFile file:files) {
            try {
                String s = FileUtil.uploadFile(file,filePath);
                log.info("上传的文件名称：{}",s);
                log.info("保存数据库地址：{}",pathRoot+s);
                info = info+"-"+s;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return info;
    }




}
