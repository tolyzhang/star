package cn.stylefeng.star.modular.bussines.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zhangty
 * @Date: 2019/3/27 20:01
 * @Description:专业面
 * @Version:1.0.0
 */
@Data
public class TNewsDto  implements Serializable {

    private Integer ID;

    private String  NEW_TITLE;

    private String NEW_TYPE;

    private String NEW_CONTENT;

    private Date NEW_TIME;

    private Date NEW_UPT_TIME;

    private String NEW_STATUS;

    private String OPERATOR;
}
