package ru.roman.pammcontr.service.http;

import java.util.Map;

/** @author Roman 13.01.13 16:52 */
public interface HttpClientService {

    String executeGet(String host, String path, Map<String, String> params);
}
