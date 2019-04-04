package cn.stylefeng.star.modular.bussines.mapper;

import cn.stylefeng.star.modular.bussines.entity.TAnnex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface TAnnexMapper extends BaseMapper<TAnnex> {

    void  tannexAdd(TAnnex dto);

}