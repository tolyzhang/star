package cn.stylefeng.star.modular.bussines.service;

import cn.stylefeng.star.modular.bussines.entity.TDemand;
import cn.stylefeng.star.modular.bussines.mapper.TDemandMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TDemandService extends ServiceImpl<TDemandMapper, TDemand> {

    /**
     * 查询全部
     * @param page
     * @param param
     * @return
     */
    public List<Map<String, Object>> getDemand(Page page, Map<String,Object> param){
        return  this.baseMapper.getDemandList(page);
    }

}