package cn.stylefeng.star.modular.bussines.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zhangty
 * @Date: 2019/3/19 17:34
 * @Description:
 * @Version:1.0.0
 */
@Data
public class Creative implements Serializable {

    private Integer id;

    private Integer unitId;

    private String unitName;

    private String allType;

    private String  companyName;

    private String orgNo;

    private Date registTime;

    private String industryType;

    private String companyAddress;

    private String bussinessScope;

    private String telephone;

    private String email;

    private String postaCode;

    private String companyUrl;

    private String companyPersonNum;

    private String companyCapital;

    private String preYearAmt;

    private String preYearAssets;

    private String preYearLiab;

    private String bankName;

    private String bankCode;

    private String productName;

    private String productPerson;

    private String productPerId;

    private String productTitle;

    private String productEdu;

    private Integer productPartId;

    private String productImpleState;

    private String productImpleEnd;

    private String productAddress;

    private String productGround;

    private String productProg;

    private String productPlan;

    private String productBenefit;

    private String enclosure;

    private String expandA;

    private String expandB;

    private String expandC;

    private String expandD;

    private String creativeStatus;

    private Date crtTime;

    private Date uptTime;

    private String operator;

    private String creativeHappen;

    private String  postalCode;

    private String  itemNo;

    private String  isAnnex;

    private String declareTopic;

    private static final long serialVersionUID = 1L;


}
