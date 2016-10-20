package com.jungle.service.util;

import java.util.Arrays;
import java.util.List;

/**
 * ******************************************
 * <p>
 * Copyright 2016
 * Jungle All rights reserved
 * <p>
 * *****************************************
 * <p>
 * *** Company ***
 * Jungle
 * <p>
 * *****************************************
 * <p>
 * *** Team ***
 * jungle
 * <p>
 * *****************************************
 *
 * @author Jungle
 * @version V1.0
 * @Title DataUtil
 * @Package com.jungle.service.util
 * <p>
 * *****************************************
 * @Description
 * @date 2016/10/20
 */
public class DataUtil {
    public static final List<String> REGEX_EXP_LIST = Arrays.asList("\\", "$", "(", ")", "*", "+", ".", "[", "]",
            "?", "^", "{", "}", "|");
    public static final List<String> LIKE_EXP_LIST = Arrays.asList("%");

    /**
     * 转义正则保留字段
     *
     * @param raw
     * @return
     */
    public static String refactorRegexChar(String raw) {
        String refactor = raw;
        for (String s : REGEX_EXP_LIST) {
            if (refactor.contains(s)) {
                refactor = refactor.replace(s, "\\" + s);
            }
        }
        return refactor;
    }

    /**
     * 转义Like保留字段
     *
     * @param raw
     * @return
     */
    public static String refactorLikeChar(String raw) {
        String refactor = raw;
        for (String s : LIKE_EXP_LIST) {
            if (refactor.contains(s)) {
                refactor = refactor.replace(s, "\\" + s);
            }
        }
        return refactor;
    }
}
