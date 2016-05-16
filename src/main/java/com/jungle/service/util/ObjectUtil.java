/*
 * Copyright (c) 2016.  ND.
 */

package com.jungle.service.util;

import java.util.Collection;
import java.util.Map;

/**
 * 对象工具静态方法
 *
 * @author 杨文军(132500)
 *         Created by jungle on 15/10/28.
 */
public class ObjectUtil {
    public static boolean convertObjectToBoolean(Object obj) {
        boolean resultValue;
        if(null == obj) {
            throw new RuntimeException("com.nd.smartq.interaction.util.ObjectUtil#convertObjectToBoolean, null cannot cast to boolean");
        }
        else if(obj instanceof Boolean) {
            resultValue = (Boolean) obj;
        }
        else if(obj instanceof String) {
            resultValue = Boolean.parseBoolean((String)obj);
        }
        else if(obj instanceof Long) {
            if(0L == (Long) obj) {
                resultValue = false;
            }
            else {
                resultValue = true;
            }
        }
        else if(obj instanceof Integer) {
            if(0 == (Integer) obj) {
                resultValue = false;
            }
            else {
                resultValue = true;
            }
        }
        else {
            throw new RuntimeException(String.format("%s cannot cast to boolean."));
        }
        return resultValue;
    }

    /**
     * 转换Object对象为boolean值
     * @param obj
     * @param defaultBoolean
     * @return
     */
    public static boolean convertObjectToBoolean(Object obj, boolean defaultBoolean) {
        boolean resultValue;
        try {
            resultValue = convertObjectToBoolean(obj);
        }
        catch (RuntimeException re) {
            resultValue = defaultBoolean;
        }
        return resultValue;
    }
    /**
     * 将object转换为int
     *
     * @param obj
     * @return
     * @throws NumberFormatException
     */
    public static int convertObjectToInt(Object obj) throws NumberFormatException {
        if (null == obj) {
            return 0;
        }
        int result;
        if (obj instanceof Long) {
            result = ((Long) obj).intValue();
        } else if (obj instanceof Double) {
            result = ((Double) obj).intValue();
        } else if (obj instanceof Integer) {
            result = (Integer) obj;
        } else if (obj instanceof String) {
            result = Integer.parseInt((String) obj);
        } else {
            throw new NumberFormatException(obj.toString());
        }
        return result;
    }

    /**
     * 将object转换为long
     *
     * @param obj
     * @return
     * @throws NumberFormatException
     */
    public static long convertObjectToLong(Object obj) throws NumberFormatException {
        if (null == obj) {
            return 0;
        }
        long result;
        if (obj instanceof Long) {
            result = ((Long) obj).longValue();
        } else if (obj instanceof Double) {
            result = ((Double) obj).longValue();
        } else if (obj instanceof Integer) {
            result = ((Integer) obj).longValue();
        } else if (obj instanceof String) {
            result = Long.parseLong((String) obj);
        } else {
            throw new NumberFormatException(obj.toString());
        }
        return result;
    }

    /**
     * 将object转换为long
     *
     * @param obj
     * @return
     * @throws NumberFormatException
     */
    public static double convertObjectToDouble(Object obj) throws NumberFormatException {
        if (null == obj) {
            return 0;
        }
        double result;
        if (obj instanceof Long) {
            result = ((Long) obj).doubleValue();
        } else if (obj instanceof Double) {
            result = ((Double) obj).doubleValue();
        } else if (obj instanceof Integer) {
            result = ((Integer) obj).doubleValue();
        } else if (obj instanceof String) {
            result = Double.parseDouble((String) obj);
        } else {
            throw new NumberFormatException(obj.toString());
        }
        return result;
    }

    /**
     * 获取object对应的string，null时返回empty string
     *
     * @param obj
     * @return
     */
    public static String getStringOfObject(Object obj) {
        if (null == obj) {
            return "";
        } else {
            return String.valueOf(obj);
        }
    }

    /**
     * 判断字符串是否有字符重复
     * @param s
     * @return
     */
    public static boolean containsDuplicateChar(String s) {
        boolean isDup = false;
        if(null == s || s.length() == 0) {
            return isDup;
        }
        for(int i=0; i<s.length(); i++) {
            for(int j=i+1; j<s.length(); j++) {
                if(s.charAt(j) == s.charAt(i)) {
                    isDup = true;
                    break;
                }
            }
            if(isDup) {
                break;
            }
        }
        return isDup;
    }

    /**
     * 判断字符串是否含有连续重复字符
     * @param s
     * @return
     */
    public static boolean containsContinuousDuplicateChar(String s) {
        boolean isDup = false;
        if(null == s || s.length() == 0) {
            return isDup;
        }
        for(int i=0; i<s.length(); i++) {
            if(i!=s.length()-1) {
                if(s.charAt(i) == s.charAt(i+1)) {
                    isDup = true;
                    break;
                }
            }
        }
        return isDup;
    }
    /**
     * 判断对象是否为空
     * 1. String
     * 2. Collection
     * 3. Map
     * 4. 其他
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }

        if (o instanceof String) {
            return "".equals((String) o);
        } else if (o instanceof Collection) {
            return ((Collection) o).isEmpty();
        } else if (o instanceof Map) {
            return ((Map) o).isEmpty();
        }
        return false;
    }
}
