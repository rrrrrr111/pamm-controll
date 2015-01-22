package ru.roman.pammcontr.gui.pane.edit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.common.mvc.View;
import ru.roman.pammcontr.gui.custom.widget.LoadingPanel;
import ru.roman.pammcontr.gui.custom.widget.TiedCheckBoxPanel;
import ru.roman.pammcontr.model.Lang;
import ru.roman.pammcontr.model.WordCategory;
import ru.roman.pammcontr.model.WordType;
import ru.roman.pammcontr.util.GuiUtil;
import ru.roman.pammcontr.util.WsUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Vector;

/** @author Roman 18.12.12 0:02 */
public class EditView extends JFrame implements View<EditViewModel, EditView, EditViewController> {
    private static final Log log = LogFactory.getLog(EditView.class);
    public static final String HTTP_TRANSLATE_YANDEX_RU = "http://translate.yandex.ru/";
    public static final String HTTP_TRANSLATE_GOOGLE_RU = "http://translate.google.ru/";

    private final EditViewController controller = new EditViewController(this);

    private final JTextArea facedArea = new JTextArea();
    private final JTextArea translationArea = new JTextArea();

    private final JButton prevButton = new JButton("prev");
    private final JButton nextButton = new JButton("next");
    private final JButton saveButton = new JButton("save");
    private final JButton newButton = new JButton("new");
    private final JButton closeButton = new JButton("close");

    private TiedCheckBoxPanel checkPanel;
    private JComboBox typeComboBox;
    private JComboBox categoryComboBox;

    private final JLabel facedLangReduction = new JLabel("XX");
    private final JLabel translationLangReduction = new JLabel("YY");
    private final JButton translateYaFacedButton = new JButton("ya");
    private final JButton translateYaTranslationButton = new JButton("ya");
    private final JButton translateGooFacedButton = new JButton("go");
    private final JButton translateGooTranslationButton = new JButton("go");

    public EditView() {

        createView();
        controller.onInit();
    }



