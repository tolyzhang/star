package cn.stylefeng.star.modular.bussines.controller;

import cn.stylefeng.star.core.common.page.LayuiPageFactory;
import cn.stylefeng.star.modular.bussines.service.TDemandService;
import cn.stylefeng.star.modular.system.warpper.LogWarpper;
import cn.stylefeng.star.modular.util.RequestUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangty
 * @Date: 2019/3/13 09:10
 * @Description:项目申报
 * @Version:1.0.0
 */
@Controller
@Slf4j
@RequestMapping("/demand")
public class TDemandController {

    private static String PREFIX = "/modular/bussines/demand/";

    @Autowired
    private TDemandService tDemandService;


    @RequestMapping("")
    public String list(){
        return PREFIX+"list.html";
    }


    /**
     *
     *项目申报
     * @author zhangty
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(HttpServletRequest reqeust) {
        Map<String,Object> param = RequestUtil.getReqMap(reqeust);
        //获取分页参数
        log.info("请求参数:{}",param);
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String,Object>> result = tDemandService.getDemand(page,param);
                log.info("返回的结果:{}",result);
        page.setRecords(new LogWarpper(result).wrap());

        return LayuiPageFactory.createPageInfo(page);
    }

}
