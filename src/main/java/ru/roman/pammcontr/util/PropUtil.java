package ru.roman.pammcontr.util;

import org.apache.commons.beanutils.PropertyUtilsBean;

/** @author Roman 27.01.13 15:56 */
public class PropUtil {

    private static final PropertyUtilsBean pub = new PropertyUtilsBean();


    public static void copyProperties(Object dest, Object orig) {
        try {
            pub.copyProperties(dest, orig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
