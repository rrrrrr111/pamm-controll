package ru.roman.pammcontr.gui.pane.settings;

import ru.roman.pammcontr.model.PammInfo;
import ru.roman.pammcontr.model.UserSettingsModel;
import ru.roman.pammcontr.service.ServiceFactory;
import ru.roman.pammcontr.util.Const;
import ru.roman.pammcontr.util.WsUtil;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

/** @author Roman 29.01.13 22:37 */
public class Settings {

    public static UserSettingsModel get() {
        return ServiceFactory.getConfigService().loadSettingsConfig();
    }

    public static void createInitialSettings() {

        final UserSettingsModel sett = new UserSettingsModel();

        sett.setFastPammUrl("http://fastpamm.com/");
        sett.setLastCheckDate(null);
        sett.setLookAndFeel("");
        sett.setMinimalPercentLevelToControl((double) -1);
        sett.setOpacity(0.75f);
        sett.setSettingsEditDate(WsUtil.getCurrGregorian());
        final List<PammInfo> pammInfos = new ArrayList<>();
        sett.setPammInfoList(pammInfos);
        pammInfos.add(new PammInfo("Ahmedos", 558616L, 0.0, false));
        pammInfos.add(new PammInfo("sean", 561368L, 0.0, false));
        pammInfos.add(new PammInfo("votfx", 520050L, 0.0, false));
        if (Const.DEV_MODE) {
            sett.setCheckFastPammInterval(0.25);       // in minutes
            sett.setPreviewDuration(0.05);             // in minutes
            sett.setDisabilityDuration((double) 5);      // in minutes
        } else {
            sett.setCheckFastPammInterval((double) 10);
            sett.setPreviewDuration(0.1);
            sett.setDisabilityDuration((double) 60);
        }

        ServiceFactory.getConfigService().saveSettingsConfig(sett);
    }
}
