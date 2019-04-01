package cn.stylefeng.star.modular.bussines.mapper;


import cn.stylefeng.star.modular.bussines.entity.TInfos;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface TInfosMapper extends   BaseMapper<TInfos>{

    /**
     * 保存行业信息
     * @param infos
     */
    void  addInfos(TInfos infos);

    /**
     * 查询全部行业信息
     * @param page
     * @param infoType
     * @param infoStatus
     * @return
     */
    List<Map<String, Object>> getInfoList(
            @Param("page") Page page,
            @Param("infoType") String infoType,
            @Param("infoStatus") String infoStatus
    );



}