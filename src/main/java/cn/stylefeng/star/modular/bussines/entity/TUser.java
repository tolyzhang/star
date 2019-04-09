package cn.stylefeng.star.modular.bussines.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TUser implements Serializable {
    private Integer id;

    private String userName;

    private String userRealName;

    private String industryType;

    private Integer industrtId;

    private String userFax;

    private String userPassword;

    private String userPhone;

    private String userCode;

    private String userEmail;

    private String userStatus;

    private String userIp;

    private String companyName;

    private String companyCode;

    private String companyPerson;

    private String companyLicense;

    private String companyUrlAdd;

    private Date crtTime;

    private Date uptTime;

    private String userTel;

    private String companyAddress;

    private static final long serialVersionUID = 1L;

}