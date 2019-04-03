package cn.stylefeng.star.modular.bussines.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import cn.stylefeng.star.core.common.constant.factory.ConstantFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/3/26 17:58
 * @Description:字典转义
 * @Version:1.0.0
 */
public class ExpertWarpper extends BaseControllerWrapper {

    public ExpertWarpper(Map<String, Object> single) {
        super(single);
    }

    public ExpertWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public ExpertWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public ExpertWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }


    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("expertStatus",ConstantFactory.me().getCreativeStatus(map.get("expertStatus")+""));
        map.put("industryType",ConstantFactory.me().getCreativeType(map.get("industryType")+""));
    }

}
