package cn.stylefeng.star.modular.bussines.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.star.core.common.constant.TypesEnum;
import cn.stylefeng.star.modular.bussines.entity.TInfos;
import cn.stylefeng.star.modular.bussines.service.TInfosService;
import cn.stylefeng.star.modular.util.UploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zhangty
 * @Date: 2019/3/21 09:00
 * @Description:行业信息
 * @Version:1.0.0
 */
@Controller
@RequestMapping("/focus")
@Slf4j
public class FocusController extends BaseController {

    @Autowired
    private TInfosService tInfosService;

    private static String PREFIX = "/modular/bussines/focus/";

    @RequestMapping("")
    public String list(){
        return PREFIX+"list.html";
    }

    @ResponseBody
    @RequestMapping(value="/upload_img",method = RequestMethod.POST)
    public String uploadImg(@RequestParam(value="file") MultipartFile Imgfile, HttpServletRequest request){
        //本地使用
        String rootPath="C:/User/zhangty/uploads/";
        String  result= UploadUtils.uploadFile(rootPath,Imgfile);
        return  result;
    }


    @RequestMapping("/add")
    @ResponseBody
    public ResponseData add(TInfos infos,HttpServletRequest request) {
        log.info("获取保存参数:{}",infos);
        String  counts = request.getParameter("infoContent");
        infos.setInfoContent(counts);
        infos.setInfoType(TypesEnum.TypesEnumSt.FOCUS.getIndex());
        this.tInfosService.addInfo(infos);
        return SUCCESS_TIP;
    }
}
