package cn.stylefeng.star.modular.bussines.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.star.core.common.exception.BizExceptionEnum;
import cn.stylefeng.star.modular.bussines.entity.Expert;
import cn.stylefeng.star.modular.bussines.entity.TAnnex;
import cn.stylefeng.star.modular.bussines.entity.TPart;
import cn.stylefeng.star.modular.bussines.entity.TWarehous;
import cn.stylefeng.star.modular.bussines.mapper.ExpertMapper;
import cn.stylefeng.star.modular.bussines.mapper.TWarehousMapper;
import cn.stylefeng.star.modular.bussines.model.SessionDto;
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
public class TWarehousService extends ServiceImpl<TWarehousMapper, TWarehous> {
    @Resource
    private TWarehousMapper warehousMapper;
    /**
     * 查询全部
     * @param page
     * @param param
     * @return
     */
    public List<Map<String, Object>> getWarehous(Page page,
                                               String companyName,
                                               String companyOrgNo,
                                               String industryType,
                                               String productName,
                                               String productPerson,
                                               String crtTime){
        return this.baseMapper.getWarehousList(page, companyName,companyOrgNo,industryType,productName,productPerson,crtTime);
    }


    /**
     * ADD
     * @author zhangty
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addWarehous(TWarehous dto, SessionDto sdto) {
        if (ToolUtil.isOneEmpty(dto.getItemNo())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        dto.setId(1);
        dto.setCrtTime(new Date());
        dto.setAllType("2");
        dto.setUnitId(sdto.getUnitId());
        dto.setUnitName(sdto.getUnitName());
        log.info("所有参数:{}",dto);
        this.warehousMapper.addWarehous(dto);
    }


    /**
     * 修改
     * @author zhangty
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editWarehous(TWarehous dto) {
        if (ToolUtil.isOneEmpty(dto.getId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        dto.setUptTime(new Date());
        log.info("所有参数:{}",dto);
        this.warehousMapper.editWarehous(dto);
    }


    public List<TWarehous> getDownExcel(TWarehous model){
        log.info("请求下载参数:{}",model);
        List<TWarehous> list = warehousMapper.getDownExcel(model);
        return list;
    }


    public List<TAnnex> getUploadName(String itemNo){
        log.info("批量导出附件");
        List<TAnnex> list = warehousMapper.selectByTannex(itemNo);
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
        List<TPart> list = warehousMapper.selectByPart(itemNo);
        log.info("所有项目相关人员结果:{}",list.size());
        return list;
    }

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    public TWarehous findById(Integer id){
        log.info("执行查看详情:{}",id);
        TWarehous dto = warehousMapper.findById(id);
        return  dto;
    }
}