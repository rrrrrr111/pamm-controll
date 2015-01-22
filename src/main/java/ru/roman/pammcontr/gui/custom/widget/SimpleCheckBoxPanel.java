package ru.roman.pammcontr.gui.custom.widget;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** @author Roman 22.12.12 12:58 */
public class SimpleCheckBoxPanel extends JPanel {
    private static final Log log = LogFactory.getLog(SimpleCheckBoxPanel.class);

    private final JCheckBox checkBox1 = new JCheckBox();
    private final JCheckBox checkBox2 = new JCheckBox();
    private final JCheckBox checkBox3 = new JCheckBox();
    private final JCheckBox checkBox4 = new JCheckBox();
    private final JCheckBox checkBox5 = new JCheckBox();
    private final List<JCheckBox> checkBoxList = new LinkedList<JCheckBox>();


    public SimpleCheckBoxPanel() {
        super();
        createView();
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

    }


    public void setRatings(Integer... ratings) {
        setRatings(Arrays.asList(ratings));
    }

    public void setRatings(List<Integer> ratings) {
        for (JCheckBox box : checkBoxList) {
            box.setSelected(false);
        }
        for (Integer rating : ratings) {
            checkBoxList.get(rating - 1).setSelected(true);
        }
    }

    public List<Integer> getRatings() {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < checkBoxList.size(); i++) {
            JCheckBox box = checkBoxList.get(i);
            if (box.isSelected()) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
