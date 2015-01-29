package ru.roman.pammcontr.gui.pane.main;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.common.cbchain.CallBackChain;
import ru.roman.pammcontr.gui.common.grid.ListTableModel;
import ru.roman.pammcontr.gui.common.mvc.Controller;
import ru.roman.pammcontr.gui.custom.tools.OpacityTimer;
import ru.roman.pammcontr.gui.custom.widget.LoadingPanel;
import ru.roman.pammcontr.gui.custom.widget.TransparentWindowSupport;
import ru.roman.pammcontr.gui.pane.settings.Settings;
import ru.roman.pammcontr.gui.pane.tray.TrayUtils;
import ru.roman.pammcontr.model.PammInfo;
import ru.roman.pammcontr.service.ServiceFactory;

import ru.roman.pammcontr.service.fastpamm.FastPammService;
import ru.roman.pammcontr.service.ghost.GhostController;
import ru.roman.pammcontr.service.ghost.GhostService;
import ru.roman.pammcontr.service.ghost.GhostServiceImpl;


import java.awt.*;
import java.util.List;
import java.util.Objects;

/** @author Roman 21.12.12 0:24 */
public class MainViewController extends Controller<MainView, MainViewModel> implements GhostController {
    private static FastDateFormat UPDATE_TIME_FORMAT = FastDateFormat.getInstance("hh:mm dd.MM.yy");
    private static final Log log = LogFactory.getLog(MainViewController.class);

    private final OpacityTimer opacityTimer;
    private final GhostService ghostService;
    private final FastPammService fastPammService = ServiceFactory.getFastPammService();

    private final TransparentWindowSupport supp = new TransparentWindowSupport();

    private volatile State state;


    public MainViewController(MainView view) {
        super(view);
        opacityTimer = new OpacityTimer(view, Settings.get().getOpacity());
        ghostService = new GhostServiceImpl(this);
        currModel = new MainViewModel();
    }

    public void onInit() {
        state = State.SCHEDULED;
        TrayUtils.addTrayIcon();

        final ListTableModel<Object> tm = currModel.getTableModel();

        tm.setColumnInfo(new String[]{"Name/Num", "p/l", "ignore"});
        for (PammInfo pi : Settings.get().getPammInfoList()) {
            tm.getData().add(new Object []{pi.getName() + "/" + pi.getNum(),
                    pi.getProfitLossPercent(),
                    pi.isFlagToIgnoreDropDown()
            });
        }
        currModel.setBorderInfo("Last check at " + (Settings.get().getLastCheckDate() == null ? "..." : UPDATE_TIME_FORMAT.format(Settings.get().getLastCheckDate())));
        currModel.setInfoText("");

        view.fillWidgets(currModel);
        ghostService.start();
    }



    protected void onCheckPamm(CallBackChain<MainViewModel> nextCallBack) {

        LoadingPanel.activateSharedLoading();

        final ListTableModel<Object> tm = currModel.getTableModel();
        final List<String[]> pammList = fastPammService.getPammList();
        if (pammList.isEmpty()) {
            TrayUtils.showTrayNotification("FastPamm service not available", TrayIcon.MessageType.ERROR);
            LoadingPanel.stopSharedLoading();
            return;
        }

        for (String[] pArr: pammList) {
            for (PammInfo pi : Settings.get().getPammInfoList()) {

            }
        }

        LoadingPanel.stopSharedLoading();
        if (nextCallBack != null) {
            nextCallBack.run(currModel);
        }
    }

    public void onShow() {
        onCheckPamm(new CallBackChain<MainViewModel>() {
            @Override
            protected void onSuccess(MainViewModel result) {
                opacityTimer.showSlowly();
            }
        });
    }

    public void onHide() {
        opacityTimer.hideSlowly();
    }

    public void hideQuickly() {
        opacityTimer.hideQuickly();
    }

    public void showQuickly() {
        opacityTimer.showQuickly();
        supp.setVisible(true);
    }

    public synchronized void changeState(State state) {
        switch (state) {
            case DISABLED:
                opacityTimer.hideQuickly();
                ghostService.stop();
                break;
            case SCHEDULED:
                ghostService.start();
                break;
        }
        log.info("Changing main state to " + state);
        this.state = state;
    }

    public void startGhostFromOpened() {
        switch (state) {
            case SCHEDULED:
                ghostService.startFromOpened();
                break;
            case DISABLED:
                opacityTimer.hideSlowly();
                break;
        }
        supp.setVisible(false);
    }

    public void stopGhost() {
        switch (state) {
            case SCHEDULED:
                ghostService.stop();
                break;
        }
    }

    public State getState() {
        return state;
    }
}