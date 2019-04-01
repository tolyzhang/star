package cn.stylefeng.star.modular.bussines.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import cn.stylefeng.star.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.star.modular.bussines.entity.Creative;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/3/26 17:58
 * @Description:字典转义
 * @Version:1.0.0
 */
public class CreativeWarpper extends BaseControllerWrapper {

    public CreativeWarpper(Map<String, Object> single) {
        super(single);
    }

    public CreativeWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public CreativeWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public CreativeWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }


    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("creativeStatus",ConstantFactory.me().getCreativeStatus(map.get("creativeStatus")+""));
        map.put("industryType",ConstantFactory.me().getCreativeType(map.get("industryType")+""));
    }

}
