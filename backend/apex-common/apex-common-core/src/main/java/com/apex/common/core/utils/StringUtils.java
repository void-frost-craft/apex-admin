package com.apex.common.core.utils;

import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Map;

/**
 * 字符串工具类
 *
 * @author apex
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /** 空字符串 */
    private static final String NULL_STR = "";

    /** 下划线 */
    private static final char SEPARATOR = '_';

    /**
     * 判断是否为空或者空字符串
     */
    public static boolean isEmpty(Object str) {
        return str == null || NULL_STR.equals(str);
    }

    /**
     * 判断是否不为空且不是空字符串
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**
     * 判断集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断 Map 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 驼峰转下划线
     */
    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean preCharIsUpperCase;
        boolean curCharIsUpperCase;
        boolean nextCharIsUpperCase;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }
            curCharIsUpperCase = Character.isUpperCase(c);
            if (i < (str.length() - 1)) {
                nextCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            } else {
                nextCharIsUpperCase = false;
            }
            if (preCharIsUpperCase && curCharIsUpperCase && !nextCharIsUpperCase) {
                sb.append(SEPARATOR);
            } else if ((i != 0 && !preCharIsUpperCase) && curCharIsUpperCase) {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     */
    public static String toCamelCase(String str) {
        if (str == null) {
            return null;
        }
        str = str.toLowerCase();
        StringBuilder sb = new StringBuilder(str.length());
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 匹配路径
     */
    public static boolean matches(String path, String pattern) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, path);
    }

    /**
     * 截取字符串
     */
    public static String substring(final String str, int start) {
        if (str == null) {
            return NULL_STR;
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULL_STR;
        }
        return str.substring(start);
    }

    /**
     * 截取字符串
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return NULL_STR;
        }
        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start > end) {
            return NULL_STR;
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }
}