    private void createView() {

        final JPanel panel = new JPanel(new GridBagLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        final Dimension preferredSize = new Dimension(470, 340);
        setPreferredSize(preferredSize);
        setResizable(true);
        setIconImage(GuiUtil.createMainImage());
        setLocation(GuiUtil.getCenterPosition(preferredSize));

        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        facedArea.setEditable(true);
        facedArea.setLineWrap(true);
        facedArea.setWrapStyleWord(true);
        facedArea.setFont(font);
        JScrollPane facedAreaScrollPane = new JScrollPane(facedArea);

        translationArea.setEditable(true);
        translationArea.setLineWrap(true);
        translationArea.setWrapStyleWord(true);
        translationArea.setFont(font);
        JScrollPane translationAreaScrollPane = new JScrollPane(translationArea);

        Collection <WordType> types = controller.getTypes();
        typeComboBox = new JComboBox(new Vector<WordType>(types));
        Collection <WordCategory> categories = controller.getCategories();
        categoryComboBox = new JComboBox(new Vector<WordCategory>(categories));

        // �����
//        final JPanel textPanel = new JPanel(new CardLayout());
//        textPanel.add(textLabel);
//        textPanel.setMinimumSize(new Dimension(269, 130));
//        textPanel.setMaximumSize(new Dimension(269, 130));
//        textPanel.setSize(new Dimension(269, 130));

        final GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
        gbc1.anchor = GridBagConstraints.PAGE_START;  // �������� � ���� ����������
        gbc1.gridwidth = 6;                         // ���-�� ����� ����������� �� ������
        gbc1.gridheight = 1;                         // ���-�� ����� ����������� �� ������
        gbc1.weighty = 1.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
        gbc1.weightx = 1.0;
        gbc1.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
        gbc1.gridy = 0;
        //gbc1.ipady = 140;                          // ����������� ������������ �������
        //gbc1.ipadx = 270;                          // ����������� ������������ �������
        panel.add(facedAreaScrollPane, gbc1);

        final GridBagConstraints gbc1$1 = new GridBagConstraints();
        gbc1$1.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
        //gbc1$1.anchor = GridBagConstraints.PAGE_START;  // �������� � ���� ����������
        gbc1$1.gridwidth = 6;                         // ���-�� ����� ����������� �� ������
        gbc1$1.weighty = 1.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
        gbc1$1.weightx = 1.0;
        gbc1$1.gridx = 0;                             // gridx �  gridy ���������� ���� �������� ���������
        gbc1$1.gridy = 1;
        panel.add(translationAreaScrollPane, gbc1$1);

        // ������
        final GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.weighty = 0.0;
        panel.add(prevButton, gbc2);

        final GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 1;
        gbc3.gridy = 3;
        gbc3.weighty = 0.0;
        panel.add(nextButton, gbc3);

        JPanel buttPanel = new JPanel();
        buttPanel.add(saveButton);
        buttPanel.add(newButton);
        buttPanel.add(closeButton);

        final GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.fill = GridBagConstraints.HORIZONTAL;
        gbc5.gridx = 3;
        gbc5.gridy = 3;
        gbc5.gridwidth = 3;
        gbc5.weighty = 0.0;
        panel.add(buttPanel, gbc5);


        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onPrev();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onNext();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSave();
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onNew();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onClose();
            }
        });
        translateYaFacedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onTranslateTranslationYandex();
            }
        });
        translateYaTranslationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onTranslateFacedYandex();

            }
        });
        translateGooFacedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onTranslateTranslationGoogle();
            }
        });
        translateGooTranslationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onTranslateFacedGoogle();
            }
        });



        prevButton.setToolTipText("previous word");
        nextButton.setToolTipText("next word");
        saveButton.setToolTipText("save word");
        newButton.setToolTipText("create new word");
        closeButton.setToolTipText("close window");
        translateYaFacedButton.setToolTipText("translate by " + HTTP_TRANSLATE_YANDEX_RU);
        translateYaTranslationButton.setToolTipText("translate by " + HTTP_TRANSLATE_YANDEX_RU);
        translateGooFacedButton.setToolTipText("translate by " + HTTP_TRANSLATE_GOOGLE_RU);
        translateGooTranslationButton.setToolTipText("translate by " + HTTP_TRANSLATE_GOOGLE_RU);

        // ��������
        checkPanel = new TiedCheckBoxPanel();
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

        final GridBagConstraints gbc8 = new GridBagConstraints();
        gbc8.fill = GridBagConstraints.HORIZONTAL;
        gbc8.gridwidth = 2;
        gbc8.gridx = 3;
        gbc8.gridy = 2;
        gbc8.weighty = 0.0;
        gbc8.weightx = 1.0;
        gbc8.insets = new Insets(0,3,0,3);
        panel.add(typeComboBox, gbc8);

        GridBagConstraints gbc9 = new GridBagConstraints();
        gbc9.fill = GridBagConstraints.HORIZONTAL;
        gbc9.gridwidth = 1;
        gbc9.gridx = 5;
        gbc9.gridy = 2;
        gbc9.weighty = 0.0;
        gbc9.weightx = 1.0;
        gbc9.insets = new Insets(0,0,0,3);
        panel.add(categoryComboBox, gbc9);


        JPanel facedAreaButtonsPanel = new JPanel();
        facedAreaButtonsPanel.setLayout(new BoxLayout(facedAreaButtonsPanel, BoxLayout.Y_AXIS));
        facedAreaButtonsPanel.add(facedLangReduction);
        facedAreaButtonsPanel.add(translateYaFacedButton);
        facedAreaButtonsPanel.add(translateGooFacedButton);

        facedLangReduction.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        gbc9 = new GridBagConstraints();
        gbc9.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
        //gbc9.anchor = GridBagConstraints.PAGE_START;  // �������� � ���� ����������
        gbc9.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
        gbc9.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
        gbc9.weightx = 0.0;
        gbc9.gridx = 6;                             // gridx �  gridy ���������� ���� �������� ���������
        gbc9.gridy = 0;
        //gbc9.ipady = 140;                          // ����������� ������������ �������
        //gbc9.ipadx = 270;                          // ����������� ������������ �������
        gbc9.insets = new Insets(0,0,0,0);
        panel.add(facedAreaButtonsPanel, gbc9);

        JPanel translationAreaButtonsPanel = new JPanel();
        translationAreaButtonsPanel.setLayout(new BoxLayout(translationAreaButtonsPanel, BoxLayout.Y_AXIS));
        translationAreaButtonsPanel.add(translationLangReduction);
        translationAreaButtonsPanel.add(translateYaTranslationButton);
        translationAreaButtonsPanel.add(translateGooTranslationButton);

        translationLangReduction.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        GridBagConstraints gbc11 = new GridBagConstraints();
        gbc11.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
        //gbc9.anchor = GridBagConstraints.PAGE_START;  // �������� � ���� ����������
        gbc11.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
        gbc11.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
        gbc11.weightx = 0.0;
        gbc11.gridx = 6;                             // gridx �  gridy ���������� ���� �������� ���������
        gbc11.gridy = 1;
        //gbc11.ipady = 140;                          // ����������� ������������ �������
        //gbc11.ipadx = 270;                          // ����������� ������������ �������
        panel.add(translationAreaButtonsPanel, gbc11);

        gbc11 = new GridBagConstraints();
        gbc11.fill = GridBagConstraints.BOTH;        // ��� ������� ��������� ������ ������������
        //gbc9.anchor = GridBagConstraints.PAGE_START;  // �������� � ���� ����������
        gbc11.gridwidth = 1;                         // ���-�� ����� ����������� �� ������
        gbc11.weighty = 0.0;                         // ��� ����������, ���� ����������� ��� ��������� ���������� ������������
        gbc11.weightx = 0.0;
        gbc11.gridx = 6;                             // gridx �  gridy ���������� ���� �������� ���������
        gbc11.gridy = 3;
        //gbc11.ipady = 140;                          // ����������� ������������ �������
        //gbc11.ipadx = 270;                          // ����������� ������������ �������
        gbc11.insets = new Insets(3,2,3,2);
        panel.add(LoadingPanel.createSharedInstance(), gbc11);

        pack();

    }

    private static final int TITLE_MAX_LENGTH = 35;

    @Override
    public void fillWidgets(EditViewModel model) {
        setTexts(model);
        typeComboBox.getModel().setSelectedItem(WordType.valueOf(model.getType()));
        categoryComboBox.getModel().setSelectedItem(
                model.getCategory() == null ? WordCategory.COMMON : WordCategory.valueOf(model.getCategory()));
        checkPanel.setRating(model.getRating().intValue());
        setTitle(model);

        facedLangReduction.setText(Lang.valueOf(model.getFacedLangId()).getReductionUpper());
        translationLangReduction.setText(Lang.valueOf(model.getShadowedLangId()).getReductionUpper());
    }

    protected void setTexts(EditViewModel model) {
        facedArea.setText(model.getTextFaced());
        translationArea.setText(model.getTextShadowed());
    }

    public void setTitle(EditViewModel model) {
        if (model.getId() != null) {
            setTitle("Edit: " + StringUtils.abbreviate(model.getTextFaced(), TITLE_MAX_LENGTH));
        } else {
            setTitle("Create new");
        }
    }

    @Override
    public EditViewController getController() {
        return controller;
    }

    public void fillModel(EditViewModel currModel) {
        currModel.setRating(Long.valueOf(checkPanel.getRating()));
        fillTexts(currModel);
        currModel.setType(((WordType) typeComboBox.getItemAt(typeComboBox.getSelectedIndex())).getOrdinal());
        currModel.setCategory(((WordCategory) categoryComboBox.getItemAt(categoryComboBox.getSelectedIndex())).getOrdinal());
        currModel.setEditDate(WsUtil.getCurrGregorian());
    }

    protected void fillTexts(EditViewModel currModel) {
        String faced = facedArea.getText();
        String shadowed = translationArea.getText();
        WordUtils.fillTexts(currModel, faced, shadowed);
    }


}
