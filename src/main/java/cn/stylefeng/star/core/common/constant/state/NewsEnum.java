package cn.stylefeng.star.core.common.constant.state;

import lombok.Getter;

/**
 * @Auther: zhangty
 * @Date: 2019/3/27 13:03
 * @Description:
 * @Version:1.0.0
 */
public class NewsEnum {

    @Getter
    public enum NewsStatus {
        OK("0", "发布中"),
        HANG("1", "已删除");
        String code;
        String message;

        NewsStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (NewsStatus ms : NewsStatus.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }

    @Getter
    public enum NewsType {
        ALL("0", "全部"),
        ALL_01("1", "道路交通"),
        ALL_02("2", "绿色交通"),
        ALL_03("3", "智能交通"),
        ALL_04("4", "公路运输"),
        ALL_05("5", "铁路城轨运输"),
        ALL_06("6", "水上运输");
        String code;
        String message;

        NewsType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (NewsType ms : NewsType.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }
}
