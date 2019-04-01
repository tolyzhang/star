package cn.stylefeng.star.modular.bussines.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.star.core.common.annotion.Permission;
import cn.stylefeng.star.core.common.constant.Const;
import cn.stylefeng.star.core.common.page.LayuiPageFactory;
import cn.stylefeng.star.core.log.LogObjectHolder;
import cn.stylefeng.star.modular.bussines.entity.TNews;
import cn.stylefeng.star.modular.bussines.entity.TUser;
import cn.stylefeng.star.modular.bussines.service.TNewsService;
import cn.stylefeng.star.modular.bussines.service.TUserService;
import cn.stylefeng.star.modular.bussines.warpper.newWarpper;
import cn.stylefeng.star.modular.bussines.warpper.userWarpper;
import cn.stylefeng.star.modular.util.TagUtil;
import cn.stylefeng.star.modular.util.UploadUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/3/21 09:00
 * @Description:注册信息
 * @Version:1.0.0
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class TUserController extends BaseController {

    @Autowired
    private TUserService tUserService;

    private static String PREFIX = "/modular/bussines/user/";

    @RequestMapping("")
    public String list(){
        return PREFIX+"list.html";
    }

    /**
     * 成果转化
     * @author zhangty
     * @Date 2019/03/23 5:34 PM
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(
                       @RequestParam(required = false) String industryType,
                       @RequestParam(required = false) String userStatus,
                       @RequestParam(required = false) String companyName,
                       @RequestParam(required = false) String  userName) {
        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();
       List<Map<String,Object>> result =tUserService.getUserList(page,industryType,userStatus,companyName,userName);
        log.info("返回的结果:{}",result);
        page.setRecords(new userWarpper(result).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }


    /**
     * 跳转到修改
     * @author zhangty
     */
    @RequestMapping("/user_detail")
    public String newDetail(@RequestParam Integer id) {
        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException();
        }
        TUser user = tUserService.getByTUser(id);
        LogObjectHolder.me().set(user);
        return PREFIX + "user_detail.html";
    }

    /**
     * 详情
     * @author zhangty
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Integer id) {
        TUser user = tUserService.getByTUser(id);
        return user;
    }

    /**
     * 修改操作
     * @author
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public ResponseData update(TUser user) {
        tUserService.editUser(user);
        return SUCCESS_TIP;
    }


    /**
     * 删除
     * @author
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public ResponseData delete(@RequestParam Integer id) {
        tUserService.deleteTuser(id);
        return SUCCESS_TIP;
    }

    /**
     * 审核
     * @param id
     * @return
     */
    @RequestMapping(value="review")
    @ResponseBody
    public ResponseData review(@RequestParam Integer id){
        TUser user = new TUser();
        user.setUserStatus("1");
        user.setId(id);
        tUserService.editUser(user);
        return SUCCESS_TIP;
    }










}
