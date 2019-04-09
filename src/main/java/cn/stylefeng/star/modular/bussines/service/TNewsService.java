package cn.stylefeng.star.modular.bussines.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.star.core.common.exception.BizExceptionEnum;
import cn.stylefeng.star.modular.bussines.entity.TInfos;
import cn.stylefeng.star.modular.bussines.entity.TNews;
import cn.stylefeng.star.modular.bussines.mapper.TNewsMapper;
import cn.stylefeng.star.modular.system.entity.Dept;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class TNewsService extends ServiceImpl<TNewsMapper, TNews> {

    @Resource
    private TNewsMapper tNewsMapperl;
    /**
     * 查询全部科创成果
     * @param page
     * @param newType
     * @param newStatus
     * @return
     */
    public List<Map<String, Object>> getNewList(Page page, String newType, String newStatus,Integer proType,String newTitle){
        return this.baseMapper.getNewList(page,newType,newStatus,proType,newTitle);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addNews(TNews news) {
        if (ToolUtil.isOneEmpty(news.getNewContent(),news.getNewTitle(),news.getNewType())) {
            throw new RequestEmptyException();
        }
        news.setId(1);
        news.setNewTime(new Date());
        news.setOperator("admin");
        tNewsMapperl.addNews(news);
    }



    /**
     * 修改成果
     *
     * @author zhangty
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editNews(TNews news) {
        if (ToolUtil.isOneEmpty(news.getNewTitle(),news.getNewType(),news.getNewContent())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        news.setNewUptTime(new Date());
        news.setOperator("admin");
        log.info("所有参数:{}",news);
        this.tNewsMapperl.editNews(news);
    }

    /**
     * 删除
     * @author
     * @Date 2018/12/23 5:16 PM
     */
    @Transactional
    public void deleteTnews(Integer id) {
        this.removeById(id);
    }


}