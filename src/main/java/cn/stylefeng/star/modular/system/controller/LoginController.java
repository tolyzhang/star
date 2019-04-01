/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.star.modular.system.controller;

import cn.stylefeng.star.core.common.node.MenuNode;
import cn.stylefeng.star.core.log.LogManager;
import cn.stylefeng.star.core.log.factory.LogTaskFactory;
import cn.stylefeng.star.core.shiro.ShiroKit;
import cn.stylefeng.star.core.shiro.ShiroUser;
import cn.stylefeng.star.core.util.FileUtil;
import cn.stylefeng.star.modular.bussines.entity.TUser;
import cn.stylefeng.star.modular.bussines.service.TUserService;
import cn.stylefeng.star.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static cn.stylefeng.roses.core.util.HttpContext.getIp;

/**
 * 登录控制器
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private TUserService tUserService;

    /**
     * 跳转到主页
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:41 PM
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        //获取当前用户角色列表
        ShiroUser user = ShiroKit.getUserNotNull();
        List<Long> roleList = user.getRoleList();

        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "/login.html";
        }

        List<MenuNode> menus = userService.getUserMenuNodes(roleList);
        model.addAttribute("menus", menus);

        return "/index.html";
    }

    /**
     * 跳转到登录页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:41 PM
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login.html";
        }
    }


    /**
     * 点击登录执行的动作
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:42 PM
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali() {

        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        String remember = super.getPara("remember");

        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

        //如果开启了记住我功能
        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }

        //执行shiro登录操作
        currentUser.login(token);

        //登录成功，记录登录日志
        ShiroUser shiroUser = ShiroKit.getUserNotNull();
        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));

        ShiroKit.getSession().setAttribute("sessionFlag", true);

        return REDIRECT + "/";
    }

    /**
     * 退出登录
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:42 PM
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUserNotNull().getId(), getIp()));
        ShiroKit.getSubject().logout();
        deleteAllCookie();
        return REDIRECT + "/login";
    }




    /**
     * 首页登录页面-前台
     * @param model
     * @return
     */
    @RequestMapping(value="/logo",method = RequestMethod.GET)
    public String overs(Model model){
        model. addAttribute("msg", "");
        return "/logo.html";
    }

    /**
     * 跳转注册首页-前台
     * @param model
     * @return
     */
    @RequestMapping(value="/regSt",method = RequestMethod.GET)
    public String regist(Model model){
        model. addAttribute("msg", "");
        return "/regist.html";
    }


    /**
     * 登录
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/reLogins",method = RequestMethod.POST)
    public String loginBegin(HttpServletRequest request, Model model){
        String userName = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        TUser tUser = new TUser();
        tUser.setUserName(userName);
        tUser.setUserPassword(password);
        TUser dto = tUserService.getUserPass(tUser);
        log.info("用户登录结果:{}",dto);
        if(dto==null){
            String  msg = " <div class=\"errorHandler alert alert-danger padding-5\">\n" +
                    "                                            <i class=\"iconfont icon-removecircle\"></i>用户名或密码错误\n" +
                    "                                        </div>";
            model.addAttribute("msg",msg);
        }else{
            if(!("1").equals(dto.getUserStatus())){
                String msgd = " <div class=\"errorHandler alert alert-danger padding-5\">\n" +
                        "                                            <i class=\"iconfont icon-removecircle\"></i>请等待审核\n" +
                        "                                        </div>";
                model.addAttribute("msg",msgd);
            }else{
                HttpSession session = request.getSession();
                session. setAttribute("userName",userName);
                session. setAttribute("isLogin","1");
                session.setAttribute("companyName",dto.getCompanyName());
                session.setAttribute("orgNo",dto.getCompanyCode());
                //登录成功跳转到科创服务类别
                return "/success.html";
            }
        }
        return  "/logo.html";
    }



    @RequestMapping(value="/rest",method = RequestMethod.POST)
    public
    String registst(TUser tUser, HttpServletRequest request, Model model, @RequestParam("files") MultipartFile file){
        log.info("获取所有请求参数:{}",tUser);
        //本地上传项目控件
        String pathRoot = "C:\\Users\\zhangty\\yf项目\\guns\\target\\classes\\static\\imgs\\";
        String info ="";
        //获取所有注册信息表单,提交后上传图片信息,保存到user表中
        try {
            //服务器上传
            //String pathRoot =  ResourceUtils.getURL("classpath:").getPath()+"static/imgs/";
            info = FileUtil.uploadFile(file,pathRoot);
            log.info("保存数据库上传信息:{}",info);
            String uploadAdd = "/imgs/"+info;
            log.info("上传图片保存文件名:{}",uploadAdd);
            tUser.setCompanyUrlAdd(uploadAdd);
            tUserService.insertUser(tUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model. addAttribute("msg", "注册成功,审核中");
        return "/logo.html";
    }


    public static void main(String[] arge) throws  Exception{
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path+"static/imgs/");
    }

}