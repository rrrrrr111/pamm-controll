package ru.roman.pammcontr.service;

import ru.roman.pammcontr.dev.stub.GaeConnectorStub;
import ru.roman.pammcontr.service.config.CastorConfigServiceImpl;
import ru.roman.pammcontr.service.config.ConfigService;
import ru.roman.pammcontr.service.config.XmlConfigService;
import ru.roman.pammcontr.service.config.XmlConfigServiceImpl;
import ru.roman.pammcontr.service.file.subtitlesmerge.SubtitlesMergeService;
import ru.roman.pammcontr.service.file.subtitlesmerge.SubtitlesMergeServiceImpl;
import ru.roman.pammcontr.service.file.textupload.TextUploadService;
import ru.roman.pammcontr.service.file.textupload.TextUploadServiceImpl;
import ru.roman.pammcontr.service.file.wordload.WordLoaderService;
import ru.roman.pammcontr.service.file.wordload.WordLoaderServiceImpl;
import ru.roman.pammcontr.service.gae.GaeConnector;
import ru.roman.pammcontr.service.gae.GaeConnectorImpl;
import ru.roman.pammcontr.service.http.HttpClientService;
import ru.roman.pammcontr.service.http.HttpClientServiceImpl;
import ru.roman.pammcontr.service.translate.GoogleService;
import ru.roman.pammcontr.service.translate.TranslationService;
import ru.roman.pammcontr.service.translate.YandexService;
import ru.roman.pammcontr.util.Const;

/** @author Roman 22.12.12 15:37 */
public class ServiceFactory {
    private static volatile GaeConnector gaeConnector;
    private static volatile HttpClientService httpClientInstance;
    private static volatile TranslationService yandexService;
    private static volatile TranslationService googleService;
    private static volatile WordLoaderService wordLoaderService;
    private static volatile XmlConfigService xmlConfigService;
    private static volatile ConfigService configService;
    private static volatile SubtitlesMergeService subtitlesMergeService;
    private static volatile TextUploadService textUploadService;


    public static synchronized GaeConnector getGaeConnector() {
        if (gaeConnector == null) {
            if (Const.DEV_MODE) {
                gaeConnector = new GaeConnectorStub();
            } else {
                gaeConnector = new GaeConnectorImpl();
            }
        }
        return gaeConnector;
    }

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

    public static synchronized WordLoaderService getWordLoaderService() {
        if (wordLoaderService == null) {
            wordLoaderService = new WordLoaderServiceImpl();
        }
        return wordLoaderService;
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

    public static synchronized SubtitlesMergeService getSubtitlesMergeService() {
        if (subtitlesMergeService == null) {
            subtitlesMergeService = new SubtitlesMergeServiceImpl();
        }
        return subtitlesMergeService;
    }

    public static synchronized TextUploadService getTextUploadService() {
        if (textUploadService == null) {
            textUploadService = new TextUploadServiceImpl();
        }
        return textUploadService;
    }


}
