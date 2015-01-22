package ru.roman.pammcontr.gui.pane.settings;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.common.mvc.View;
import ru.roman.pammcontr.gui.common.validator.BimValidationException;
import ru.roman.pammcontr.gui.custom.widget.SimpleCheckBoxPanel;

import ru.roman.pammcontr.util.Const;
import ru.roman.pammcontr.util.GuiUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** @author Roman 16.01.13 23:58 */
public class SettingsView extends JFrame implements View<SettingsViewModel, SettingsView, SettingsViewController> {
    private static final Log log = LogFactory.getLog(SettingsView.class);

    private final SettingsViewController controller = new SettingsViewController(this);

    private JTabbedPane tabbedPane;
    private JPanel genericTab;
    private JPanel prevSettTab;
    private JScrollPane toolsTabScroll;
    private JPanel toolsTab;

    private JTextField loginText;
    private JPasswordField passwordText;

    private SimpleCheckBoxPanel ratingsPanel;
    private JTextField portionText;
    private JButton saveButton;
    private JButton cancelButton;
    private static final String PASSWORD_STUB = "password";

    public SettingsView() {

        createView();
        controller.onInit();
    }

    private void createView() {

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        final Dimension preferredSize = new Dimension(400, 350);
        setPreferredSize(preferredSize);
        setResizable(true);
        setIconImage(GuiUtil.createMainImage());
        setTitle(Const.APP_NAME + " settings");
        setLocation(GuiUtil.getCenterPosition(preferredSize));

        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(preferredSize);
        final Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(tabbedPane);

        genericTab = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Generic", genericTab);

        prevSettTab = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Preview settings", prevSettTab);

        toolsTab = new JPanel(new GridBagLayout());
        toolsTabScroll = new JScrollPane(toolsTab);
        tabbedPane.addTab("Tools", toolsTabScroll);

//        final JPanel hzTab = new JPanel();
//        tabbedPane.addTab("hz", hzTab);


        // common variables
        final int leftAndRightMargin = 60;
        final int behindLabelAndWidgetMargin = 10;
        final int upAndUnderMargin = 20;
        GridBagConstraints gbc;

        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////// Authorization ///////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////
        {
            final JPanel authPanel = new JPanel(new GridBagLayout());
            authPanel.setBorder(BorderFactory.createTitledBorder("Authorization"));

            //loginText.setMaximumSize(new Dimension(160, 0));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.NORTH;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 0;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, 0, 0, 0);
            genericTab.add(authPanel, gbc);

            final JLabel fillYourCredLabel = new JLabel("Fill your credentials:");
            //fillYourCredLabel.setBorder(BorderFactory.createBevelBorder(1));
            //fillYourCredLabel.setPreferredSize(new Dimension(260, 0));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 2;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 0;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(upAndUnderMargin, leftAndRightMargin, 5, 0);
            authPanel.add(fillYourCredLabel, gbc);

            JLabel loginLabel = new JLabel("login");
            loginLabel.setHorizontalAlignment(JLabel.RIGHT);
            loginLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 1;
            //gbc11.ipady = 140;                          // ����������� ������������ �������
            //gbc11.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, leftAndRightMargin, 0, behindLabelAndWidgetMargin);
            authPanel.add(loginLabel, gbc);

            loginText = new JTextField();
            //loginText.setPreferredSize(new Dimension(120, 0));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.5;
            gbc.gridx = 1;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 1;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, 0, 0, leftAndRightMargin);
            authPanel.add(loginText, gbc);

            JLabel passwordLabel = new JLabel("password");
            passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
            passwordLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 2;
            //gbc21.ipady = 140;                          // ����������� ������������ �������
            //gbc21.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, leftAndRightMargin, upAndUnderMargin, behindLabelAndWidgetMargin);
            authPanel.add(passwordLabel, gbc);

            passwordText = new JPasswordField();
            //passwordText.setPreferredSize(new Dimension(160, 0));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.5;
            gbc.gridx = 1;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 2;
            //gbc2.ipady = 140;                          // ����������� ������������ �������
            //gbc2.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, 0, upAndUnderMargin, leftAndRightMargin);
            authPanel.add(passwordText, gbc);
        }

        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////// Generic settings ////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////
        {
            final JPanel genSettPanel = new JPanel(new GridBagLayout());
            genSettPanel.setBorder(BorderFactory.createTitledBorder("Generic settings"));
            //loginText.setMaximumSize(new Dimension(160, 0));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 1;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, 0, 0, 0);
            genericTab.add(genSettPanel, gbc);

            ratingsPanel = new SimpleCheckBoxPanel();
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 1;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 0;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(upAndUnderMargin, 0, 0, leftAndRightMargin);
            genSettPanel.add(ratingsPanel, gbc);

            JLabel ratingsLabel = new JLabel("displayed ratings");
            ratingsLabel.setHorizontalAlignment(JLabel.RIGHT);
            ratingsLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 0;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(upAndUnderMargin, leftAndRightMargin, 0, behindLabelAndWidgetMargin);
            genSettPanel.add(ratingsLabel, gbc);

            portionText = new JTextField();
            portionText.setPreferredSize(new Dimension(10, 0));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 1;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 1;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, 0, upAndUnderMargin, leftAndRightMargin);
            genSettPanel.add(portionText, gbc);

