package cn.stylefeng.star.modular.bussines.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.star.core.common.exception.BizExceptionEnum;
import cn.stylefeng.star.modular.bussines.entity.Expert;
import cn.stylefeng.star.modular.bussines.entity.TAnnex;
import cn.stylefeng.star.modular.bussines.entity.TPart;
import cn.stylefeng.star.modular.bussines.mapper.ExpertMapper;
import cn.stylefeng.star.modular.bussines.mapper.TAnnexMapper;
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
 * @Description:附件信息保存
 * @Version:1.0.0
 */
@Service
@Slf4j
public class TAnnexService extends ServiceImpl<TAnnexMapper, TAnnex> {

     @Resource
     private TAnnexMapper tAnnexMapper;


    @Transactional(rollbackFor = Exception.class)
    public void addTAnnex(TAnnex dto) {
        log.info("执行保存附件数据信息");
          dto.setCrtTime(new Date());
          dto.setId(1);
          tAnnexMapper.tannexAdd(dto);
    }


}
