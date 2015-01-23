package ru.roman.pammcontr.service.ghost;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.pane.settings.Settings;
import ru.roman.pammcontr.util.AppException;
import ru.roman.pammcontr.util.Const;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** @author Roman 10.01.13 23:38 */
public class GhostServiceImpl implements GhostService {
    private static final Log log = LogFactory.getLog(GhostServiceImpl.class);
    public static final double MILLISEC_IN_MIN = 60000;

    public Integer mainInterval;
    public Integer thirstDelay;
    public Integer showInterval;

    private final GhostController controller;

    private Timer mainTicker;
    private Timer hideTicker;
    private Timer unfreezeTicker;


    public GhostServiceImpl(GhostController ctrl) {
        this.controller = ctrl;
        mainInterval = minutesToMilliSec(Settings.get().getCheckFastPammInterval());
        thirstDelay = (Const.DEV_MODE ? 1000 : mainInterval);
        showInterval = minutesToMilliSec(Settings.get().getPreviewDuration());

        mainTicker = new Timer(mainInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onShow();
                hideTicker.restart();
            }
        });
        mainTicker.setCoalesce(true);
        mainTicker.setRepeats(true);

        hideTicker = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onHide();
            }
        });
        hideTicker.setCoalesce(true);
        hideTicker.setRepeats(false);

        unfreezeTicker = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onHide();
                start();
            }
        });
        unfreezeTicker.setCoalesce(true);
        unfreezeTicker.setRepeats(false);

        loadTimers();
        log.info(String.format("Timers initialised, main interval %s ms,  preview duration : %s ms, first delay : %s ms",
                mainInterval, showInterval, thirstDelay));
    }

    private void loadTimers() {
        if (mainInterval < 60000 && !Const.DEV_MODE) {
            throw new AppException("Main interval can't be less then 1 minute");
        } else if (showInterval < 1000) {
            throw new AppException("Preview duration can't be less then 1 second");
        }
        mainTicker.setInitialDelay(thirstDelay);
        mainTicker.setDelay(mainInterval);
        hideTicker.setInitialDelay(showInterval);
        unfreezeTicker.setInitialDelay(showInterval);
    }



    public void start() {
        //log.info("Start main ticker");
        mainTicker.restart();
    }

    public void startFromOpened() {
        //log.info("Start main ticker from opened");
        unfreezeTicker.restart();
    }

    public void stop() {
        //log.info("Stop all tickers");
        mainTicker.stop();
        hideTicker.stop();
        unfreezeTicker.stop();
    }


    public static int minutesToMilliSec(Double inMin) {
        if (inMin == null || (inMin > (Integer.MAX_VALUE / MILLISEC_IN_MIN)) || inMin < 0.05d) {
            throw new AppException("Wrong value for conversion Min to Milliseconds : " + inMin);
        }
        return (int)(inMin * MILLISEC_IN_MIN);
    }

}
