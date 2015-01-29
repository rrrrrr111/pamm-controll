package ru.roman.pammcontr.gui.pane.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.common.mvc.View;
import ru.roman.pammcontr.gui.custom.widget.LoadingPanel;
import ru.roman.pammcontr.gui.pane.tray.TrayUtils;
import ru.roman.pammcontr.util.Const;
import ru.roman.pammcontr.util.GuiUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/** @author Roman 18.12.12 0:02 */
public class MainView extends JWindow implements View<MainViewModel, MainView, MainViewController> {
    private static final Log log = LogFactory.getLog(MainView.class);

    private final MainViewController controller = new MainViewController(this);

    private TitledBorder titledEmptyBorder;
    private final JTable mainTable = new JTable();
    private final JScrollPane mainScroll = new JScrollPane(mainTable,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private final JButton prevButton = new JButton("....");
    private final JButton nextButton = new JButton("....");
    private final JButton translateButton = new JButton("check");
    private final JButton editButton = new JButton("....");
    private final JButton hideButton = new JButton("hide");

    private final JLabel infoLabel = new JLabel();

    private Point mouseDownScreenCoords;
    private Point mouseDownCompCoords;


    public MainView() {

        createView();
        controller.onInit();
        controller.getModel().getTableModel().fireTableStructureChanged();
    }



    private void createView() {

        final JPanel panel = new JPanel(new GridBagLayout());
        setLocationRelativeTo(null);
        setBackground(new Color(0.5294118f, 0f, 1.0f, 0.0f));
        add(panel);
        setPreferredSize(new Dimension(270, 140));
        setAlwaysOnTop(true);
        setFocusable(true);
        setAutoRequestFocus(true);

        // border and grid
        titledEmptyBorder = BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "...",
                TitledBorder.RIGHT, TitledBorder.DEFAULT_POSITION);
        //mainTable.setBorder(titledEmptyBorder);
        //mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mainTable.setModel(controller.getModel().getTableModel());
        mainTable.setCellSelectionEnabled(true);

        mainScroll.setBorder(titledEmptyBorder);
        mainScroll.getVerticalScrollBar().setUnitIncrement(7);

        final GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.gridwidth = 5;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.weighty = 1.0;
        gbc1.weightx = 1.0;
        panel.add(mainScroll, gbc1);

        // buttons line
        final GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        panel.add(prevButton, gbc2);
        prevButton.setEnabled(false);

        final GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 1;
        gbc3.gridy = 1;
        panel.add(nextButton, gbc3);
        nextButton.setEnabled(false);

        final GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.fill = GridBagConstraints.HORIZONTAL;
        gbc4.gridx = 2;
        gbc4.gridy = 1;
        panel.add(translateButton, gbc4);

        final GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.fill = GridBagConstraints.HORIZONTAL;
        gbc5.gridx = 3;
        gbc5.gridy = 1;
        panel.add(editButton, gbc5);
        editButton.setEnabled(false);

        final GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.fill = GridBagConstraints.HORIZONTAL;
        gbc6.gridx = 4;
        gbc6.gridy = 1;
        panel.add(hideButton, gbc6);

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException();
            }
        });
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCheckPamm(null);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException();
            }
        });
        hideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hideQuickly();
            }
        });

        // info label and shared loading panel
        GridBagConstraints gbc8 = new GridBagConstraints();
        gbc8.fill = GridBagConstraints.HORIZONTAL;
        gbc8.anchor = GridBagConstraints.LAST_LINE_START;
        gbc8.gridwidth = 4;
        gbc8.gridx = 0;
        gbc8.gridy = 2;
        gbc8.weighty = 0.0;
        gbc8.weightx = 1.0;
        gbc8.insets = new Insets(0,5,0,5);
        panel.add(infoLabel, gbc8);

        gbc8 = new GridBagConstraints();
        gbc8.fill = GridBagConstraints.HORIZONTAL;
        gbc8.gridx = 4;
        gbc8.gridy = 2;
        gbc8.weighty = 0.0;
        gbc8.weightx = 0.0;
        gbc8.insets = new Insets(0,10,0,10);
        panel.add(LoadingPanel.createSharedInstance(), gbc8);


        // special listeners
        final Timer mouseExitedTicker = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startGhostFromOpened();
            }
        });
        mouseExitedTicker.setCoalesce(true);
        mouseExitedTicker.setRepeats(false);
        final MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                controller.stopGhost();
                controller.showQuickly();
                mouseExitedTicker.stop();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseExitedTicker.restart();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseDownScreenCoords = null;
                mouseDownCompCoords = null;
            }
            @Override
            public void mousePressed(MouseEvent e) {
                mouseDownScreenCoords = e.getLocationOnScreen();
                mouseDownCompCoords = e.getPoint();
            }
        };
        GuiUtil.addMouseListenerToChilds(panel, mouseAdapter);

        final MouseMotionListener mouseMotionAdapter = new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                MainView.this.setLocation(
                        mouseDownScreenCoords.x + (currCoords.x - mouseDownScreenCoords.x) - mouseDownCompCoords.x,
                        mouseDownScreenCoords.y + (currCoords.y - mouseDownScreenCoords.y) - mouseDownCompCoords.y
                );
            }
        };
        panel.addMouseMotionListener(mouseMotionAdapter);
        mainScroll.addMouseMotionListener(mouseMotionAdapter);
        //mainTable.addMouseMotionListener(mouseMotionAdapter);
        infoLabel.addMouseMotionListener(mouseMotionAdapter);

        pack();
        Point pos = GuiUtil.getRightCornerPosition(getSize(), 3);
        setLocation(pos);

    }

    @Override
    public void fillWidgets(MainViewModel model) {
        infoLabel.setText(model.getInfoText());
        infoLabel.setToolTipText(model.getInfoText());
        titledEmptyBorder.setTitle(model.getBorderInfo());
    }

    @Override
    public void fillModel(MainViewModel currModel) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public MainViewController getController() {
        return controller;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        TrayUtils.removeTrayIcon();
        log.warn(Const.APP_NAME_WITH_VERSION + " crash");
    }
}
