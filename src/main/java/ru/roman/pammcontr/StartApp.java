package ru.roman.pammcontr;


import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.common.cbchain.CallBackChain;
import ru.roman.pammcontr.gui.pane.PaineFactory;
import ru.roman.pammcontr.gui.pane.settings.Settings;

import ru.roman.pammcontr.gui.pane.tray.TrayUtils;
import ru.roman.pammcontr.model.UserSettingsModel;
import ru.roman.pammcontr.service.lock.LockerUtils;
import ru.roman.pammcontr.util.Const;
import ru.roman.pammcontr.util.ExceptionHandler;
import ru.roman.pammcontr.util.GuiUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


/** @author Roman 17.12.12 23:44 */
public class StartApp {
    private static final Log log = LogFactory.getLog(StartApp.class);

    public static void main(String args[]) {
        GuiUtil.startSwingApp(new CallBackChain<Void>() {
            @Override
            protected void onSuccess(Void result) {
                prepareEnvironment();
                prepareSettings(new CallBackChain<Void>() {
                    @Override
                    public void onSuccess(Void sett) {
                        PaineFactory.createMainView();
                        LockerUtils.tryLockApplication();
                    }

                    @Override
                    protected void onFailure(Exception e) {
                        ExceptionHandler.showErrorMessage(e);
                        prepareSettings(this);
                    }
                });
            }
        });
    }

    public static void stop(int exitCode) {
        try {
            TrayUtils.removeTrayIcon();
            log.info(Const.APP_NAME + " closed");
        } catch (Exception ex){
            log.info("Error while closing", ex);
        } finally {
            System.exit(exitCode);
        }
    }

    private static void prepareEnvironment() {
        try {
            File configDir = new File(Const.APP_CONFIG_PATH);
            if (!configDir.exists()) {
                FileUtils.forceMkdir(configDir);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void prepareSettings(CallBackChain<Void> callBack) {
        if (Settings.get() == null) {
            Settings.createInitialSettings();
        }
        callBack.run();
    }
}
