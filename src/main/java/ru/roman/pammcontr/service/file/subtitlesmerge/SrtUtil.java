package ru.roman.pammcontr.service.file.subtitlesmerge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** @author Roman 09.06.13 22:50 */
public class SrtUtil {
    protected static final Log log = LogFactory.getLog(SrtUtil.class);
    public static final int MILL_IN_SEC = 1000;
    public static final int MILL_IN_MIN = 60 * MILL_IN_SEC;
    public static final int MILL_IN_HOUR = 60 * MILL_IN_MIN;

    public static String calcStopTime(String stopTime) {
        long time = calcTime(stopTime);
        time = time - 1;
        StringBuilder str = new StringBuilder();

        {
            final Long hv = time / MILL_IN_HOUR;
            String h = hv.toString();
            if (h.length() == 1) {
                h = "0" + h;
            }
            time = time - hv * MILL_IN_HOUR;
            str.append(h).append(":");
        }
        {
            final Long mv = time / MILL_IN_MIN;
            String m = mv.toString();
            if (m.length() == 1) {
                m = "0" + m;
            }
            time = time - mv * MILL_IN_MIN;
            str.append(m).append(":");
        }
        {
            final Long sv = time / MILL_IN_SEC;
            String s = sv.toString();
            if (s.length() == 1) {
                s = "0" + s;
            }
            time = time - sv * MILL_IN_SEC;
            str.append(s).append(",").append(time);
        }
        return str.toString();
    }

    public static long calcTime(String str) {
        int ind = str.indexOf(":");
        if (ind == 1) {
            str = "0" + str;
        }
        long h = Long.valueOf(str.substring(0, 2));
        long m = Long.valueOf(str.substring(3, 5));
        long s = Long.valueOf(str.substring(6, 8));
        long ms = Long.valueOf(str.substring(9, 12));
        return (h * MILL_IN_HOUR + m * MILL_IN_MIN + s * MILL_IN_SEC + ms);
    }
}
