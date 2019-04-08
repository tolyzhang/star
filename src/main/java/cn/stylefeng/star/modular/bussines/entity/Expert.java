package cn.stylefeng.star.modular.bussines.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Expert implements Serializable {
    private Integer id;

    private Integer unitId;

    private String unitName;

    private String  itemNo;

    private String industryType;

    private String allType;

    private String expertName;

    private String expertSex;

    private String expertBirth;

    private String expertCardNo;

    private String expertPhone;

    private String expertEmail;

    private String expertSchool;

    private String expertProfe;

    private String expertEdu;

    private String expertWork;

    private String expertDepar;

    private String expertPosi;

    private String expertJob;

    private String expertGroup;

    private String expertResume;

    private String expertProject;

    private String expertSit;

    private String expertRecord;

    private String expertRewards;

    private String expertReview;

    private String expertPhoto;

    private Date  expertTime;

    private Integer expertStatus;

    private Date expertUptTime;

    private String operator;

    private Integer isAnnex;

    private static final long serialVersionUID = 1L;


}