package cn.stylefeng.star.core.common.constant;

/**
 * @Auther: zhangty
 * @Date: 2019/3/26 15:10
 * @Description:
 * @Version:1.0.0
 */
public class TypesEnum {
    public enum TypesEnumSt implements java.io.Serializable{
        INFOS("行业信息","infos"),
        STANDARD("规范标准","standard"),
        FOCUS("重点实验室","focus"),
        EXPERT("专家人才库","expert");
        private String name;
        private String index;

        TypesEnumSt(String name, String index) {
            this.name = name;
            this.index = index;
        }


        public static String getName(String index) {
            for (TypesEnumSt reason : TypesEnumSt.values()) {
                if (reason.getIndex().equals(index)) {
                    return reason.name;
                }
            }
            return null;
        }

        public static String getIndex(String name) {
            for (TypesEnumSt reason : TypesEnumSt.values()) {
                if (reason.getName().equals(name)) {
                    return reason.index;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }
    }
}