            JLabel portionLabel = new JLabel("portion");
            portionLabel.setHorizontalAlignment(JLabel.RIGHT);
            portionLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 1;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, leftAndRightMargin, upAndUnderMargin, behindLabelAndWidgetMargin);
            genSettPanel.add(portionLabel, gbc);

        }

        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////// Tools tab ///////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////

        toolsTabScroll.getVerticalScrollBar().setUnitIncrement(10);
        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////// Excel word list loading /////////////////////////
        ///////////////////////////////////////////////////////////////////////////////
        {
            final JPanel loadWordListPanel = new JPanel(new GridBagLayout());
            loadWordListPanel.setBorder(BorderFactory.createTitledBorder("Excel word list uploading"));
            //loginText.setMaximumSize(new Dimension(160, 0));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 1;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, 0, 0, 0);
            toolsTab.add(loadWordListPanel, gbc);
            {
                JLabel broseWordListLabel = new JLabel("Select a file to upload");
                broseWordListLabel.setHorizontalAlignment(JLabel.RIGHT);
                broseWordListLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
                gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
                gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
                gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
                gbc.weightx = 0.0;
                gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
                gbc.gridy = 0;
                //gbc11.ipady = 140;                          // ����������� ������������ �������
                //gbc11.ipadx = 270;                          // ����������� ������������ �������
                gbc.insets = new Insets(upAndUnderMargin, leftAndRightMargin, upAndUnderMargin, behindLabelAndWidgetMargin);
                loadWordListPanel.add(broseWordListLabel, gbc);

                final JButton broseWordListButton = new JButton();
                broseWordListButton.setPreferredSize(new Dimension(100, 0));
                broseWordListButton.setAction(new AbstractAction() {//������ ����� ������ ����� ���������� ������ ������
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        throw new UnsupportedOperationException();
                    }
                });
                broseWordListButton.setText("brose...");
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
                gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
                gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
                gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
                gbc.weightx = 0.0;
                gbc.gridx = 1;                             // gridx �  gridy ���������� ���� �������� ���������
                gbc.gridy = 0;
                //gbc.ipady = 140;                          // ����������� ������������ �������
                //gbc.ipadx = 270;                          // ����������� ������������ �������
                gbc.insets = new Insets(upAndUnderMargin, 0, upAndUnderMargin, leftAndRightMargin);
                loadWordListPanel.add(broseWordListButton, gbc);
            }
        }
        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////// Subtitles merge ///////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////

        {
            final JPanel subtitlesMergePanel = new JPanel(new GridBagLayout());
            subtitlesMergePanel.setBorder(BorderFactory.createTitledBorder("Subtitles merge"));
            //loginText.setMaximumSize(new Dimension(160, 0));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 2;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, 0, 0, 0);
            toolsTab.add(subtitlesMergePanel, gbc);
            {
                // button label
                final JLabel subtitlesMergeLabel = new JLabel("Select files to merge");
                subtitlesMergeLabel.setHorizontalAlignment(JLabel.RIGHT);
                subtitlesMergeLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
                gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
                gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
                gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
                gbc.weightx = 0.0;
                gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
                gbc.gridy = 0;
                //gbc11.ipady = 140;                          // ����������� ������������ �������
                //gbc11.ipadx = 270;                          // ����������� ������������ �������
                gbc.insets = new Insets(upAndUnderMargin, leftAndRightMargin, 0, behindLabelAndWidgetMargin);
                subtitlesMergePanel.add(subtitlesMergeLabel, gbc);

                // button brose...
                final JButton subtitlesMergeButton = new JButton();
                subtitlesMergeButton.setPreferredSize(new Dimension(100, 0));
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
                gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
                gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
                gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
                gbc.weightx = 0.0;
                gbc.gridx = 1;                             // gridx �  gridy ���������� ���� �������� ���������
                gbc.gridy = 0;
                //gbc.ipady = 140;                          // ����������� ������������ �������
                //gbc.ipadx = 270;                          // ����������� ������������ �������
                gbc.insets = new Insets(upAndUnderMargin, 0, 0, leftAndRightMargin);
                subtitlesMergePanel.add(subtitlesMergeButton, gbc);

                // format list label
                final JLabel label = new JLabel("Select output formats");
                label.setHorizontalAlignment(JLabel.RIGHT);
                label.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
                gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
                gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
                gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
                gbc.weightx = 0.0;
                gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
                gbc.gridy = 1;
                //gbc11.ipady = 140;                          // ����������� ������������ �������
                //gbc11.ipadx = 270;                          // ����������� ������������ �������
                gbc.insets = new Insets(0, leftAndRightMargin, upAndUnderMargin, behindLabelAndWidgetMargin);
                subtitlesMergePanel.add(label, gbc);

                // format list
                final JList<String> list = new JList<String>();
                //list.setPreferredSize(new Dimension(80, 28));
                list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                list.setLayoutOrientation(JList.VERTICAL);
                DefaultListModel listModel = new DefaultListModel();
                list.setModel(listModel);
                final JScrollPane listScroll = new JScrollPane(list);
                listScroll.setPreferredSize(new Dimension(80, 8));
                listScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
                //listScroll.setAutoscrolls(true);
                listScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
                gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
                gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
                gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
                gbc.weightx = 0.0;
                gbc.gridx = 1;                             // gridx �  gridy ���������� ���� �������� ���������
                gbc.gridy = 1;
                gbc.ipady = 28;                          // ����������� ������������ �������
                //gbc.ipadx = 270;                          // ����������� ������������ �������
                gbc.insets = new Insets(0, 0, upAndUnderMargin, leftAndRightMargin);
                subtitlesMergePanel.add(listScroll, gbc);

                // listeners
                subtitlesMergeButton.setAction(new AbstractAction() {//������ ����� ������ ����� ���������� ������ ������
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        throw new UnsupportedOperationException();
                    }
                });
                subtitlesMergeButton.setText("brose...");
            }
        }

        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////// Text upload ///////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////
        {
            final JPanel textUploadPanel = new JPanel(new GridBagLayout());
            textUploadPanel.setBorder(BorderFactory.createTitledBorder("Text upload"));
            //loginText.setMaximumSize(new Dimension(160, 0));
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
            gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
            gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
            gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
            gbc.weightx = 0.0;
            gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
            gbc.gridy = 3;
            //gbc.ipady = 140;                          // ����������� ������������ �������
            //gbc.ipadx = 270;                          // ����������� ������������ �������
            gbc.insets = new Insets(0, 0, 0, 0);
            toolsTab.add(textUploadPanel, gbc);
            {
                JLabel textUploadLabel = new JLabel("Select a file to upload");
                textUploadLabel.setHorizontalAlignment(JLabel.RIGHT);
                textUploadLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
                gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
                gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
                gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
                gbc.weightx = 0.0;
                gbc.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
                gbc.gridy = 0;
                //gbc11.ipady = 140;                          // ����������� ������������ �������
                //gbc11.ipadx = 270;                          // ����������� ������������ �������
                gbc.insets = new Insets(upAndUnderMargin, leftAndRightMargin, upAndUnderMargin, behindLabelAndWidgetMargin);
                textUploadPanel.add(textUploadLabel, gbc);

                final JButton textUploadButton = new JButton();
                textUploadButton.setPreferredSize(new Dimension(100, 0));
                textUploadButton.setAction(new AbstractAction() {//������ ����� ������ ����� ���������� ������ ������
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        throw new UnsupportedOperationException();
                    }
                });
                textUploadButton.setText("brose...");
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
                gbc.anchor = GridBagConstraints.CENTER;  // �������� � ���� ����������
                gbc.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
                gbc.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
                gbc.weightx = 0.0;
                gbc.gridx = 1;                             // gridx �  gridy ���������� ���� �������� ���������
                gbc.gridy = 0;
                //gbc.ipady = 140;                          // ����������� ������������ �������
                //gbc.ipadx = 270;                          // ����������� ������������ �������
                gbc.insets = new Insets(upAndUnderMargin, 0, upAndUnderMargin, leftAndRightMargin);
                textUploadPanel.add(textUploadButton, gbc);
            }
        }


        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////// Footer //////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////

        final JPanel buttonsPanel = new JPanel();
        saveButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);
        contentPane.add(buttonsPanel);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSaveOrRegister();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCancel();
            }
        });

        pack();
    }

    public void prepareForFirstInput() {
        saveButton.setText("Save/Register");
    }

    public void prepareSettingsView() {
        saveButton.setText("OK");
    }


    @Override
    public SettingsViewController getController() {
        return controller;
    }

    @Override
    public void fillWidgets(SettingsViewModel model) {
        if (model.getLogin() == null && loginText.getText() != null) {
            model.setLogin(loginText.getText());
        } else {
            loginText.setText(model.getLogin());
        }
        if (null != model.getPassword()) {
            passwordText.setText(PASSWORD_STUB);
        } else {
            passwordText.setText(null);
        }
        portionText.setText(ObjectUtils.toString(model.getPortion()));
        ratingsPanel.setRatings(model.getRatings());
    }

    @Override
    public void fillModel(SettingsViewModel model) {
        //model.setCacheMaxSize();
        //model.setCurrentNum();
        //model.setFacedLangId(Const.DEFAULT_LANG_ID.longValue());
        //model.setId();
        model.setLogin(loginText.getText());
        //model.setOpacity();
        final char[] passChars = passwordText.getPassword();
        final String pass = new String(passChars);
            // если пароль уже есть в модели используем его
            // на экране отображается заглушка пароля
        if (PASSWORD_STUB.equals(pass) && null == model.getPassword()) {
            throw new BimValidationException("'%s' can't be used as password", pass);
            // если пароль введен пользователем и не равен залушке берем его, предварительно валидируем
            // иначе оставляем в модели старый пароль
        } else if (!PASSWORD_STUB.equals(pass)) {
            controller.getValidator().validatePassword(pass);
            model.setPassword(GuiUtil.createDigest(passChars));
        }
        model.setPortion(Long.valueOf(portionText.getText()));
        //model.setPreviewDuration();
        //model.setPreviewInterval();
        //model.setRecordsCount();
        //model.setShadowedLangId(Const.);
        //model.setSortingDirection(Const.DEFAULT_SORTING_DIRECTION);
        //model.setSortingField(Const.DEFAULT_SORTING_FIELD);
        model.getRatings().clear();
        model.getRatings().addAll(ratingsPanel.getRatings());
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void selectTab(int num) {
        switch (num) {
            case 1:
                tabbedPane.setSelectedComponent(genericTab);
                break;
            case 2:
                tabbedPane.setSelectedComponent(prevSettTab);
                break;
            case 3:
                tabbedPane.setSelectedComponent(toolsTabScroll);
                break;
        }
    }
}
