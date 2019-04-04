package cn.stylefeng.star.modular.bussines.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SessionDto implements Serializable {

    private String unitName;

    private Integer unitId;

    private String isLogin;

    private String  companyName;

    private String orgNo;

    private String companyAddress;

    private String telephone;

    private String email;

    private String postaCode;

    private String contacts;

    private String industrySectro;
}
