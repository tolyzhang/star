package cn.stylefeng.star.modular.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: zhangty
 * @Date: 2019/3/26 14:56
 * @Description:文本编辑标签转换
 * @Version:1.0.0
 */
@Slf4j
public class TagUtil {

    /**
     * 根据标签存入库的原始值，转成HTML页面标签
     * @param tagData
     * @return
     */
    public static String getTagHtml(String  tagData){
        log.info("原始存库的值：{}",tagData);
        String  strs = "";
        try{
              strs = tagData.replaceAll("& lt;","<").replaceAll("& gt;",">");
            log.info("转义后的值:{}",strs);
        }catch (Exception e){
             log.error("转义异常:{}",e);
             return strs;
        }
        return strs;
    }
}
