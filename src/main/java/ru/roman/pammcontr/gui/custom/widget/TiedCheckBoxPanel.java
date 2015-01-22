package ru.roman.pammcontr.gui.custom.widget;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

/** @author Roman 22.12.12 12:58 */
public class TiedCheckBoxPanel extends JPanel {
    private static final Log log = LogFactory.getLog(TiedCheckBoxPanel.class);

    private final SilentJCheckBox checkBox1 = new SilentJCheckBox();
    private final SilentJCheckBox checkBox2 = new SilentJCheckBox();
    private final SilentJCheckBox checkBox3 = new SilentJCheckBox();
    private final SilentJCheckBox checkBox4 = new SilentJCheckBox();
    private final SilentJCheckBox checkBox5 = new SilentJCheckBox();
    private final List<SilentJCheckBox> checkBoxList = new LinkedList<SilentJCheckBox>();

    private OnChangeCallBack callBack;

    public TiedCheckBoxPanel(OnChangeCallBack callBack) {
        this.callBack = callBack;
        createView();
    }

    public TiedCheckBoxPanel() {
        this(null);
    }

    private void createView() {
        //checkPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(checkBox1);
        add(checkBox2);
        add(checkBox3);
        add(checkBox4);
        add(checkBox5);
        checkBoxList.add(checkBox1);
        checkBoxList.add(checkBox2);
        checkBoxList.add(checkBox3);
        checkBoxList.add(checkBox4);
        checkBoxList.add(checkBox5);

        final ItemListener cl = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.SELECTED :
                    case ItemEvent.DESELECTED :
                        final int idx = checkBoxList.indexOf(e.getSource());
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            upTo(idx);
                        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                            if (idx + 1 == checkBoxList.size()) {
                                checkBoxList.get(checkBoxList.size() - 1).setSelectedSilent(false);
                            } else if (checkBoxList.get(idx + 1).isSelected()) {
                                upTo(idx);
                            } else {
                                checkBoxList.get(idx).setSelectedSilent(false);
                            }
                        }
                        //log.info("rating changed to " + rating);
                        if (callBack != null) {
                            callBack.OnChange(getRating());
                        }
                }
            }
        };
        activateListener(cl);

    }


    private void activateListener(ItemListener l) {
        for (JCheckBox cb : checkBoxList) {
            cb.addItemListener(l);
        }
    }

    private void upTo(int idx) {
        for (int i = 0; i < checkBoxList.size(); i++) {
            if (i <= idx) {
                checkBoxList.get(i).setSelectedSilent(true);
            } else {
                checkBoxList.get(i).setSelectedSilent(false);
            }
        }
    }

    public void setRating(int rating) {
        upTo(rating - 1);
    }

    public int getRating() {
        for (int i = 0; i < checkBoxList.size(); i++) {
            if (!checkBoxList.get(i).isSelected()) {
                return i;
            }
        }
        return 5;
    }

    public interface OnChangeCallBack {
        void OnChange(int rating);
    }
}
