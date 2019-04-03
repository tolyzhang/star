package cn.stylefeng.star.modular.bussines.mapper;

import cn.stylefeng.star.modular.bussines.entity.TNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TNewsMapper extends BaseMapper<TNews> {

    /**
     * 查询全部成果转化信息
     * @param page
     * @param newType
     * @param newStatus
     * @return
     */
    List<Map<String, Object>> getNewList(
            @Param("page") Page page,
            @Param("newType") String newType,
            @Param("newStatus") String newStatus,
            @Param("proType") Integer proType,
            @Param("newTitle") String newTitle
    );

    /**
     * 保存所有信息
     * @param news
     */
    void  addNews(TNews news);

    void editNews(TNews news);
}