package cn.stylefeng.star.modular.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/3/26 15:44
 * @Description:上传工具类
 * @Version:1.0.0
 */
@Slf4j
public class UploadUtils {

    /**
     * 上传工具类
     * @param rootPath 保存地址
     * @param Imgfile file文件信息
     * @return
     */
    public static String uploadFile(String rootPath, MultipartFile Imgfile){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String res = sdf.format(new Date());
        log.info("当前时间:{}",res);
        //服务器上使用
        // String rootPath =request.getServletContext().getRealPath("/resource/uploads/");//target的目录
        //本地使用
       // String rootPath="C:/User/zhangty/uploads/";
        //原始名称
        String originalName = Imgfile.getOriginalFilename();
        //新的文件名称
        String newFileName = res+originalName.substring(originalName.lastIndexOf("."));
        //创建文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR)
                + File.separator + (date.get(Calendar.MONTH)+1));
        //新文件
        File newFile =new File(rootPath+File.separator+dateDirs+File.separator+newFileName);
        //判断目标文件所在的目录是否存在
        if(!newFile.getParentFile().exists()){
            //如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile);
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> map2 = new HashMap<String,Object>();
        String fileUrl="";
        String result="";
        //将内存中的数据写入磁盘
        try {
            Imgfile.transferTo(newFile);
            //完整的url
            fileUrl =rootPath+date.get(Calendar.YEAR)+ "/"+(date.get(Calendar.MONTH)+1)+ "/"+ newFileName;
        } catch (IOException e) {
            map.put("code",1);//0表示成功，1失败
            map.put("msg","上传异常");//提示消息
            map.put("data",map2);
            map2.put("src",fileUrl);//图片url
            map2.put("title",newFileName);//图片名称，这个会显示在输入框里
            result = new JSONObject(map).toString();
            return result;
        }
        map.put("code",0);//0表示成功，1失败
        map.put("msg","上传成功");//提示消息
        map.put("data",map2);
        map2.put("src",fileUrl);//图片url
        map2.put("title",newFileName);//图片名称，这个会显示在输入框里
        result = new JSONObject(map).toString();

        return result;
    }
}
