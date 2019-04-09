package cn.stylefeng.star.modular.util;

import cn.stylefeng.star.core.util.MD5Util;
import cn.stylefeng.star.modular.system.model.SendDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import java.util.*;

/**
 * @Auther: zhangty
 * @Date: 2019/4/9 16:09
 * @Description:发送
 * @Version:1.0.0
 */
@Slf4j
public class SendUtils {

    private static String  URL = "https://sh2.ipyy.com/sms.aspx";

    public static JSONObject  sendSms(String phone,Integer code){
        log.info("发送短信:{}",URL);
        SendDto dto = new SendDto();
         dto.setAccount("gsj121");
        String password = "ab123456";	//发送帐号密码
        String newPass = MD5Util.MD5Encode(password).toUpperCase();
         dto.setPassword(newPass);
         dto.setMobile(phone);
        String content = "您的短信验证码为:["+code+"],验证码5分钟内有效。";	//发送内容
        String url = URL+"?action=send&userid=&account=gsj121&password="+newPass+"&mobile="+phone+"&content="+content+"&sendTime=&extno=";
        String result = HttpUtils.HttpDoPost(url,"");
        log.info("发送结果:{}",result);
        String json = xml2JSON(result);
        log.info("结果:{}",json);
        JSONObject jsonj = JSONObject.parseObject(json);
        JSONObject jsont = JSONObject.parseObject(jsonj.get("retrunsms")+"");
        return jsont;
    }


    public static void main(String[] arge){
        String password = "ab123456";	//发送帐号密码
        String newPass = MD5Util.MD5Encode(password).toUpperCase();
        String content = "您的短信验证码为:[123456],验证码5分钟内有效。";	//发送内容
        String url = URL+"?action=send&userid=&account=gsj121&password="+newPass+"&mobile=17602100029&content="+content+"&sendTime=&extno=";
        String result = HttpUtils.HttpDoPost(url,"");
        log.info("发送结果:{}",result);
       // JSONObject jsObj = JSONObject.parseObject(result);
        try{
           // JSONObject json = null;


            //log.info("结果:{}",jsonj);
            //log.info("结果:{}",jsonj.get("returnstatus"));
        }catch (Exception e){
            log.info("转换异常:{}",e);
        }
     }

    public static int randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return Integer.parseInt(str.toString());
    }

/*

    public static JSONObject xml2JSON(byte[] xml) throws JDOMException, IOException {
        JSONObject json = new JSONObject();
        InputStream is = new ByteArrayInputStream(xml);
        SAXBuilder sb = new SAXBuilder();
        org.jdom2.Document doc = sb.build(is);
        Element root = doc.getRootElement();
        json.put(root.getName(), iterateElement(root));
        return json;
    }

    private static String iterateElement(Element element) {
        List node = element.getChildren();
        Element et = null;
        JSONObject obj = new JSONObject();
        List list = null;
        for (int i = 0; i < node.size(); i++) {
            list = new LinkedList();
            et = (Element) node.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                  obj.put(et.getName(), list);
            }
        }
        return obj.toString();
    }
*/



    /**
     * 转换一个xml格式的字符串到json格式
     *
     * @param xml
     *            xml格式的字符串
     * @return 成功返回json 格式的字符串;失败反回null
     */
    @SuppressWarnings("unchecked")
    public static  String xml2JSON(String xml) {
        JSONObject obj = new JSONObject();
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();
            obj.put(root.getName(), iterateElement(root));
            return obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 一个迭代方法
     *
     * @param element
     * @return java.util.Map 实例
     */
    @SuppressWarnings("unchecked")
    private static Map  iterateElement(Element element) {
        List jiedian = element.elements();
        Element et = null;
        Map obj = new HashMap();
        Object temp;
        List list = null;
        for (int i = 0; i < jiedian.size(); i++) {
            list = new LinkedList();
            et = (Element) jiedian.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.elements().size() == 0){
                    continue;
                }
                if (obj.containsKey(et.getName())) {
                    temp = obj.get(et.getName());
                    if(temp instanceof List){
                        list = (List)temp;
                        list.add(iterateElement(et));
                    }else if(temp instanceof Map){
                        list.add((HashMap)temp);
                        list.add(iterateElement(et));
                    }else{
                        list.add((String)temp);
                        list.add(iterateElement(et));
                    }
                    obj.put(et.getName(), list);
                }else{
                    obj.put(et.getName(), iterateElement(et));
                }
            } else {
                if (obj.containsKey(et.getName())) {
                    temp = obj.get(et.getName());
                    if(temp instanceof List){
                        list = (List)temp;
                        list.add(et.getTextTrim());
                    }else if(temp instanceof Map){
                        list.add((HashMap)temp);
                        list.add(iterateElement(et));
                    }else{
                        list.add((String)temp);
                        list.add(et.getTextTrim());
                    }
                    obj.put(et.getName(), list);
                }else{
                    obj.put(et.getName(), et.getTextTrim());
                }

            }

        }
        return obj;
    }





}
