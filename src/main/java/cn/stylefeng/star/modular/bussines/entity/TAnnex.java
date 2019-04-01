package cn.stylefeng.star.modular.bussines.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TAnnex implements Serializable {
    private Integer id;

    private String itemNo;

    private String allType;

    private String annexName;

    private String annexUrlAdd;

    private String annexType;

    private Integer annexStatus;

    private Date crtTime;

    private Date uptTime;

    private String operator;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getAllType() {
        return allType;
    }

    public void setAllType(String allType) {
        this.allType = allType;
    }

    public String getAnnexName() {
        return annexName;
    }

    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    public String getAnnexUrlAdd() {
        return annexUrlAdd;
    }

    public void setAnnexUrlAdd(String annexUrlAdd) {
        this.annexUrlAdd = annexUrlAdd;
    }

    public String getAnnexType() {
        return annexType;
    }

    public void setAnnexType(String annexType) {
        this.annexType = annexType;
    }

    public Integer getAnnexStatus() {
        return annexStatus;
    }

    public void setAnnexStatus(Integer annexStatus) {
        this.annexStatus = annexStatus;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Date getUptTime() {
        return uptTime;
    }

    public void setUptTime(Date uptTime) {
        this.uptTime = uptTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}