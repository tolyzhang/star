package cn.stylefeng.star.modular.util;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: zhangty
 * @Date: 2019/4/4 12:55
 * @Description:
 * @Version:1.0.0
 */
public class DateUtils {

    public static Date getNowDate(String crtTime)  throws  Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime  = formatter.parse(crtTime);
        return currentTime;
    }

    public static void main(String[] arge) throws Exception{
        Date st = getNowDate("2019-04-04 12:26:21");
        System.out.println(st);
    }
}
