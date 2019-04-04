package cn.stylefeng.star.modular.bussines.mapper;

import cn.stylefeng.star.modular.bussines.entity.TPart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface TPartMapper extends BaseMapper<TPart> {

    void addTpart(TPart dto);
}