package cn.zwellcode.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * 用来校验数据的合理性
 *
 * @author HM
 */
public class MatchUtil {
    /**
     * 校验手机号码的合理性
     */
    private static Pattern MAIN_PATTERN = Pattern.compile("^[A-Z][A-z0-9]*$");

    public static boolean isUpper(String mobiles) {
        Matcher m = MAIN_PATTERN.matcher(mobiles);
        return m.matches();
    }
}
