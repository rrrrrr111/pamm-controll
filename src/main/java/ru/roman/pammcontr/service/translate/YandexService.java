package ru.roman.pammcontr.service.translate;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.model.Lang;
import ru.roman.pammcontr.service.ServiceFactory;
import ru.roman.pammcontr.service.http.HttpClientService;
import ru.roman.pammcontr.service.translate.dto.YandexWordTranslation;

import java.util.HashMap;
import java.util.Map;

/** @author Roman 13.01.13 16:51 */
public class YandexService implements TranslationService{
    private static final Log log = LogFactory.getLog(YandexService.class);

    public static final String TRANSLATE_YANDEX_RU = "translate.yandex.ru";
    public static final String TRANSLATE_YANDEX_RU_PATH = "/tr.json/translate";
    public static final String TRANSLATE_YANDEX_NET = "translate.yandex.net";
    public static final String TRANSLATE_YANDEX_NET_PATH = "/dicservice.json/lookup";

    private HttpClientService httpClient = ServiceFactory.getHttpClientInstance();
    private Gson gson = new Gson();


    public YandexWordTranslation fromGson(String gsonRes) {
        return gson.fromJson(gsonRes, YandexWordTranslation.class);
    }

    @Override
    public String translate(String word, Long wordLandId, Long targetLandId) {
        if (StringUtils.isBlank(word)) {
            return "";
        }
        word = StringUtils.strip(word, " ,.");
        boolean expr = StringUtils.contains(word, " ");
        if (expr) {
            return translateExpression(word, wordLandId, targetLandId);
        } else {
            return translateWord(word, wordLandId, targetLandId);
        }
    }


    @Override
    public String translateExpression(String word, Long wordLandId, Long targetLandId) {
        //1. 2012 http://translate.yandex.ru/tr.json/translate?lang=ru-en&text=������
        //2. 2013.08.25 http://translate.yandex.net/tr.json/translate?lang=ru-en
        // &text=%D0%BF%D1%80%D0%B8%D0%B2%D0%B5%D1%82%20%D0%BC%D0%BE%D1%80%D0%BA%D0%BE%D0%B2%D0%BA%D0%B0&srv=tr-text


        final Map<String, String> params = new HashMap<String, String>();
        params.put("lang", createLangParamValue(wordLandId, targetLandId));
        params.put("text", word);
        params.put("srv", "tr-text");

        final String result = httpClient.executeGet(TRANSLATE_YANDEX_NET, TRANSLATE_YANDEX_RU_PATH, params);
        return StringUtils.strip(result, "\"");
    }

    @Override
    public String translateWord(String word, Long wordLandId, Long targetLandId) {
        //http://translate.yandex.net/dicservice.json/lookup?lang=ru-en&text=������

        final Map<String, String> params = new HashMap<String, String>();
        params.put("lang", createLangParamValue(wordLandId, targetLandId));
        params.put("text", word);

        final String gsonRes = httpClient.executeGet(TRANSLATE_YANDEX_NET, TRANSLATE_YANDEX_NET_PATH, params);
        YandexWordTranslation res = fromGson(gsonRes);
        return res.toUserString();
    }

    private String createLangParamValue(Long wordLandId, Long targetLandId) {
        return Lang.valueOf(wordLandId).getReductionLower() + "-" + Lang.valueOf(targetLandId).getReductionLower();
    }
}
