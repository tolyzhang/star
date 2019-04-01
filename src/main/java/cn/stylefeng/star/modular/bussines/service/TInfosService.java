package cn.stylefeng.star.modular.bussines.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.star.core.common.constant.TypesEnum;
import cn.stylefeng.star.modular.bussines.entity.TInfos;
import cn.stylefeng.star.modular.bussines.mapper.TInfosMapper;
import cn.stylefeng.star.modular.system.entity.Role;
import cn.stylefeng.star.modular.util.TagUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TInfosService extends ServiceImpl<TInfosMapper, TInfos> {

    @Resource
    private  TInfosMapper tInfosMapper;

    @Transactional(rollbackFor = Exception.class)
    public void addInfo(TInfos infos) {
        if (ToolUtil.isOneEmpty(infos.getInfoContent(),infos.getInfoTitle())) {
            throw new RequestEmptyException();
        }
        infos.setId(1);
        infos.setInfoTime(new Date());
        infos.setOperator("admin");
        tInfosMapper.addInfos(infos);
    }


    public List<Map<String, Object>> getInfoList(Page page, String infoType, String infoStatus){
        return this.baseMapper.getInfoList(page,infoType,infoStatus);
    }

    public static void main(String[] arge){
        String str = "& lt;p& gt;& lt;b& gt;没有人所烧师德师风是的& lt;/b& gt;& lt;/p& gt;& lt;p& gt;& lt;b& gt;& lt;i& gt;胜多负少的方式大幅杀跌发sdfsdfsdf& lt;/i& gt;& lt;/b& gt;& lt;/p& gt;& lt;p& gt;& lt;b& gt;& lt;i& gt;& lt;u& gt;水电费离开稍等积分流口水的减肥了空间看& lt;/u& gt;& lt;/i& gt;& lt;/b& gt;& lt;/p& gt;& lt;p& gt;& lt;b& gt;& lt;i& gt;& lt;strike style=\"\"& gt;适得府君书浪蝶狂蜂极乐空间3拉加料酒了&nbsp;& lt;/strike& gt;& lt;/i& gt;& lt;/b& gt;& lt;/p& gt;& lt;p& gt;& lt;p& gt;& lt;b& gt;& lt;i& gt;& lt;strike style=\"\"& gt;& lt;img src=\"/uploads/2019/3/20190326150401.png\" alt=\"20190326150401.png\"& gt;& lt;/strike& gt;& lt;/i& gt;& lt;/b& gt;& lt;/p& gt;& lt;p& gt;& lt;p& gt;& lt;b& gt;& lt;i& gt;& lt;strike style=\"\"& gt;& lt;img src=\"/uploads/2019/3/20190326150406.jpg\" alt=\"20190326150406.jpg\"& gt;& lt;/strike& gt;& lt;/i& gt;& lt;/b& gt;& lt;/p& gt;& lt;p& gt;& lt;b& gt;& lt;i& gt;& lt;strike style=\"\"& gt;& lt;img src=\"/uploads/2019/3/20190326150410.png\" alt=\"20190326150410.png\"& gt;& lt;br& gt;& lt;/strike& gt;& lt;/i& gt;& lt;/b& gt;& lt;/p& gt;& lt;/p& gt;& lt;/p& gt;";
        String ret = TagUtil.getTagHtml(str);
        System.out.println(ret);
    }


}