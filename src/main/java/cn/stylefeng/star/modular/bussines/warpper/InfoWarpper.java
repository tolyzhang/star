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
public class InfoWarpper extends BaseControllerWrapper {

    public InfoWarpper(Map<String, Object> single) {
        super(single);
    }

    public InfoWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public InfoWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public InfoWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("INFO_STATUS",ConstantFactory.me().getInfoStatus(map.get("INFO_STATUS")+""));
    }

}
