package cn.stylefeng.star.modular.bussines.mapper;


import cn.stylefeng.star.modular.bussines.entity.TDemand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TDemandMapper extends BaseMapper<TDemand> {

    List<Map<String, Object>> getDemandList(
            @Param("page") Page page
/*            @Param("creativeTile") String creativeTile,
            @Param("productName") String productName,
            @Param("productPerson") String productPerson,
            @Param("type") String type,
            @Param("orgNo") String orgNo,
            @Param("compamyName") String compamyName*/
    );


}