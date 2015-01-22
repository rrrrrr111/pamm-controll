package ru.roman.pammcontr.service.http;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import ru.roman.pammcontr.util.AppException;

import java.net.URI;
import java.util.Map;

/** @author Roman 13.01.13 14:42 */
public class HttpClientServiceImpl implements HttpClientService {
    private static final Log log = LogFactory.getLog(HttpClientServiceImpl.class);

    private final HttpClient httpClient = new DefaultHttpClient();


    @Override
    public String executeGet(String host, String path, Map<String, String> params) {

        HttpEntity entity = null;
        try {
            final URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(host).setPath(path);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.setParameter(entry.getKey(), entry.getValue());
            }
            final URI uri = builder.build();
            final HttpGet httpget = new HttpGet(uri);
            final HttpResponse response = httpClient.execute(httpget);
            entity = response.getEntity();
            if (entity != null) {
                long len = entity.getContentLength();
                //if (len > -1){
                    return EntityUtils.toString(entity);
                //} else {
                //    throw new BimException(String.format("Content length %s not supported by service", len));
                //}
            } else {
                throw new AppException(String.format("Response entity is null"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception in HTTP-Client service", e);
        } finally {
            EntityUtils.consumeQuietly(entity);
        }
    }

}
