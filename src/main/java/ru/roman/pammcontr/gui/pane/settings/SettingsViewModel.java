package ru.roman.pammcontr.gui.pane.settings;

import ru.roman.pammcontr.gui.common.mvc.Model;
import ru.roman.pammcontr.service.gae.wsclient.UserSettingsModel;
import ru.roman.pammcontr.util.PropUtil;

/** @author Roman 16.01.13 23:59 */
public class SettingsViewModel extends UserSettingsModel implements Model{


    public SettingsViewModel() {
    }

    public SettingsViewModel(UserSettingsModel res) {
        PropUtil.copyProperties(this, res);
        getRatings().addAll(res.getRatings());
        getCategories().addAll(res.getCategories());
        getSubscribed().addAll(res.getSubscribed());
        getTypes().addAll(res.getTypes());
    }

    @Override
    public int compareTo(Object o) {
        throw new RuntimeException("not implemented");
    }
}
