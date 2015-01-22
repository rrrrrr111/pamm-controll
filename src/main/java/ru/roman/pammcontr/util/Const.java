package ru.roman.pammcontr.util;

import org.apache.commons.io.FileUtils;

/**
 *
 * User: Roman
 * DateTime: 01.09.12 12:08
 */
public interface Const {

    boolean DEV_MODE = true;  //

    String VERSION = "1.0";
    String APP_NAME = "PammControll";


    String APP_NAME_WITH_VERSION = APP_NAME + " " + VERSION;
    String APP_DATA_PATH = FileUtils.getUserDirectoryPath() + "/." + APP_NAME;
    String APP_CONFIG_PATH = APP_DATA_PATH + "/config";
    boolean SHOW_TRAY_NOTIFICATIONS = !DEV_MODE;

}

