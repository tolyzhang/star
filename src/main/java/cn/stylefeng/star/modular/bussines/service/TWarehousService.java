package cn.stylefeng.star.modular.bussines.service;

import cn.stylefeng.star.modular.bussines.entity.TWarehous;
import cn.stylefeng.star.modular.bussines.mapper.TWarehousMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TWarehousService extends ServiceImpl<TWarehousMapper, TWarehous> {

    /**
     * 查询全部
     * @param page
     * @param param
     * @return
     */
    public List<Map<String, Object>> getTWarehous(Page page, Map<String,Object> param){
        return  this.baseMapper.getTWarehousList(page);
    }

}