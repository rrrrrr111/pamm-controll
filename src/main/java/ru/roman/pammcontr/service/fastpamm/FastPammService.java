package ru.roman.pammcontr.service.fastpamm;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.service.ServiceFactory;
import ru.roman.pammcontr.service.http.HttpClientService;



import java.util.*;

/**
 * Created by Roman on 29.01.2015.
 */
public class FastPammService {
    private static final Log log = LogFactory.getLog(FastPammService.class);

    public static final String FASTPAMM_HOST = "fastpamm.com";
    public static final String FASTPAMM_GETPAMMLIST_PATH = "/getpammlist.php";

    private HttpClientService httpClient = ServiceFactory.getHttpClientInstance();



    public List<String[]> getPammList() {


        final Map<String, String> params = new HashMap<String, String>();
        final String result = httpClient.executeGet(FASTPAMM_HOST, FASTPAMM_GETPAMMLIST_PATH, params);

        final String[] rows = StringUtils.splitPreserveAllTokens(result);
        if (rows == null || StringUtils.isBlank(result) || rows.length < 10) {
            log.warn(String.format("Service http://%s%s returns wrong data : %s ", FASTPAMM_HOST, FASTPAMM_GETPAMMLIST_PATH, result));
            return Collections.emptyList();
        } else {
            log.trace(String.format("Found %s pamms and indexes data rows", rows.length));
        }

        final List<String[]> res = new LinkedList<>();
        String[] row;
        for (int i = 0; i < rows.length; i++) {
            if (rows[i].length() < 20) {
                continue;
            }
            row = StringUtils.splitPreserveAllTokens(rows[i], (char)1);
            //log.info(">>>>>" + row.length + ">>>>>> " + rows[i]);
            res.add(row);
        }

        return res;
    }
}

