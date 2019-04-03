package cn.stylefeng.star.modular.welcome.controller;


import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.star.core.common.annotion.Permission;
import cn.stylefeng.star.core.common.constant.Const;
import cn.stylefeng.star.core.common.page.LayuiPageFactory;
import cn.stylefeng.star.core.util.FileUtil;
import cn.stylefeng.star.modular.bussines.controller.TNewController;
import cn.stylefeng.star.modular.bussines.entity.*;
import cn.stylefeng.star.modular.bussines.model.SessionDto;
import cn.stylefeng.star.modular.bussines.service.*;
import cn.stylefeng.star.modular.bussines.warpper.newWarpper;
import cn.stylefeng.star.modular.util.CheckUtil;
import cn.stylefeng.star.modular.util.ItemNoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class KcsbbController {

    @Autowired
    private TNewsService tNewsService;

    @Autowired
    private TAnnexService tAnnexService;

    @Autowired
    private CreativeService creativeService;

    @Autowired
    private TPartService tPartService;

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value="/kcsbb",method = RequestMethod.GET)
    public String kcsbb(Model model){
        model. addAttribute("msg", "");
        return "/kcsbb.html";
    }

    /**
     * 项目申报新闻
     * @author zhangty
     * @Date 2019/03/23 5:34 PM
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Object list(HttpServletRequest request) {
        //获取分页参数
        Integer proType = 1;
       String newTitle = request.getParameter("newTitle");
        String newType = "";
        String newStatus = "";
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String,Object>> result =tNewsService.getNewList(page,newType,newStatus,proType,newTitle);
        log.info("返回的结果:{}",result);
        page.setRecords(new newWarpper(result).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }



    @RequestMapping(value="/kcsbb_d",method = RequestMethod.GET)
    public String kcsbb_d(Model model,HttpServletRequest request){
        String isLogin = CheckUtil.isLogins(request);
        model. addAttribute("msg", isLogin);
        return "/kcsbb_d.html";
    }

    @RequestMapping(value="/onlineAdd",method = RequestMethod.GET)
    public String onlineAdd(Model model,HttpServletRequest req){
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msg", "1");
        return "/kcsbb_sb.html";
    }

    @RequestMapping(value="/success",method = RequestMethod.GET)
    public String success(Model model,HttpServletRequest req){
        model. addAttribute("msg", "");
        return "/success.html";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public void add(Creative dto, TPart tPart, HttpServletRequest request,HttpServletResponse response) throws  Exception {
        log.info("获取所有保存的参数:{}",dto);
        log.info("保存part表参数:{}",tPart);
        String itemNo = ItemNoUtil.getItemNo();
        log.info("生成订单号：{}",itemNo);
        String pathRoot = "C:\\项目\\star\\target\\classes\\static\\imgs\\";
        //获取上传的文件数组
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        //遍历处理文件
        String info = null;
        for (MultipartFile file:files) {
            TAnnex tAnnex = new TAnnex();
            try {
                String s = FileUtil.uploadFile(file,pathRoot);
                log.info("上传的文件名称：{}",s);
                log.info("上传地址：{}",pathRoot+s);
                 info = "imgs/"+s;
                 log.info("保存数据库url:{},保存文件名：{}",info,s);
                  tAnnex.setAllType("1");
                  tAnnex.setAnnexName(s);
                  tAnnex.setAnnexUrlAdd(info);
                  tAnnex.setItemNo(itemNo);
                  tAnnexService.addTAnnex(tAnnex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Integer isAnnex=0;
        if(files.size()>0){
            isAnnex=1;
        }
        log.info("上传成功,保存业务数据");
        SessionDto seDto = CheckUtil.getSessionSr(request);
        dto.setItemNo(itemNo);
        dto.setIsAnnex(isAnnex);
        creativeService.addCreative(dto,seDto);
        log.info("保存申请表成功,开始保存参与人表");
        tPart.setItemNo(itemNo);
        tPartService.addTPart(tPart,seDto);
        log.info("保存项目人员附属表完成");
        response.sendRedirect("/success");

    }

    //处理多文件上传
    @RequestMapping(value="/upload/batch", method = RequestMethod.POST)
    @ResponseBody
    public String multipleFilesUpload(HttpServletRequest request){
        //String pathRoot = "C:\\Users\\zhangty\\yf项目\\star\\target\\classes\\static\\imgs\\";
        String pathRoot = "C:\\项目\\star\\target\\classes\\static\\imgs\\";
        //获取上传的文件数组
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        //遍历处理文件
        String info = null;
        for (MultipartFile file:files) {
            try {
                String s = FileUtil.uploadFile(file,pathRoot);
                log.info("上传的文件名称：{}",s);
                log.info("保存数据库地址：{}",pathRoot+s);
                info = info+"-"+s;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return info;
    }


    @RequestMapping(value="/kcxmk",method = RequestMethod.GET)
    public String kcxmk(Model model){
        model. addAttribute("msg", "");
        return "/kcxmk.html";
    }

    @RequestMapping(value="/kcxmk_d",method = RequestMethod.GET)
    public String kcxmk_d(Model model){
        model. addAttribute("msg", "");
        return "/kcxmk_d.html";
    }

    @RequestMapping(value="/onkcxmkAdd",method = RequestMethod.GET)
    public String onkcxmkAdd(Model model){
        model. addAttribute("msg", "");
        return "/kcxmk_rk.html";
    }

    @RequestMapping(value="/rck",method = RequestMethod.GET)
    public String rck(Model model){
        model. addAttribute("msg", "");
        return "/rck.html";
    }

    @RequestMapping(value="/onRckAdd",method = RequestMethod.GET)
    public String onRckAdd(Model model){
        model. addAttribute("msg", "");
        return "/rck_zj.html";
    }

    @RequestMapping(value="/hysj",method = RequestMethod.GET)
    public String hysj(Model model){
        model. addAttribute("msg", "");
        return "/hysj.html";
    }
    @RequestMapping(value="/kcxqzj",method = RequestMethod.GET)
    public String kcxqzj(Model model){
        model. addAttribute("msg", "");
        return "/kcxqzj.html";
    }





}
