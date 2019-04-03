package cn.stylefeng.star.modular.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: zhangty
 * @Date: 2019/4/2 22:41
 * @Description:编号生成
 * @Version:1.0.0
 */
public class ItemNoUtil {

    /**
     * 生成编号
     * @return
     */
    public static String getItemNo(){
        //设置日期格式
        String k = "K";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        // new Date()为获取当前系统时间，也可使用当前时间戳
        String date = df.format(new Date());
        String result = k+date;
        return result;
    }


    public static void main(String[] arge){
        String reslut = getItemNo();
        System.out.println(reslut);
    }
}
