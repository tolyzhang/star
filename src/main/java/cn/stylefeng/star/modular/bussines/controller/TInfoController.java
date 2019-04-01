package cn.stylefeng.star.modular.bussines.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.star.core.common.annotion.Permission;
import cn.stylefeng.star.core.common.constant.Const;
import cn.stylefeng.star.core.common.page.LayuiPageFactory;
import cn.stylefeng.star.modular.bussines.entity.TInfos;
import cn.stylefeng.star.modular.bussines.service.TInfosService;
import cn.stylefeng.star.modular.bussines.warpper.InfoWarpper;
import cn.stylefeng.star.modular.system.warpper.LogWarpper;
import cn.stylefeng.star.modular.system.warpper.MenuWarpper;
import cn.stylefeng.star.modular.util.UploadUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: zhangty
 * @Date: 2019/3/21 09:00
 * @Description:行业信息
 * @Version:1.0.0
 */
@Controller
@RequestMapping("/infos")
@Slf4j
public class TInfoController extends BaseController {

    @Autowired
    private TInfosService tInfosService;

    private static String PREFIX = "/modular/bussines/infos/";

    @RequestMapping("")
    public String list(){
        return PREFIX+"list.html";
    }

    @RequestMapping("/list")
    public String  infostList(){
        return  PREFIX+"/list/list.html";
    }

    /**
     * 查询操作日志列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("/lists")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(
                       @RequestParam(required = false) String infoType,
                       @RequestParam(required = false) String infoStatus) {
        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String,Object>> result =tInfosService.getInfoList(page,infoType,infoStatus);
        log.info("返回的结果:{}",result);
        page.setRecords(new InfoWarpper(result).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }


    @ResponseBody
    @RequestMapping(value="/upload_img",method = RequestMethod.POST)
    public String uploadImg(@RequestParam(value="file") MultipartFile Imgfile, HttpServletRequest request){
        String rootPath="C:/User/zhangty/uploads/";
        String  result  = UploadUtils.uploadFile(rootPath,Imgfile);
        return result;
    }


    @RequestMapping("/add")
    @ResponseBody
    public ResponseData add(TInfos infos,HttpServletRequest request) {
        log.info("获取保存参数:{}",infos);
        String  counts = request.getParameter("infoContent");
        infos.setInfoContent(counts);
        log.info("详情内容:{}",counts);
        this.tInfosService.addInfo(infos);
        return SUCCESS_TIP;
    }

}
