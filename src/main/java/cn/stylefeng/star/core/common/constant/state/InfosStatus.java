package cn.stylefeng.star.core.common.constant.state;

import lombok.Getter;

/**
 * @Auther: zhangty
 * @Date: 2019/3/26 21:39
 * @Description:行业状态
 * @Version:1.0.0
 */
@Getter
public enum InfosStatus {
    OK("0", "正常"),
    HANG("1", "挂起"),
    FREEZE("2", "冻结"),
    DELETED("3", "删除");
    String code;
    String message;

    InfosStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getDescription(String value) {
        if (value == null) {
            return "";
        } else {
            for (InfosStatus ms : InfosStatus.values()) {
                if (ms.getCode().equals(value)) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}
