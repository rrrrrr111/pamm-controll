package ru.roman.pammcontr.gui.pane.tray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.util.AppException;
import ru.roman.pammcontr.util.Const;
import ru.roman.pammcontr.util.GuiUtil;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** @author Roman 22.12.12 3:14 */
public class TrayUtils {
    private static final Log log = LogFactory.getLog(TrayUtils.class);

    private static TrayIcon trayIcon;
    private static TrayJPopupMenu popupMenu;

    private TrayUtils(){
    }


    public synchronized static void addTrayIcon() {

        //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            throw new AppException("SystemTray is not supported by platform");
        }

        removeTrayIcon();
        trayIcon = new TrayIcon(GuiUtil.createMainImage());


        final TrayController controller = new TrayController();

//        final TrayPopupMenu popupMenu = new TrayPopupMenu(controller);
//        trayIcon.setPopupMenu(popupMenu);

        popupMenu = new TrayJPopupMenu(controller);
        trayIcon.addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                    popupMenu.setLocation(e.getX(), e.getY());
                    popupMenu.setInvoker(popupMenu);
                    popupMenu.showPopup();
                } else if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
                    controller.onShowQuickly();
                }
            }
        });
        trayIcon.setToolTip(Const.APP_NAME_WITH_VERSION);
        trayIcon.setImageAutoSize(true);

        try {
            final SystemTray tray = SystemTray.getSystemTray();
            tray.add(trayIcon);
        } catch (Exception e) {
            throw new AppException("TrayIcon could not be added", e);
        }


    }

    public synchronized static void removeTrayIcon() {
        if (trayIcon != null) {
            final SystemTray tray = SystemTray.getSystemTray();
            tray.remove(trayIcon);
        }
    }

    public static void showTrayNotification(String str, TrayIcon.MessageType type) {
        if (Const.SHOW_TRAY_NOTIFICATIONS && trayIcon != null) {
            trayIcon.displayMessage(Const.APP_NAME, str, type);
        }
    }

    public static TrayJPopupMenu getPopupMenu() {
        return popupMenu;
    }
}
