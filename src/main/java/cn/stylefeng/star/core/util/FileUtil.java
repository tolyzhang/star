package cn.stylefeng.star.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class FileUtil {
    /**
     * 第一步：判断文件是否为空   true：返回提示为空信息   false：执行第二步
     * 第二步：判断目录是否存在   不存在：创建目录
     * 第三部：通过输出流将文件写入硬盘文件夹并关闭流
     * @param file
     * @return
     */
    public static String uploadFile(MultipartFile file,String filePath){
        String fileName = System.currentTimeMillis()+"";
        log.info("上传重命名:{}",fileName);
        String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        log.info("文件名后缀",suffixName);
        fileName = fileName + suffixName;
        File targetFile = new File(filePath);
        //第一步：判断文件是否为空
        if(file.isEmpty())return fileName+"文件为空";
        //第二步：判断目录是否存在   不存在：创建目录
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //第三部：通过输出流将文件写入硬盘文件夹并关闭流
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(new FileOutputStream(filePath+fileName));
            stream.write(file.getBytes());
            stream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (stream != null) stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
