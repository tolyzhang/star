package cn.stylefeng.star.modular.bussines.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.star.core.common.exception.BizExceptionEnum;
import cn.stylefeng.star.modular.bussines.entity.Creative;
import cn.stylefeng.star.modular.bussines.entity.Expert;
import cn.stylefeng.star.modular.bussines.entity.TAnnex;
import cn.stylefeng.star.modular.bussines.entity.TPart;
import cn.stylefeng.star.modular.bussines.mapper.CreativeMapper;
import cn.stylefeng.star.modular.bussines.mapper.ExpertMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/3/19 17:32
 * @Description:专家人才库
 * @Version:1.0.0
 */
@Service
@Slf4j
public class ExpertService extends ServiceImpl<ExpertMapper, Expert> {

     @Resource
     private ExpertMapper expertMapper;

    /**
     *
     * @param page
     * @param expertName
     * @param expertWork
     * @param industryType
     * @param expertProfe
     * @param expertJob
     * @param crtTime
     * @return
     */
    public List<Map<String, Object>> getExpert(Page page,
                                                 String expertName,
                                                 String expertWork,
                                                 String industryType,
                                                 String expertProfe,
                                                 String expertJob,
                                                 String expertTime){
        return this.baseMapper.getExpertList(page, expertName,expertWork,industryType,expertProfe,expertJob,expertTime);
    }


    /**
     * 修改
     * @author zhangty
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editExpert(Expert dto) {
        if (ToolUtil.isOneEmpty(dto.getId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        dto.setExpertUptTime(new Date());
        log.info("所有参数:{}",dto);
        this.expertMapper.editExpert(dto);
    }


    public List<Expert> getDownExcel(Expert model){
        log.info("请求下载参数:{}",model);
        List<Expert> list = expertMapper.getDownExcel(model);
        return list;
    }


    public List<TAnnex> getUploadName(String itemNo){
        log.info("批量导出附件");
        List<TAnnex> list = expertMapper.selectByTannex(itemNo);
        log.info("所有返回的结果:{}",list.size());
        return list;
    }

    /**
     * 查询所有项目人员信息
     * @param itemNo
     * @return
     */
    public List<TPart> getPartList(String itemNo){
        log.info("获取项目相关人员信息:{}",itemNo);
        List<TPart> list = expertMapper.selectByPart(itemNo);
        log.info("所有项目相关人员结果:{}",list.size());
        return list;
    }

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    public Expert findById(Integer id){
        log.info("执行查看详情:{}",id);
        Expert dto = expertMapper.findById(id);
        //TUser users = tUserMapper.getByTUserId(id);
        return  dto;
    }

}
