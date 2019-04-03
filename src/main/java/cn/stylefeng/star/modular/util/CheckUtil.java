package cn.stylefeng.star.modular.util;

import cn.stylefeng.star.modular.bussines.model.SessionDto;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class CheckUtil {

    /**
     * 检查是否登陆
     * @param request
     * @return
     */
    public static String isLogins(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object islogin = session.getAttribute("isLogin");
        String isLogin ="0";
        log.info("获取是否登陆:{}",islogin);
        if(islogin!=null){
            isLogin = "1";
        }
        return  isLogin;
    }

    /**
     * 获取session对象
     * @param request
     * @return
     */
    public static SessionDto getSessionSr(HttpServletRequest request){
        SessionDto dto = new SessionDto();
        HttpSession session = request.getSession();
        Object islogin = session.getAttribute("isLogin");
        String isLogin ="0";
        log.info("获取是否登陆:{}",islogin);
        if(islogin!=null){
            isLogin = "1";
            dto.setUnitId(Integer.valueOf(session.getAttribute("id")+""));
            dto.setUnitName(session.getAttribute("userName")+"");
            dto.setCompanyName(session.getAttribute("companyName")+"");
            dto.setOrgNo(session.getAttribute("orgNo")+"");
            log.info("登陆成功拿session：{}",dto);
        }
        dto.setIsLogin(isLogin);
        return dto;

    }
}
