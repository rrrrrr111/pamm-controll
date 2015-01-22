package ru.roman.pammcontr.service.translate;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.service.ServiceFactory;
import ru.roman.pammcontr.service.http.HttpClientService;
import ru.roman.pammcontr.service.translate.dto.YandexWordTranslation;
import ru.roman.pammcontr.util.BimException;

import java.util.*;

/** @author Roman 13.01.13 16:51 */
public class GoogleService implements TranslationService{
    private static final Log log = LogFactory.getLog(GoogleService.class);

    public static final String TRANSLATE_GOOGLE = "translate.google.ru";
    public static final String TRANSLATE_GOOGLE_PATH = "/translate_a/t";

    private HttpClientService httpClient = ServiceFactory.getHttpClientInstance();


    @Override
    public String translate(String word, Long wordLandId, Long targetLandId) {
        if (StringUtils.isBlank(word)) {
            return "";
        }
        return translateExpression(word, wordLandId, targetLandId);
    }


    @Override
    public String translateExpression(String word, Long wordLandId, Long targetLandId) {
        //http://translate.google.ru/translate_a/t?client=t&sl=ru&tl=en&hl=ru&sc=2&ie=UTF-8&oe=UTF-8&pc=1&oc=1&otf=2&trs=1&inputm=1&srcrom=1&ssel=3&tsel=6&q=%D0%BF%D1%80%D0%B8%D0%B2%D0%B5%D1%82

        final Map<String, String> params = new HashMap<String, String>();
        params.put("client", "t");
        params.put("oe", "UTF-8");
        params.put("pc", "1");
        params.put("q", word);

        final String result = httpClient.executeGet(TRANSLATE_GOOGLE, TRANSLATE_GOOGLE_PATH, params);
        if (StringUtils.isBlank(result)) {
            return "";
        }
        final List<Object> objList = fromBracketsToArrays(result);
        if (objList.size() < 2) {
            return "";
        }
        final StringBuilder str = new StringBuilder();

        if (objList.get(0) instanceof List
                && ((List) objList.get(0)).get(0) instanceof List) {
            str.append(((List)((List) objList.get(0)).get(0)).get(0)).append("\n");
        }
        if (objList.get(1) instanceof List) {
            for (Object obj: (List<Object>)objList.get(1)) {
                if (((List<Object>)obj).size() < 2) {
                    log.warn("Wrong data from translation provider, can't parse : " + objList);
                    return "";
                } else {
                    final String type = (String)((List<Object>)obj).get(0);
                    final List<Object> translations = (List<Object>)((List<Object>)obj).get(1);
                    str.append(type).append(":\n");
                    for (Object translation : translations) {
                        str.append(" - ").append(translation).append("\n");
                    }
                }
            }
        }
        if (str.length() == 0) {
            log.warn("Translation haven't found in object : " + objList);
        }
        return StringUtils.strip(str.toString());
    }

    private List<Object> fromBracketsToArrays(String result) {
        final char[] chArr = result.toCharArray();
        final int startIdx = 0;
        final List<Object> objList = extractList(chArr, new int[]{startIdx}, startIdx);
        return objList;
    }

    private List<Object> extractList(char[] chArr, int[] idx, int startIdx) {
        final List<Object> words = new LinkedList<Object>();
        final List<Character> word = new LinkedList<Character>();
        for (; idx[0] < chArr.length; idx[0]++) {
            final char c = chArr[idx[0]];
            if (idx[0] == startIdx) {
                if (c != '[') {
                    throw new BimException("Error while parsing string array, first symbol must be bracket : "
                    + String.valueOf(chArr));
                }
                continue;
            } else if (c == '[') {
                final List<Object> internalList = extractList(chArr, idx, idx[0]);
                if (!internalList.isEmpty()) {
                    words.add(internalList);
                }
            } else if (c == ',' || c == ']'){
                if (!word.isEmpty()) {
                    final Character[] wordArr = word.toArray(new Character[word.size()]);
                    final String strWord = String.valueOf(ArrayUtils.toPrimitive(wordArr));
                    if (StringUtils.isNotBlank(strWord)) {
                        words.add(StringUtils.strip(strWord, " \"'"));
                    }
                    word.clear();
                }
                if (c == ']') {
                    break;
                }
            } else {
                word.add(c);
            }
        }
        return words;
    }


    @Override
    public String translateWord(String word, Long wordLandId, Long targetLandId) {
        return translateExpression(word, wordLandId, targetLandId);
    }

    @Override
    public YandexWordTranslation fromGson(String gsonRes) {
        throw new RuntimeException("not implemented");
    }
}
