package ru.roman.pammcontr.gui.pane.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.common.mvc.View;
import ru.roman.pammcontr.gui.custom.widget.LoadingPanel;
import ru.roman.pammcontr.gui.custom.widget.TiedCheckBoxPanel;
import ru.roman.pammcontr.gui.pane.tray.TrayUtils;
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
    private final JLabel textLabel = new JLabel();
    private final JScrollPane textScroll = new JScrollPane(textLabel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private final JButton prevButton = new JButton("pr");
    private final JButton nextButton = new JButton("nx");
    private final JButton translateButton = new JButton("translate");
    private final JButton editButton = new JButton("edit");
    private final JButton hideButton = new JButton("hide");

    private TiedCheckBoxPanel checkPanel;
    private final JLabel typeLabel = new JLabel();

    private Point mouseDownScreenCoords;
    private Point mouseDownCompCoords;


    public MainView() {

        createView();
        controller.onInit();
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

        // �����
        titledEmptyBorder = BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "XX-XX",
                TitledBorder.RIGHT, TitledBorder.DEFAULT_POSITION);
        textLabel.setBorder(titledEmptyBorder);


        textScroll.setBorder(BorderFactory.createEmptyBorder());
        textScroll.getVerticalScrollBar().setUnitIncrement(7);

        final GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
        //gbc1.anchor = GridBagConstraints.PAGE_START;  // �������� � ���� ����������
        gbc1.gridwidth = 5;                         // ���-�� ����� ����������� �� ������
        gbc1.weighty = 1.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
        gbc1.weightx = 1.0;
        gbc1.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
        gbc1.gridy = 0;
        //gbc1.ipady = 140;                          // ����������� ������������ �������
        //gbc1.ipadx = 270;                          // ����������� ������������ �������
        panel.add(textScroll, gbc1);

        // ������
        final GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.weighty = 0.0;
        panel.add(prevButton, gbc2);

        final GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 1;
        gbc3.gridy = 1;
        gbc3.weighty = 0.0;
        panel.add(nextButton, gbc3);

        final GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.fill = GridBagConstraints.HORIZONTAL;
        gbc4.gridx = 2;
        gbc4.gridy = 1;
        gbc4.weighty = 0.0;
        panel.add(translateButton, gbc4);

        final GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.fill = GridBagConstraints.HORIZONTAL;
        gbc5.gridx = 3;
        gbc5.gridy = 1;
        gbc5.weighty = 0.0;
        panel.add(editButton, gbc5);

        final GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.fill = GridBagConstraints.HORIZONTAL;
        gbc6.gridx = 4;
        gbc6.gridy = 1;
        gbc6.weighty = 0.0;
        panel.add(hideButton, gbc6);

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onPrev();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onNext(null);
            }
        });
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onTranslate();
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


        // ��������
        checkPanel = new TiedCheckBoxPanel(new TiedCheckBoxPanel.OnChangeCallBack() {
            @Override
            public void OnChange(int rating) {
                controller.onRatingChange(rating);
            }
        });
        final GridBagConstraints gbc7 = new GridBagConstraints();
        gbc7.fill = GridBagConstraints.NONE;
        gbc7.anchor = GridBagConstraints.LAST_LINE_START;
        gbc7.gridwidth = 3;
        //gbc7.insets = new Insets(0,0,0,10);  // padding
        //gbc7.weighty = 0.1;
        gbc7.gridx = 0;
        gbc7.gridy = 2;
        gbc7.weighty = 0.0;
        panel.add(checkPanel, gbc7);


        GridBagConstraints gbc8 = new GridBagConstraints();
        gbc8.fill = GridBagConstraints.HORIZONTAL;
        gbc8.gridwidth = 1;
        gbc8.gridx = 3;
        gbc8.gridy = 2;
        gbc8.weighty = 0.0;
        gbc8.weightx = 1.0;
        panel.add(typeLabel, gbc8);

        gbc8 = new GridBagConstraints();
        gbc8.fill = GridBagConstraints.HORIZONTAL;
        gbc8.gridwidth = 1;
        gbc8.gridx = 4;
        gbc8.gridy = 2;
        gbc8.weighty = 0.0;
        gbc8.weightx = 0.0;
        gbc8.insets = new Insets(0,10,0,10);  // padding
        panel.add(LoadingPanel.createSharedInstance(), gbc8);


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
        textScroll.addMouseMotionListener(mouseMotionAdapter);
        textLabel.addMouseMotionListener(mouseMotionAdapter);

        pack();
        Point pos = GuiUtil.getRightCornerPosition(getSize(), 3);
        setLocation(pos);


    }

    public void setRating(Integer rating) {
        checkPanel.setRating(rating);
    }

    @Override
    public void fillWidgets(MainViewModel model) {
        setText(model.getTextFaced());
        typeLabel.setText("заглушка");
        setRating(model.getRating().intValue());

        titledEmptyBorder.setTitle(String.format("%s", "заглушка"));
    }

    @Override
    public void fillModel(MainViewModel currModel) {
        throw new RuntimeException("not implemented");
    }

    private void setText(String str) {
        // border: 4px double black;
        //
        String length = "250";
        textLabel.setText(String.format("<html>" +
                "<div width='" + length + "' align='center' style='color:blue;font:10px;'>%s</div>" +
                "</html>", str));
        //textLabel.setText(String.format("%s", str));
    }

    private boolean translatedState;

    public void translate() {
        if (translatedState) {
            setText(controller.getModel().getTextFaced());
        } else {
            setText(controller.getModel().getTextShadowed());
        }
        translatedState = !translatedState;
    }

    @Override
    public MainViewController getController() {
        return controller;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        TrayUtils.removeTrayIcon();
        log.warn("Bim crash");
    }
}
