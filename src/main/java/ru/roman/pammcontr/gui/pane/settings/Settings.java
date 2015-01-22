package ru.roman.pammcontr.gui.pane.settings;

import ru.roman.pammcontr.model.UserSettingsModel;
import ru.roman.pammcontr.service.ServiceFactory;

/** @author Roman 29.01.13 22:37 */
public class Settings {

    public static UserSettingsModel get() {
        return ServiceFactory.getConfigService().loadSettingsConfig();
    }

    public static void createInitialSettings() {

    }
}
