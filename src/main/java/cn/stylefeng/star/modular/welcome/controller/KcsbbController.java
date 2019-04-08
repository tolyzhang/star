package cn.stylefeng.star.modular.welcome.controller;


import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.star.core.common.annotion.Permission;
import cn.stylefeng.star.core.common.constant.Const;
import cn.stylefeng.star.core.common.page.LayuiPageFactory;
import cn.stylefeng.star.core.log.LogManager;
import cn.stylefeng.star.core.log.factory.LogTaskFactory;
import cn.stylefeng.star.core.shiro.ShiroKit;
import cn.stylefeng.star.core.util.FileUtil;
import cn.stylefeng.star.modular.bussines.controller.TNewController;
import cn.stylefeng.star.modular.bussines.entity.*;
import cn.stylefeng.star.modular.bussines.model.SessionDto;
import cn.stylefeng.star.modular.bussines.service.*;
import cn.stylefeng.star.modular.bussines.warpper.newWarpper;
import cn.stylefeng.star.modular.util.CheckUtil;
import cn.stylefeng.star.modular.util.ItemNoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
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

import static cn.stylefeng.roses.core.util.HttpContext.getIp;


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

    @Autowired
    private TWarehousService tWarehousService;

    @Autowired
    private ExpertService tExpertService;

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value="/kcsbb",method = RequestMethod.GET)
    public String kcsbb(Model model,HttpServletRequest req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }else{
            model.addAttribute("userName","");
        }
        model.addAttribute("msg",isLogin);
        return "/kcsbb.html";
    }

    /**
     * 项目申报新闻
     * @author zhangty
     * @Date 2019/03/23 5:34 PM
     *//*
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
*/


    @RequestMapping(value="/kcsbb_d",method = RequestMethod.GET)
    public String kcsbb_d(Model model,HttpServletRequest req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }else{
            model.addAttribute("userName","");
        }
        model.addAttribute("msg",isLogin);
        return "/kcsbb_d.html";
    }

    @RequestMapping(value="/onlineAdd",method = RequestMethod.GET)
    public String onlineAdd(Model model,HttpServletRequest req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }else{
            model.addAttribute("userName","");
        }
        model.addAttribute("msg",isLogin);
        return "/kcsbb_sb.html";
    }

    @RequestMapping(value="/success",method = RequestMethod.GET)
    public String success(Model model,HttpServletRequest req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }
        model.addAttribute("msg",isLogin);
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

    @RequestMapping(value="/kcxmk",method = RequestMethod.GET)
    public String kcxmk(Model model,HttpServletRequest req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }else{
            model.addAttribute("userName","");
        }
        model.addAttribute("msg",isLogin);
        return "/kcxmk.html";
    }

    @RequestMapping(value="/kcxmk_d",method = RequestMethod.GET)
    public String kcxmk_d(Model model,HttpServletRequest req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }else{
            model.addAttribute("userName","");
        }
        model.addAttribute("msg",isLogin);
        return "/kcxmk_d.html";
    }

    @RequestMapping(value="/onkcxmkAdd",method = RequestMethod.GET)
    public String onkcxmkAdd(Model model,HttpServletRequest  req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }else{
            model.addAttribute("userName","");
        }
        model.addAttribute("msg",isLogin);
        return "/kcxmk_rk.html";
    }


    @RequestMapping(value="/kcxmkAdd", method = RequestMethod.POST)
    @ResponseBody
    public void kcxmkAdd(TWarehous dto, TPart tPart, HttpServletRequest request,HttpServletResponse response) throws  Exception {
        log.info("获取所有保存的参数:{}",dto);
        log.info("保存part表参数:{}",tPart);
        String itemNo = ItemNoUtil.getItemNo();
        log.info("生成订单号：{}",itemNo);
        //获取上传的文件数组
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String upResult = uploadData(request,itemNo,files);
        log.info("上传附件及保存数据结果:{}",upResult);
        Integer isAnnex=0;
        if(files.size()>0){
            isAnnex=1;
        }
        log.info("上传成功,保存业务数据");
        SessionDto seDto = CheckUtil.getSessionSr(request);
        dto.setItemNo(itemNo);
        dto.setIsAnnex(isAnnex);
        tWarehousService.addWarehous(dto,seDto);
        response.sendRedirect("/success");
    }


    @RequestMapping(value="/rck",method = RequestMethod.GET)
    public String rck(Model model,HttpServletRequest req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }else{
            model.addAttribute("userName","");
        }
        model.addAttribute("msg",isLogin);
        return "/rck.html";
    }

    @RequestMapping(value="/onRckAdd",method = RequestMethod.GET)
    public String onRckAdd(Model model,HttpServletRequest req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }else{
            model.addAttribute("userName","");
        }
        model.addAttribute("msg",isLogin);
        return "/rck_zj.html";
    }

    @RequestMapping(value="/rckAdd", method = RequestMethod.POST)
    @ResponseBody
    public void rckAdd(Expert dto, TPart tPart, HttpServletRequest request,HttpServletResponse response) throws  Exception {
        log.info("获取所有专家人才库保存的参数:{}",dto);
        String itemNo = ItemNoUtil.getItemNo();
        log.info("生成订单号：{}",itemNo);
        //获取上传的文件数组
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String upResult = uploadData(request,itemNo,files);
        log.info("上传附件及保存数据结果:{}",upResult);
        Integer isAnnex=0;
        if(files.size()>0){
            isAnnex=1;
        }
        log.info("上传成功,保存业务数据");
        SessionDto seDto = CheckUtil.getSessionSr(request);
        dto.setItemNo(itemNo);
        dto.setIsAnnex(isAnnex);
        tExpertService.addExpert(dto,seDto);
        response.sendRedirect("/success");
    }


    @RequestMapping(value="/hysj",method = RequestMethod.GET)
    public String hysj(Model model,HttpServletRequest req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }else{
            model.addAttribute("userName","");
        }
        model.addAttribute("msg","");
        return "/hysj.html";
    }
    @RequestMapping(value="/kcxqzj",method = RequestMethod.GET)
    public String kcxqzj(Model model,HttpServletRequest req){
        HttpSession session = req.getSession();
        String isLogin = CheckUtil.isLogins(req);
        log.info("是否登陆:{}",isLogin);
        model. addAttribute("msgs", isLogin);
        if(isLogin.equals("1")){
            model.addAttribute("userName",session.getAttribute("userName"));
        }else{
            model.addAttribute("userName","");
        }
        model.addAttribute("msg","");
        return "/kcxqzj.html";
    }


    /**
     * 退出登录
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:42 PM
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    @ResponseBody
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("获取用户信息:{}",session.getAttribute("userName"));
        session.removeAttribute("userName");
        session.invalidate();
        return "1";
    }




    /**
     * 上传保存附件信息
     * @param request
     * @param itemNo
     * @param files
     * @return
     */
    public String uploadData(HttpServletRequest request,String itemNo,List<MultipartFile> files){
        String pathRoot = "C:\\项目\\star\\target\\classes\\static\\imgs\\";
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
                return "error";
            }
        }
        return "ok";
    }



}
