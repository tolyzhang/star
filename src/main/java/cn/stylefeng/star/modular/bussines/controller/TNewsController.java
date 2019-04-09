package cn.stylefeng.star.modular.bussines.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.star.core.common.annotion.BussinessLog;
import cn.stylefeng.star.core.common.annotion.Permission;
import cn.stylefeng.star.core.common.constant.Const;
import cn.stylefeng.star.core.common.constant.dictmap.DeptDict;
import cn.stylefeng.star.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.star.core.common.page.LayuiPageFactory;
import cn.stylefeng.star.core.log.LogObjectHolder;
import cn.stylefeng.star.modular.bussines.entity.TInfos;
import cn.stylefeng.star.modular.bussines.entity.TNews;
import cn.stylefeng.star.modular.bussines.model.TNewsDto;
import cn.stylefeng.star.modular.bussines.service.TInfosService;
import cn.stylefeng.star.modular.bussines.service.TNewsService;
import cn.stylefeng.star.modular.bussines.warpper.InfoWarpper;
import cn.stylefeng.star.modular.bussines.warpper.newWarpper;
import cn.stylefeng.star.modular.system.entity.Dept;
import cn.stylefeng.star.modular.system.model.DeptDto;
import cn.stylefeng.star.modular.util.TagUtil;
import cn.stylefeng.star.modular.util.UploadUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/3/21 09:00
 * @Description:行业信息
 * @Version:1.0.0
 */
@Controller
@RequestMapping("/news")
@Slf4j
public class TNewsController extends BaseController {

    @Autowired
    private TNewsService  tNewsService;

    private static String PREFIX = "/modular/bussines/news/";

    @RequestMapping("")
    public String list(){
        return PREFIX+"list.html";
    }

    /**
     * 科创成果
     * @author zhangty
     * @Date 2019/03/23 5:34 PM
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(
                       @RequestParam(required = false) String newType,
                       @RequestParam(required = false) String newStatus,
                        @RequestParam(required = false) String newTitle) {
        //获取分页参数
        Integer proType = 2;
        Page page = LayuiPageFactory.defaultPage();
       List<Map<String,Object>> result =tNewsService.getNewList(page,newType,newStatus,proType,newTitle);
        log.info("返回的结果:{}",result);
        page.setRecords(new newWarpper(result).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }


    /**
     * 跳转到添加
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("/news_add")
    public String newAdd() {
        return PREFIX + "news_add.html";
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
    public ResponseData add(TNews news, HttpServletRequest request) {
        log.info("获取保存参数:{}",news);
        String  counts = request.getParameter("newContent");
        news.setNewContent(counts);
        news.setProType(2);
        log.info("详情内容:{}",counts);
        this.tNewsService.addNews(news);
        return SUCCESS_TIP;
    }

    /**
     * 修改部门
     *
     * @author
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public ResponseData update(TNews news) {
        tNewsService.editNews(news);
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
        tNewsService.deleteTnews(id);
        return SUCCESS_TIP;
    }



    /**
     * 详情
     *
     * @author zhangty
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Integer id) {
        TNews news = tNewsService.getById(id);
        if(!("").equals(news.getNewContent())){
            news.setNewContent(TagUtil.getTagHtml(news.getNewContent()));
        }
         return news;
    }

    /**
     * 跳转到修改
     * @author zhangty
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("/news_update")
    public String newUpdate(@RequestParam Integer id) {
        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException();
        }
        TNews newst = tNewsService.getById(id);
        LogObjectHolder.me().set(newst);

        return PREFIX + "news_edit.html";
    }



}
