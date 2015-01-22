package ru.roman.pammcontr.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.StartApp;
import ru.roman.pammcontr.gui.common.validator.BimValidationException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Roman
 * Date: 01.09.12
 * Time: 0:46
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionHandler {
    private static final Log log = LogFactory.getLog(ExceptionHandler.class);
    private final static NarrowOptionPane ERR_PAINE = new NarrowOptionPane();
    private static volatile boolean errMessAlreadyShown;

    public static void showErrorMessageAndExit(Throwable t){
        showErrorMessage(t);
        StartApp.stop(1);
    }

    public static void showErrorMessage(Throwable t){

        synchronized (ExceptionHandler.class) {
            if (errMessAlreadyShown) {
                log.error(Const.APP_NAME + " exception, message box missing, because already shown :", t);
                return;
            }
            errMessAlreadyShown = true;
        }
        try {
            Validate.notNull(t);
            final String mess = createErrorText(t);
            final String title;
            if (t instanceof BimValidationException) {
                ERR_PAINE.setMessageType(JOptionPane.WARNING_MESSAGE);
                title = Const.APP_NAME + " validation error";
            } else {
                ERR_PAINE.setMessageType(JOptionPane.ERROR_MESSAGE);
                title = Const.APP_NAME + " error";
                log.error(Const.APP_NAME + " exception :", t);
            }
            ERR_PAINE.setMessage(mess);
            ERR_PAINE.createDialog(null, title).setVisible(true);
        } finally {
            errMessAlreadyShown = false;
        }
    }

    private static String createErrorText(Throwable e) {
        final StrBuilder ms = new StrBuilder();
        String mess;
        while (e != null) {
            if (e instanceof SQLException) {
                SQLException se = (SQLException)e;
                checkMess(se.getMessage(), ms, "");
                checkMess("SQLState=" + se.getSQLState(), ms, " ");
                checkMess("ErrorCode=" + se.getErrorCode(), ms, ", ");

                e = se.getNextException();
                if (e == null) {
                    e = se.getCause();
                }
            } else {
                checkMess(e.getMessage(), ms, " ");
                e = e.getCause();
            }
            ms.append(" : ");
        }
        mess = ms.substring(0, ms.length()-3).replace(" :  : ", " : ");
        return mess;
    }

    private static void checkMess(String mess, StrBuilder ms, String prefix) {
        if (StringUtils.isNotBlank(mess) && !ms.contains(mess)) {
            ms.append(prefix).append(mess);
        }
    }

    public static class NarrowOptionPane extends JOptionPane {
        public NarrowOptionPane() {
            super();
            setMessageType(JOptionPane.ERROR_MESSAGE);
        }

        public int getMaxCharactersPerLineCount() {
            return 100;
        }
    }

    public static void registerUncaughtExceptionHandler() {
        EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        queue.push(new EventQueue () {
            @Override
            protected void dispatchEvent(AWTEvent newEvent) {
                try {
                    super.dispatchEvent(newEvent);
                } catch (Throwable t) {
                    showErrorMessage(t);
                }
            }
        });
    }
}
