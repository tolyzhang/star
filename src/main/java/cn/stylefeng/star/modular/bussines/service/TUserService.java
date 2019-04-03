package cn.stylefeng.star.modular.bussines.service;

import cn.stylefeng.roses.core.util.MD5Util;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.star.core.common.exception.BizExceptionEnum;
import cn.stylefeng.star.modular.bussines.entity.TNews;
import cn.stylefeng.star.modular.bussines.entity.TUser;
import cn.stylefeng.star.modular.bussines.mapper.TUserMapper;
import cn.stylefeng.star.modular.util.ItemNoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class TUserService extends ServiceImpl<TUserMapper, TUser> {

    @Resource
    private TUserMapper tUserMapper;

    /**
     * 查询全部
     * @param page
     * @param industryType
     * @param userStatus
     * @param companyName
     * @param userName
     * @return
     */
    public List<Map<String, Object>> getUserList(Page page, String industryType, String userStatus,
    String companyName,String userName){
        return this.baseMapper.getUserList(page,industryType,userStatus,companyName,userName);
    }

    /**
     * 修改
     * @author zhangty
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editUser(TUser user) {
        if (ToolUtil.isOneEmpty(user.getId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        user.setUptTime(new Date());
        log.info("所有参数:{}",user);
        this.tUserMapper.editUser(user);
    }

    /**
     * 删除
     * @author
     * @Date 2018/12/23 5:16 PM
     */
    public void deleteTuser(Integer id) {
        log.info("执行用户删除:{}",id);
        this.tUserMapper.deleteUser(id);
    }

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    public TUser getByTUser(Integer id){
        log.info("执行查看详情:{}",id);
        TUser users = tUserMapper.getByTUserId(id);
        return  users;
    }

    /**
     * 登录验证
     * @param tUser
     * @return
     */
    public TUser getUserPass(TUser tUser){
        log.info("查询用户和密码是否正确");
        TUser dto = tUserMapper.getUserPass(tUser);
        return dto;
    }

    /**
     * 新增商户
     * @param tUser
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(TUser tUser){
        log.info("新增商户信息:{}",tUser);
        String password = tUser.getUserPassword();
        String newPassword = MD5Util.encrypt(password);
        tUser.setUserPassword(newPassword);
        tUser.setCrtTime(new Date());
        tUser.setId(1);
        tUserMapper.insertUser(tUser);
    }
}