package ru.roman.pammcontr.service.translate.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import ru.roman.pammcontr.util.GsonUtil;

import java.util.HashMap;
import java.util.Map;

/** @author Roman 13.01.13 17:58 */
public class YandexWordTranslation {
    private static final Map<String,GsonUtil.Writer> WRITERS = new HashMap<String, GsonUtil.Writer>();

    static {
        WRITERS.put(GsonUtil.DEFAULT_WRITER_KEY, GsonUtil.SIMPLE_WRITER);
        WRITERS.put("pos", GsonUtil.STUB_WRITER);
        WRITERS.put("syn", GsonUtil.createSimpleWrapper("Syn"));
        WRITERS.put("mean", GsonUtil.createSimpleWrapper("Transl"));
        WRITERS.put("ex", GsonUtil.createSimpleWrapper("Examp"));
        WRITERS.put("text", new GsonUtil.Writer() {
            @Override
            public String write(String parentKey, String key, String str, int level, GsonUtil.GsonType type) {
                if ("ex".equals(parentKey) && type == GsonUtil.GsonType.KEYED && level == 6) {
                    return str + " - ";
                } else if (level == 2) {
                    return "";
                }
                return GsonUtil.simpleWrite(str);
            }
        });
    }
    private class CountWriter implements GsonUtil.Writer {
        int counter = 0;
        @Override
        public String write(String parentKey, String key, String value, int level, GsonUtil.GsonType type) {
            if (level == 4) {
                ++counter;
                return (counter > 1 ? "\n" : "") + counter + ". " + GsonUtil.simpleWrite(value, true);
            }
            return GsonUtil.simpleWrite(value);
        }
    }


    private Object head;
    private Object def;
    private Object link;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String toUserString() {

        Map<String, GsonUtil.Writer> wrs = new HashMap<String, GsonUtil.Writer>(WRITERS);
        wrs.put("tr", new CountWriter());
        return GsonUtil.processGson(def, wrs);
    }

    public Object getHead() {
        return head;
    }

    public Object getDef() {
        return def;
    }

    public Object getLink() {
        return link;
    }
}
