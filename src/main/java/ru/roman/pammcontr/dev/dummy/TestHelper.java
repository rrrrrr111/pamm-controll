package ru.roman.pammcontr.dev.dummy;

import ru.roman.pammcontr.gui.pane.main.MainViewModel;

/** @author Roman 22.12.12 13:41 */
public class TestHelper {



    public static MainViewModel createMainViewModel() {
        MainViewModel model = new MainViewModel();
        model.setRating(1L);
        return model;
    }
}
