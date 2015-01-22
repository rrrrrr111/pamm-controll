package ru.roman.pammcontr.util;

import org.apache.commons.io.FileUtils;

/**
 *
 * User: Roman
 * DateTime: 01.09.12 12:08
 */
public interface Const {
    /**
     * � �������� ������:
     * - GaeConnectorStub ������ GaeConnectorImpl
     * - ���� � ����������� settings.dev ������ settings
     * - ����� �������� ��������� ��������� �������� ����
     * - ����������� � ���� ���������
     *
     */
    boolean DEV_MODE = false;  // ��������� ����� ������ ��� ������
    String DEFAULT_ENDPOINT = "https://bim-app.appspot.com/DataProvider";
    String DEFAULT_ENDPOINT_WSDL = "https://bim-app.appspot.com/wsdl/DataProvider.wsdl";
    //String DEFAULT_ENDPOINT = "http://localhost:8888/DataProvider";
    //String DEFAULT_ENDPOINT_WSDL = "http://localhost:8888/wsdl/DataProvider.wsdl";
    String VERSION = "1.49";


    String APP_NAME = "Bim";
    String APP_NAME_WITH_VERSION = APP_NAME + " " + VERSION;
    String APP_DATA_PATH = FileUtils.getUserDirectoryPath() + "/." + APP_NAME;
    String APP_CONFIG_PATH = APP_DATA_PATH + "/config";

    boolean SHOW_TRAY_NOTIFICATIONS = !DEV_MODE;

}

