package cn.stylefeng.star.core.common.constant.state;

import lombok.Getter;

/**
 * @Auther: zhangty
 * @Date: 2019/3/28 10:36
 * @Description:枚举类
 * @Version:1.0.0
 */
public class CreativeEnum {

    @Getter
    public enum CreativeStatus {
        STATUS_0("0", "处理中"),
        STATUS_1("1", "已完成"),
        STATUS_2("2","退回"),
        STATUS_3("3","其他");
        String code;
        String message;

        CreativeStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (CreativeEnum.CreativeStatus ms : CreativeEnum.CreativeStatus.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }

    //专家人才库状态
    @Getter
    public enum ExpertType {
        ALL_01("0", "处理中"),
        ALL_02("2", "已审核"),
        ALL_03("3", "已入库"),
        ALL_04("4", "已退回"),
        ALL_05("5", "其他");
        String code;
        String message;

        ExpertType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (CreativeEnum.ExpertType ms : CreativeEnum.ExpertType.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }


    //项目入库状态
    @Getter
    public enum WarehousType {
        ALL_01("0", "处理中"),
        ALL_02("2", "已审核"),
        ALL_03("3", "已入库"),
        ALL_04("4", "已退回"),
        ALL_05("5", "其他");
        String code;
        String message;

        WarehousType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (CreativeEnum.WarehousType ms : CreativeEnum.WarehousType.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }
}
