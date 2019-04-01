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
public class userWarpper extends BaseControllerWrapper {

    public userWarpper(Map<String, Object> single) {
        super(single);
    }

    public userWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public userWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public userWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("userStatus",ConstantFactory.me().getUserStatus(map.get("userStatus")+""));
        map.put("industryType",ConstantFactory.me().getUserType(map.get("industryType")+""));
    }

}
