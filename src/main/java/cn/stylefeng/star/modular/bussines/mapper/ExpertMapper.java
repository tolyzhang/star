package cn.stylefeng.star.modular.bussines.mapper;

import cn.stylefeng.star.modular.bussines.entity.Expert;
import cn.stylefeng.star.modular.bussines.entity.TAnnex;
import cn.stylefeng.star.modular.bussines.entity.TPart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/3/19 17:33
 * @Description:专家人才
 * @Version:1.0.0
 */
public interface ExpertMapper extends BaseMapper<Expert> {


    List<Map<String, Object>> getExpertList(
            @Param("page") Page page,
            @Param("expertName") String expertName,
            @Param("expertWork") String expertWork,
            @Param("industryType") String industryType,
            @Param("expertProfe") String expertProfe,
            @Param("expertJob") String expertJob,
            @Param("expertTime") String expertTime
    );

    void editExpert(Expert dto);

    void addExpert(Expert dto);

    Expert findById(@Param("id") Integer id);

    List<Expert> getDownExcel(Expert dto);

    List<TAnnex> selectByTannex(@Param("itemNo") String itemNo);

    List<TPart> selectByPart(@Param("itemNo") String itemNo);

}
