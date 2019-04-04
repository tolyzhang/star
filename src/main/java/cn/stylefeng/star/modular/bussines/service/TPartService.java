package cn.stylefeng.star.modular.bussines.service;

import cn.stylefeng.star.modular.bussines.entity.TAnnex;
import cn.stylefeng.star.modular.bussines.entity.TPart;
import cn.stylefeng.star.modular.bussines.mapper.TAnnexMapper;
import cn.stylefeng.star.modular.bussines.mapper.TPartMapper;
import cn.stylefeng.star.modular.bussines.model.SessionDto;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Auther: zhangty
 * @Date: 2019/3/19 17:32
 * @Description:人员附属表
 * @Version:1.0.0
 */
@Service
@Slf4j
public class TPartService extends ServiceImpl<TPartMapper, TPart> {

     @Resource
     private TPartMapper tPartMapper;


    @Transactional(rollbackFor = Exception.class)
    public void addTPart(TPart dto, SessionDto sdto) {
        log.info("执行保存项目参与人附属数据信息");
          dto.setCrtTime(new Date());
          dto.setId(1);
          dto.setUnitId(sdto.getUnitId());
          dto.setUnitName(sdto.getUnitName());
        tPartMapper.addTpart(dto);
    }


}
