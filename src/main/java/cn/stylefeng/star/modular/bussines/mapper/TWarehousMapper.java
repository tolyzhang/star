package cn.stylefeng.star.modular.bussines.mapper;

import cn.stylefeng.star.modular.bussines.entity.Expert;
import cn.stylefeng.star.modular.bussines.entity.TAnnex;
import cn.stylefeng.star.modular.bussines.entity.TPart;
import cn.stylefeng.star.modular.bussines.entity.TWarehous;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TWarehousMapper  extends BaseMapper<TWarehous> {


    List<Map<String, Object>> getWarehousList(
            @Param("page") Page page,
            @Param("companyName") String companyName,
            @Param("companyOrgNo") String companyOrgNo,
            @Param("industryType") String industryType,
            @Param("productName") String productName,
            @Param("productPerson") String productPerson,
            @Param("crtTime") String crtTime
    );

    void editWarehous(TWarehous dto);

    void addWarehous(TWarehous dto);

    TWarehous findById(@Param("id") Integer id);

    List<TWarehous> getDownExcel(TWarehous dto);

    List<TAnnex> selectByTannex(@Param("itemNo") String itemNo);

    List<TPart> selectByPart(@Param("itemNo") String itemNo);


}