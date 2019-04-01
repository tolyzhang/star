package cn.stylefeng.star.modular.bussines.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TWarehous implements Serializable {
    private Integer id;

    private Integer unitId;

    private String unitName;

    private String creativeType;

    private String companyName;

    private String companyAddress;

    private String companyOrgNo;

    private String companyLegal;

    private String productPerson;

    private String productPhone;

    private String productEmail;

    private String companyBrief;

    private String productName;

    private String productTrade;

    private String productSurvey;

    private Integer enclosureId;

    private String warehouStatus;

    private Date crtTime;

    private Date uptTime;

    private String operator;

    private static final long serialVersionUID = 1L;


}