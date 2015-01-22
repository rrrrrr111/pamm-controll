package ru.roman.pammcontr.gui.pane;


import ru.roman.pammcontr.gui.pane.main.MainView;
import ru.roman.pammcontr.gui.pane.main.MainViewController;



/**
 *
 * User: Roman
 * DateTime: 01.09.12 0:15
 */
public class PaineFactory {

    private static MainView mainView;

    public static MainView createMainView() {
        if (mainView == null) {
            PaineFactory.mainView = new MainView();
        }
        return mainView;
    }

    public static MainViewController getMainViewController() {
        return createMainView().getController();
    }




}
