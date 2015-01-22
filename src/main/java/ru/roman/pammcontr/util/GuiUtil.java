package ru.roman.pammcontr.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.common.cbchain.CallBackChain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/** @author Roman 18.12.12 0:10 */
public abstract class GuiUtil {
    private static final Log log = LogFactory.getLog(GuiUtil.class);
    public static final int TASK_BAR_HEIGHT = 40;
    private static Dimension screenSize;

    public static void startSwingApp(final CallBackChain<Void> starter) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    ExceptionHandler.registerUncaughtExceptionHandler();
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    starter.run(null);
                } catch (Exception e) {
                    starter.exception(e);
                }
            }
        });
    }

    public static void addMouseListenerToChilds(Container component, MouseAdapter listener) {
        component.addMouseListener(listener);
        for (Component comp : component.getComponents()) {
            if (comp instanceof Container) {
                addMouseListenerToChilds((Container) comp, listener);
            } else {
                comp.addMouseListener(listener);
            }
        }
    }


    // taken from: http://java.sun.com/developer/technicalArticles/GUI/translucent_shaped_windows/
    public static void setTranslucency(Window window, float opacity){
        try {
            Class<?> awtUtilitiesClass = Class.forName("com.sun.awt.AWTUtilities");
            Method mSetWindowOpacity = awtUtilitiesClass.getMethod("setWindowOpacity", Window.class, float.class);

            mSetWindowOpacity.invoke(null, window, opacity);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public static Point getRightCornerPosition(Dimension size, int padding) {
        Dimension dimension = getScreenSize();
        Integer width = (int)dimension.getWidth();
        Integer height = (int)dimension.getHeight();
        final Point position = new Point(width - (int)size.getWidth() - padding,
                height - (int)size.getHeight() - padding - TASK_BAR_HEIGHT);
//            log.info(String.format("Display size: width=%1$s, height=%2$s, position=%3$s",
//                    new Object[]{width, height, position}));
        return position;
    }


    public static Point getCenterPosition(Dimension size) {
        Dimension dimension = getScreenSize();
        Integer width = (int)dimension.getWidth();
        Integer height = (int)dimension.getHeight();
        final Point position = new Point((width - (int)size.getWidth()) / 2,
                (height - (int)size.getHeight() - TASK_BAR_HEIGHT) / 2);
        return position;
    }

    public static Point getInvisiblePosition() {
        Dimension dimension = getScreenSize();
        Integer width = (int)dimension.getWidth();
        Integer height = (int)dimension.getHeight();
        final Point position = new Point(width + 1000, height + 1000);
        return position;
    }

    public static void showInfoMessage(String mess) {
        JOptionPane.showMessageDialog(null, mess);
    }

    public static void showInfoMessage(String format, Object... params) {
        JOptionPane.showMessageDialog(null, String.format(format, params));
    }


    public static Image createMainImage() {
        return createImage(Res.MAIN_IMAGE_PATH);
    }

    public static ImageIcon createMainIcon() {
        return createIcon(Res.MAIN_IMAGE_PATH);
    }

    public static Image createLoadingImage() {
        return createImage(Res.LOADING_IMAGE_PATH);
    }

    private static Map<String, ImageIcon> images = new HashMap<String, ImageIcon>();

    public static ImageIcon createIcon(String path) {
        if (!images.containsKey(path)) {
            final URL imageURL = GuiUtil.class.getResource(path);
            final ImageIcon icon;
            if (imageURL == null) {
                log.warn("Resource not found : " + path);
                icon = null;
            } else {
                icon = new ImageIcon(imageURL, null);
            }
            images.put(path, icon);
        }
        return images.get(path);
    }

    public static Image createImage(String path) {
        ImageIcon icon = createIcon(path);
        return icon != null ? icon.getImage() : null;
    }

    private static Dimension getScreenSize() {
        try {
            if (screenSize == null) {
                Integer width = null;
                Integer height = null;
                try {
                    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                    width = gd.getDisplayMode().getWidth();
                    height = gd.getDisplayMode().getHeight();
                } catch (Exception e) {
                    log.warn("Exception while screen resolution detection", e);
                } finally {
                    if (width == null || height == null) {
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        width = (int)screenSize.getWidth();
                        height = (int)screenSize.getHeight();
                    }
                }
                screenSize = new Dimension(width, height);
            }
            return screenSize;
        } catch (RuntimeException e) {
            throw new RuntimeException("Exception while screen resolution detection", e);
        }
    }


    public static String createDigest(char [] bytes) {
        try{
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            for (char aByte : bytes) {
                algorithm.update((byte)aByte);
            }
            byte messageDigest[] = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i=0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            return hexString.toString();
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
