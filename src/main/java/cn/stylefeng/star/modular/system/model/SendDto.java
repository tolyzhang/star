package cn.stylefeng.star.modular.system.model;

import cn.stylefeng.star.core.util.MD5Util;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zhangty
 * @Date: 2019/4/9 16:30
 * @Description:
 * @Version:1.0.0
 */
@Data
public class SendDto implements Serializable {

    private String userid;
    private  String account;	//发送用户帐号
    private String password ;	//发送帐号密码
    private  String  mobile ;	//全部被叫号码
    private   String content;	//发送内容
    private  String sendTime;//定时发送时间
    private  String action ;	//发送任务命令
    private  String extno;	//扩展子号

}
