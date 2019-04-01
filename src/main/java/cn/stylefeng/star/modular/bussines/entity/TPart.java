package cn.stylefeng.star.modular.bussines.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("T_PART")
public class TPart implements Serializable {
    private Integer id;

    private String itemNo;

    private String partName;

    private String partCode;

    private String partJob;

    private String partEdu;

    private Date crtTime;

    private Date uptTime;

    private Integer unitId;

    private String unitName;

    private Integer partStatus;

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

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartJob() {
        return partJob;
    }

    public void setPartJob(String partJob) {
        this.partJob = partJob;
    }

    public String getPartEdu() {
        return partEdu;
    }

    public void setPartEdu(String partEdu) {
        this.partEdu = partEdu;
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

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(Integer partStatus) {
        this.partStatus = partStatus;
    }
}