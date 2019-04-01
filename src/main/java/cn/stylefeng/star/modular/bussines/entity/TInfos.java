package cn.stylefeng.star.modular.bussines.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("T_INFOS")
@Data
public class TInfos implements Serializable {

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Integer id;

    private String  infoTitle;

    private String infoType;

    private String infoContent;

    private String infoTag;

    private Date infoTime;

    private Date infoUptTime;

    private String operator;

    private Integer infoStatus;

    private static final long serialVersionUID = 1L;



    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getInfoTag() {
        return infoTag;
    }

    public void setInfoTag(String infoTag) {
        this.infoTag = infoTag;
    }

    public Date getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(Date infoTime) {
        this.infoTime = infoTime;
    }

    public Date getInfoUptTime() {
        return infoUptTime;
    }

    public void setInfoUptTime(Date infoUptTime) {
        this.infoUptTime = infoUptTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}