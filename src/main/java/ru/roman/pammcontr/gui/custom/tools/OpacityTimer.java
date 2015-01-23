package ru.roman.pammcontr.gui.custom.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** @author Roman 22.12.12 0:52 */
public class OpacityTimer extends Timer {
    private static final Log log = LogFactory.getLog(OpacityTimer.class);

    private static final float START_OPACITY = 0;
    private static final float OPACITY_STEP = 0.05f;

    private float opacity;
    private float step;
    private final Window pane;
    private final float finalOpacity;


    public OpacityTimer(final Window frame, float finalOpacity) {
        super(100, null);
        this.pane = frame;
        this.finalOpacity = finalOpacity;
        setRepeats(true);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pane.isVisible()) {
                    opacity = opacity + step;
                    if (opacity > finalOpacity || opacity < START_OPACITY) {
                        stop();
                    }
                    opacity = opacity > finalOpacity ? finalOpacity : opacity;
                    opacity = opacity < START_OPACITY ? START_OPACITY : opacity;
                    pane.setOpacity(opacity);
                } else {
                    pane.setOpacity(opacity);
                    pane.setVisible(true);

                    pane.setAlwaysOnTop(true);
                    pane.toFront();
                }
                //log.info("OpacityTimer opacity : " + opacity);
            }
        });
    }

    public void showSlowly() {
        step = OPACITY_STEP;
        restart();
    }

    public void hideSlowly() {
        step = -OPACITY_STEP;
        restart();
    }

    public void showQuickly() {
        step = finalOpacity;
        restart();
    }

    public void hideQuickly() {
        step = -finalOpacity;
        restart();
    }
}
