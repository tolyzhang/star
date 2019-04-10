package cn.stylefeng.star.modular.util;

import cn.stylefeng.star.core.util.MD5Util;
import cn.stylefeng.star.modular.system.model.SendDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URLEncoder;
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

    public static JSONObject  sendSms(String phone, Integer code, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        log.info("发送短信:{}",URL);
        SendDto dto = new SendDto();
         dto.setAccount("jksc1296");
        String password = "jksc129635";	//发送帐号密码
        String newPass = MD5Util.MD5Encode(password).toUpperCase();
         dto.setPassword(newPass);
         dto.setMobile(phone);
        String url="";
        try{
        //String content = "尊敬的用户您好,您正在使用手机号码注册,验证码为："+code+"";	//发送内容
        String content = "【创新平台】"+code+"（你的手机验证码，请完成验证），如非本人操作，请忽略本短信。";
        log.info("发送短信内容:{}",content);
             content = new String(content.getBytes("utf-8"), "utf-8");
            log.info("发送短信内容content:{}",content);
            URLEncoder.encode(content,"utf-8");
             url = URL+"?action=send&userid=&account=jksc1296&password="+newPass+"&mobile="+phone+"&content="+URLEncoder.encode(content,"utf-8")+"&sendTime=&extno=";
            log.info("地址:{}",url);
            String result = HttpUtils.HttpDoPost(url,"");
            log.info("发送结果:{}",result);
            String json = xml2JSON(result);
            log.info("结果:{}",json);
            JSONObject jsonj = JSONObject.parseObject(json);
            String returnSms = jsonj.getString("returnsms");
            log.info("解析所有返回参数:{}",returnSms);
            JSONObject jsont = JSONObject.parseObject(returnSms);
            return jsont;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] arge) throws Exception{
        String password = "ab123456";	//发送帐号密码
        String newPass = MD5Util.MD5Encode(password).toUpperCase();
        String content = "【创新平台】111111（你的手机验证码，请完成验证），如非本人操作，请忽略本短信。";	//发送内容
        content = new String(content.getBytes("utf-8"),"utf-8");
        System.out.println(content);
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
