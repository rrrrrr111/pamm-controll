package ru.roman.pammcontr.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/** @author Roman 13.01.13 18:24 */
public abstract class GsonUtil {

    public static final Writer DEFAULT_WRITER = new DefaultWriter();
    public static final Writer STUB_WRITER = new StubWriter();
    public static final Writer SIMPLE_WRITER = new SimpleWriter();

    public static final String DEFAULT_WRITER_KEY = "GsonUtil.defaultWriter.specialKey";

    public static String processGson(Object obj) {
        return processGson(obj, Collections.EMPTY_MAP);
    }

    public static String processGson(Object obj, Map<String, Writer> writers) {
        return processGson(null, obj, writers, 0);
    }

    public static String processGson(String key, Object obj, Map<String, Writer> writers, int level) {
        StringBuilder buff = new StringBuilder();
        processGson(key, obj, buff, writers, level);
        return StringUtils.strip(buff.toString(), ", ");
    }

    private static void processGson(String key, Object obj, StringBuilder buff, Map<String, Writer> writers, int level) {
        level++;
        if (obj != null) {
            if (obj instanceof List) {
                final List target = (List)obj;
                for (Object o : target) {
                    processGson(key, o, buff, writers, level);
                }
            } else if (obj instanceof Map) {
                final Map<String, Object> target = (Map<String, Object>)obj;
                final StringBuilder tempBuff = new StringBuilder();
                for (Map.Entry<String, Object> entry : target.entrySet()) {
                    final String entryPart = processGson(entry.getKey(), entry.getValue(), writers, level);
                    tempBuff.append(write(key, entry.getKey(), entryPart, level, writers, GsonType.KEYED));
                }
                buff.append(write(key, null, tempBuff.toString(), level, writers, GsonType.VALUE));
            } else {
                buff.append(write(key, null, obj, level, writers, GsonType.VALUE));
            }
        }
    }

    private static String write(String parentKey, String key, Object value, int level, Map<String, Writer> writers, GsonType type) {
        String str = value.toString();
        str = StringUtils.strip(str, ", ");
        if (writers.containsKey(key)) {
            return writers.get(key).write(parentKey, key, str, level, type);
        } else if (writers.containsKey(parentKey)){
            return writers.get(parentKey).write(parentKey, key, str, level, type);
        } else if (writers.containsKey(DEFAULT_WRITER_KEY)){
            return writers.get(DEFAULT_WRITER_KEY).write(parentKey, key, str, level, type);
        } else {
            return DEFAULT_WRITER.write(parentKey, key, str, level, type);
        }
    }

    public static Writer createSimpleWrapper(final String wrapKey) {
        return new Writer() {
            @Override
            public String write(String parentKey, String key, String str, int level, GsonUtil.GsonType type) {
                if (type == GsonUtil.GsonType.KEYED) {
                    return wrapKey + ":(" + str + "), ";
                }
                return GsonUtil.simpleWrite(str);
            }
        };
    }

    public static class DefaultWriter implements Writer{
        @Override
        public String write(String parentKey, String key, String str, int level, GsonType type) {
            str = StringUtils.normalizeSpace(str.toString());
            switch (type) {
                case KEYED:
                    return "" + key + ":[" + str + "], ";
                case VALUE:
                    return str + ", ";
                default:
                    throw new RuntimeException(String.format("Unknown type %s", type));
            }
        }
    }

    public static class SimpleWriter implements Writer{
        @Override
        public String write(String parentKey, String key, String value, int level, GsonType type) {
            return simpleWrite(value);
        }
    }

    public static class StubWriter implements Writer{
        @Override
        public String write(String parentKey, String key, String value, int level, GsonType type) {
            return "";
        }
    }

    public static interface Writer {
        String write(String parentKey, String key, String value, final int level, GsonType type);
    }

    public static enum GsonType {
        KEYED,
        VALUE
    }


    public static String simpleWrite(String value) {
        return simpleWrite(value, false);
    }

    public static String simpleWrite(String str, boolean commaLess) {
        return str + (commaLess ? " ": ", ");
    }
}
