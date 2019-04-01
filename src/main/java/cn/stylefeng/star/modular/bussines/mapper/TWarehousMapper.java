package cn.stylefeng.star.modular.bussines.mapper;

import cn.stylefeng.star.modular.bussines.entity.TWarehous;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TWarehousMapper  extends BaseMapper<TWarehous> {


    List<Map<String, Object>> getTWarehousList(
            @Param("page") Page page
    );
}