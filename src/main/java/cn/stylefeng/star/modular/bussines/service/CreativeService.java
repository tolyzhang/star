package cn.stylefeng.star.modular.bussines.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.star.core.common.exception.BizExceptionEnum;
import cn.stylefeng.star.modular.bussines.entity.Creative;
import cn.stylefeng.star.modular.bussines.entity.TAnnex;
import cn.stylefeng.star.modular.bussines.entity.TPart;
import cn.stylefeng.star.modular.bussines.entity.TUser;
import cn.stylefeng.star.modular.bussines.mapper.CreativeMapper;
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
 * @Description:
 * @Version:1.0.0
 */
@Service
@Slf4j
public class CreativeService  extends ServiceImpl<CreativeMapper, Creative> {

    @Resource
     private CreativeMapper creativeMapper;

    /**
     * 查询creative结果
     * @param page 分页
     * @param creativeTile 项目标题
     * @param productName 项目名称
     * @param productPerson 项目负责人
     * @param industryType 类型
     * @param orgNo 组织结构代码证
     * @param compamyName 公司名称
     * @return
     */
    public List<Map<String, Object>> getCreative(Page page,
                                                 String creativeTile,
                                                 String productName,
                                                 String productPerson,
                                                 String industryType,
                                                 String orgNo,
                                                 String companyName){
        return this.baseMapper.getCreativeList(page, creativeTile,productName,productPerson,industryType,orgNo,companyName);
    }


    /**
     * 修改
     * @author zhangty
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editCreative(Creative dto) {
        if (ToolUtil.isOneEmpty(dto.getId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        dto.setUptTime(new Date());
        log.info("所有参数:{}",dto);
        this.creativeMapper.editCreative(dto);
    }


    public List<Creative> getDownExcel(Creative model){
        log.info("请求下载参数:{}",model);
        List<Creative> list = creativeMapper.getDownExcel(model);
        return list;
    }


    public List<TAnnex> getUploadName(String itemNo){
        log.info("批量导出附件");
        List<TAnnex> list = creativeMapper.selectByTannex(itemNo);
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
        List<TPart> list = creativeMapper.selectByPart(itemNo);
        log.info("所有项目相关人员结果:{}",list.size());
        return list;
    }

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    public Creative findById(Integer id){
        log.info("执行查看详情:{}",id);
        Creative dto = creativeMapper.findById(id);
        //TUser users = tUserMapper.getByTUserId(id);
        return  dto;
    }

}
