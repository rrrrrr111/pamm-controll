package ru.roman.pammcontr.service;


import ru.roman.pammcontr.service.config.CastorConfigServiceImpl;
import ru.roman.pammcontr.service.config.ConfigService;
import ru.roman.pammcontr.service.config.XmlConfigService;
import ru.roman.pammcontr.service.config.XmlConfigServiceImpl;


import ru.roman.pammcontr.service.http.HttpClientService;
import ru.roman.pammcontr.service.http.HttpClientServiceImpl;
import ru.roman.pammcontr.service.translate.GoogleService;
import ru.roman.pammcontr.service.translate.TranslationService;
import ru.roman.pammcontr.service.translate.YandexService;
import ru.roman.pammcontr.util.Const;

/** @author Roman 22.12.12 15:37 */
public class ServiceFactory {

    private static volatile HttpClientService httpClientInstance;
    private static volatile TranslationService yandexService;
    private static volatile TranslationService googleService;
    private static volatile XmlConfigService xmlConfigService;
    private static volatile ConfigService configService;


    public static synchronized HttpClientService getHttpClientInstance() {
        if (httpClientInstance == null) {
            httpClientInstance = new HttpClientServiceImpl();
        }
        return httpClientInstance;
    }

    public static synchronized TranslationService getYandexService() {
        if (yandexService == null) {
            yandexService = new YandexService();
        }
        return yandexService;
    }

    public static synchronized TranslationService getGoogleService() {
        if (googleService == null) {
            googleService = new GoogleService();
        }
        return googleService;
    }

    public static synchronized XmlConfigService getXmlConfigService() {
        if (xmlConfigService == null) {
            xmlConfigService = new XmlConfigServiceImpl();
        }
        return xmlConfigService;
    }

    public static synchronized ConfigService getConfigService() {
        if (configService == null) {
            configService = new CastorConfigServiceImpl();
        }
        return configService;
    }


}
