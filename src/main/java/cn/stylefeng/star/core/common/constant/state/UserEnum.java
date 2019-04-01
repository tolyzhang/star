package cn.stylefeng.star.core.common.constant.state;

import lombok.Getter;

/**
 * @Auther: zhangty
 * @Date: 2019/3/28 10:36
 * @Description:注册商户相关枚举类
 * @Version:1.0.0
 */
public class UserEnum {

    @Getter
    public enum UserStatus {
        STATUS_0("0", "审核中"),
        STATUS_1("1", "审核通过"),
        STATUS_2("2","审核拒绝"),
        STATUS_3("3","已删除"),
        STATUS_4("4","冻结");
        String code;
        String message;

        UserStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (UserEnum.UserStatus ms : UserEnum.UserStatus.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }

    @Getter
    public enum UserType {
        ALL_01("1", "道路交通"),
        ALL_02("2", "绿色交通"),
        ALL_03("3", "智能交通"),
        ALL_04("4", "公路运输"),
        ALL_05("5", "铁路城轨运输"),
        ALL_06("6", "水上运输");
        String code;
        String message;

        UserType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (UserEnum.UserType ms : UserEnum.UserType.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }
}
