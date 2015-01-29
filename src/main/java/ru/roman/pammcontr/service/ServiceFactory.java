package ru.roman.pammcontr.service;


import ru.roman.pammcontr.service.config.CastorConfigServiceImpl;
import ru.roman.pammcontr.service.config.ConfigService;
import ru.roman.pammcontr.service.config.XmlConfigService;
import ru.roman.pammcontr.service.config.XmlConfigServiceImpl;


import ru.roman.pammcontr.service.fastpamm.FastPammService;
import ru.roman.pammcontr.service.http.HttpClientService;
import ru.roman.pammcontr.service.http.HttpClientServiceImpl;



/** @author Roman 22.12.12 15:37 */
public class ServiceFactory {

    private static volatile HttpClientService httpClientInstance;
    private static volatile XmlConfigService xmlConfigService;
    private static volatile ConfigService configService;
    private static volatile FastPammService fastPammService;


    public static synchronized HttpClientService getHttpClientInstance() {
        if (httpClientInstance == null) {
            httpClientInstance = new HttpClientServiceImpl();
        }
        return httpClientInstance;
    }

    public static synchronized FastPammService getFastPammService() {
        if (fastPammService == null) {
            fastPammService = new FastPammService();
        }
        return fastPammService;
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
