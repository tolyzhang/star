package cn.stylefeng.star.modular.welcome.controller;


import cn.stylefeng.star.core.common.annotion.Permission;
import cn.stylefeng.star.core.common.constant.Const;
import cn.stylefeng.star.core.common.page.LayuiPageFactory;
import cn.stylefeng.star.modular.bussines.controller.TNewController;
import cn.stylefeng.star.modular.bussines.entity.TUser;
import cn.stylefeng.star.modular.bussines.service.TNewsService;
import cn.stylefeng.star.modular.bussines.service.TUserService;
import cn.stylefeng.star.modular.bussines.warpper.newWarpper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class KcsbbController {

    @Autowired
    private TNewsService tNewsService;

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
    public String kcsbb_d(Model model){
        model. addAttribute("msg", "");
        return "/kcsbb_d.html";
    }

    @RequestMapping(value="/onlineAdd",method = RequestMethod.GET)
    public String onlineAdd(Model model){
        model. addAttribute("msg", "");
        return "/kcsbb_sb.html";
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
