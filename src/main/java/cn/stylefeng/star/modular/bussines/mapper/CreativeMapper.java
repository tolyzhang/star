package cn.stylefeng.star.modular.bussines.mapper;

import cn.stylefeng.star.modular.bussines.entity.Creative;
import cn.stylefeng.star.modular.bussines.entity.TAnnex;
import cn.stylefeng.star.modular.bussines.entity.TPart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/3/19 17:33
 * @Description:
 * @Version:1.0.0
 */
public interface CreativeMapper extends BaseMapper<Creative> {

    /**
     *页面查询
     * @param page
     * @param creativeTile
     * @param productName
     * @param productPerson
     * @param industryType
     * @param orgNo
     * @param compamyName
     * @return
     */
    List<Map<String, Object>> getCreativeList(
            @Param("page") Page page,
            @Param("creativeTile") String creativeTile,
             @Param("productName") String productName,
            @Param("productPerson") String productPerson,
            @Param("industryType") String industryType,
            @Param("orgNo") String orgNo,
            @Param("companyName") String companyName
    );

    void editCreative(Creative dto);

    void addCreative(Creative dto);

    Creative findById(@Param("id") Integer id);

    List<Creative> getDownExcel(Creative creative);

    List<TAnnex> selectByTannex(@Param("itemNo") String itemNo);

    List<TPart> selectByPart(@Param("itemNo") String itemNo);

}
