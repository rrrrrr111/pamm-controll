package ru.roman.pammcontr.gui.custom.widget;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/** @author Roman 10.03.13 17:32 */
public class LoadingPanel extends JPanel {

    //private final Image loadingImage = GuiUtil.createLoadingImage();
    private final JProgressBar progressBar = new JProgressBar();

    public LoadingPanel() {
        super(new CardLayout());
        //setBorder(BorderFactory.createEtchedBorder());
        progressBar.setIndeterminate(true);
        add(progressBar);
        add(new JLabel(""));
        //setVisible(false);
        //activateLoading();
        stopLoading();
    }


    private final static List<LoadingPanel> commonInstances = new LinkedList<LoadingPanel>();

    public static synchronized LoadingPanel createSharedInstance() {
        final LoadingPanel inst = new LoadingPanel();
        commonInstances.add(inst);
        return inst;
    }

    public static void activateSharedLoading() {
        for (LoadingPanel instance : commonInstances) {
            instance.activateLoading();
        }
    }

    public static void stopSharedLoading() {
        for (LoadingPanel instance : commonInstances) {
            instance.stopLoading();
        }
    }

    private void activateLoading() {
        //setVisible(true);
        ((CardLayout)getLayout()).first(this);
    }

    private void stopLoading() {
        //setVisible(false);
        ((CardLayout)getLayout()).last(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(loadingImage, 0, 0, null); // see javadoc for more info on the parameters
    }
}
