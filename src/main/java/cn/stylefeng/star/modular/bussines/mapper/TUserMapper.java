package cn.stylefeng.star.modular.bussines.mapper;

import cn.stylefeng.star.modular.bussines.entity.TNews;
import cn.stylefeng.star.modular.bussines.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface TUserMapper extends BaseMapper<TUser> {
    /**
     * 查询全部
     * @return
     */
    List<Map<String, Object>> getUserList(
            @Param("page") Page page,
            @Param("industryType") String industryType,
            @Param("userStatus") String userStatus,
            @Param("companyName") String companyName,
            @Param("userName") String userName
    );
    /**
     * 修改
     * @param
     */
    void editUser(TUser user);

    /**
     * 删除
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 根据ID 查询
     * @param id
     * @return
     */
    TUser getByTUserId(@Param("id") Integer id);

    /**
     * 查询用户名和密码
     * @param dto
     * @return
     */
    TUser getUserPass(TUser dto);

    /**
     * 新增用户
     * @param dto
     */
    void insertUser(TUser dto);

}