package ru.roman.pammcontr.gui.custom.widget;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.pane.PaineFactory;
import ru.roman.pammcontr.gui.pane.tray.TrayJPopupMenu;
import ru.roman.pammcontr.gui.pane.tray.TrayUtils;
import ru.roman.pammcontr.util.Const;
import ru.roman.pammcontr.util.GuiUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 *
 * ��������������� ���� ��� ������ ��������� � JWindow
 *
 * @author Roman 30.03.13 11:12 */
public class TransparentWindowSupport extends JDialog {
    private static final Log log = LogFactory.getLog(TransparentWindowSupport.class);

    public TransparentWindowSupport() throws HeadlessException {

        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(270, 140));
        setType(Type.POPUP);
        setResizable(false);
        setFocusable(true);
        setLocation(GuiUtil.getInvisiblePosition());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle(Const.APP_NAME_WITH_VERSION);
        setAutoRequestFocus(true);
        setIconImage(GuiUtil.createMainImage());
        setUndecorated(true);

        setVisible(false);

        final JLabel lab = new JLabel();
        add(lab);
        final int c = JComponent.WHEN_IN_FOCUSED_WINDOW;
        final KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK);
        lab.getInputMap(c).put(ks, "disableOnAltD");
        lab.getActionMap().put("disableOnAltD", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("Event Ald+D catch");
                final TrayJPopupMenu menu = TrayUtils.getPopupMenu();
                switch (PaineFactory.getMainViewController().getState()) {
                    case DISABLED:
                        menu.setDisableItemSelected(false);
                        break;
                    case SCHEDULED:
                        menu.setDisableItemSelected(true);
                        break;
                }
            }
        });
        pack();

    }
}
