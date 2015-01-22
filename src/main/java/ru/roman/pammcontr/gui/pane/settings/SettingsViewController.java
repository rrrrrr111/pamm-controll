package ru.roman.pammcontr.gui.pane.settings;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.StartApp;
import ru.roman.pammcontr.gui.common.cbchain.CallBackChain;
import ru.roman.pammcontr.gui.common.mvc.Controller;
import ru.roman.pammcontr.gui.pane.PaineFactory;
import ru.roman.pammcontr.service.ServiceFactory;
import ru.roman.pammcontr.service.cache.LocalCache;
import ru.roman.pammcontr.service.config.ConfigService;

import ru.roman.pammcontr.model.UserSettingsModel;
import ru.roman.pammcontr.util.BimException;
import ru.roman.pammcontr.util.GuiUtil;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/** @author Roman 16.01.13 23:59 */
public class SettingsViewController extends Controller<SettingsView, SettingsViewModel> {
    private static final Log log = LogFactory.getLog(SettingsViewController.class);


    private final SettingsViewValidator validator = new SettingsViewValidator();
    private final ConfigService configService = ServiceFactory.getConfigService();

    private State state = State.REGISTERED;
    private CallBackChain<UserSettingsModel> callBack;

    public SettingsViewController(SettingsView view) {
        super(view);
    }

    public void onInit() {

    }

    public void fillCredentials(CallBackChain<UserSettingsModel> callBack) {
        state = State.FIRST_INPUT;
        this.callBack = callBack;
        view.prepareForFirstInput();
        view.setVisible(true);
        view.selectTab(1);
        if (currModel == null) {
            // ���������� ����������� �� ���������
            currModel = new SettingsViewModel();
            currModel.setPortion(100L);
            currModel.getRatings().addAll(Arrays.asList(1, 2, 3));
        }
        modelDataToView();
    }

    public void reloadSettings(final CallBackChain<UserSettingsModel> callBack) {

    }

    public void showSettingsView() {
        currModel = configService.loadSettingsConfig();
        modelDataToView();
        view.setVisible(true);
        view.setState(Frame.NORMAL);
    }

    /**
     * �� ������ ��������� ��� ������������������
     *
     *
     */
    public void onSaveOrRegister() {
        viewDataToModel();
        // validation
        validator.validateLogin(currModel.getLogin());
        validator.validateRatings(currModel.getRatings());

        switch (state) {
            case FIRST_INPUT:
                try {
                    view.setVisible(false);

                    final UserSettingsModel param = currModel;
                    currModel = null;
                    // ��� ����������� ����� ������ �� ����������,
                    // ����� �������� ���������� ������ ���� ��������� ���������
                    final CountDownLatch signal = new CountDownLatch(1);

                    if (!signal.await(5, TimeUnit.MINUTES)) {
                        throw new BimException("Illegal state of synchronously invocation, CountDownLatch" +
                                " waits more then 5 minutes");
                    } else if (currModel == null) {
                        return;
                    }
                } catch (Exception e) {
                    callBack.exception(e);
                    return;
                }
                break;
            case REGISTERED:
                gaeStoreSettings();
                break;
        }

        configService.saveSettingsConfig(currModel);
        modelDataToView();

        switch (state) {
            case FIRST_INPUT:
                callBack.run(currModel);      // ������ ������� ����������, ������ ������� ����� ������� ������
                callBack = null;
                view.prepareSettingsView();
                state = State.REGISTERED;
                break;
            case REGISTERED:
                GuiUtil.showInfoMessage("Settings saved successfully");
                view.setVisible(false);
                break;
        }
    }

    private void gaeStoreSettings() {// additional properties
        LocalCache mainCache = PaineFactory.getMainViewController().getLocalCache();
        //model.setCacheMaxSize();
        currModel.setCurrentNum(mainCache.getCurrentNum().longValue());
        //model.setFacedLangId(Const.DEFAULT_LANG_ID.longValue());
        //model.setId();
        //model.setLogin(loginText.getText());
        //model.setOpacity();
        //model.setPassword(GuiUtil.createDigest(passwordText.getPassword()));
        //model.setPortion(Long.valueOf(portionText.getText()));
        //model.setPreviewDuration();
        //model.setPreviewInterval();
        currModel.setRecordsCount(mainCache.getRecordsCount().longValue());
        //model.setShadowedLangId(Const.);
        //model.setSortingDirection(Const.DEFAULT_SORTING_DIRECTION);
        //model.setSortingField(Const.DEFAULT_SORTING_FIELD);
        //model.getRatings().addAll(ratingsPanel.getRatings());


    }


    public void saveConfig() {
        if (currModel == null) {
            log.info("Settings is null, will not be saved to config file");
        } else {
            gaeStoreSettings();
            configService.saveSettingsConfig(currModel);
        }
    }

    public void onCancel() {
        if (state == State.FIRST_INPUT) {
            StartApp.stop(0);
        } else {
            view.setVisible(false);
        }
    }




    public SettingsViewValidator getValidator() {
        return validator;
    }

    public void selectTab(int tabNum) {
        view.selectTab(tabNum);
    }

}
