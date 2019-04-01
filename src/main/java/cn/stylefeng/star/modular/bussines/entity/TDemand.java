package cn.stylefeng.star.modular.bussines.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TDemand implements Serializable {
    private Integer id;

    private Integer unitId;

    private String unitName;

    private String demandType;

    private String companyName;

    private String companyAddress;

    private String demandPerson;

    private String demandPhone;

    private String demandEmail;

    private String companySurvey;

    private String productTrade;

    private String productSurvey;

    private String productDemand;

    private String demandStatus;

    private Date crtTime;

    private Date uptTime;

    private String operator;

    private static final long serialVersionUID = 1L;


}