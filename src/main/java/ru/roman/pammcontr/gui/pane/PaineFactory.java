package ru.roman.pammcontr.gui.pane;


import ru.roman.pammcontr.gui.pane.main.MainView;
import ru.roman.pammcontr.gui.pane.main.MainViewController;
import ru.roman.pammcontr.gui.pane.settings.SettingsView;
import ru.roman.pammcontr.gui.pane.settings.SettingsViewController;

/**
 *
 * User: Roman
 * DateTime: 01.09.12 0:15
 */
public class PaineFactory {

    private static MainView mainView;
    private static SettingsView settingsView;

    public static MainView createMainView() {
        if (mainView == null) {
            PaineFactory.mainView = new MainView();
        }
        return mainView;
    }

    public static MainViewController getMainViewController() {
        return createMainView().getController();
    }


    public static SettingsView createSettingsView() {
        if (settingsView == null) {
            PaineFactory.settingsView = new SettingsView();
        }
        return settingsView;
    }

    public static SettingsViewController getSettingsViewController() {
        return createSettingsView().getController();
    }
}
