package com.zwj.basedemo.util;

/**
 * 用来获取字典对应的描述
 *
 * @author zwj
 */
public class DictionaryUtil {

    private DictionaryUtil() {
    }

    /**
     * 根据使用性质的id获取相应的描述
     *
     * @param useType
     * @return
     */
    public static String getUseTypeById(int useType) {
        switch (useType) {
            case 1:
                return "非营运";

            case 2:
                return "营运";

            case 3:
                return "营转非";

            case 4:
                return "军转挂";

            default:
                return "";
        }
    }

    /**
     * 根据车型Id获取车型描述
     *
     * @param styleId
     * @return
     */
    public static String getStyleById(int styleId) {
        switch (styleId) {
            case 1:
                return "轿车";

            case 2:
                return "SUV";

            case 3:
                return "MPV";

            case 4:
                return "跑车";

            default:
                return "";
        }
    }

}
