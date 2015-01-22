package ru.roman.pammcontr.service.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.mapping.Mapping;
import ru.roman.pammcontr.gui.pane.settings.SettingsViewModel;
import ru.roman.pammcontr.service.ServiceFactory;
import ru.roman.pammcontr.service.config.reader.CastorReader;
import ru.roman.pammcontr.service.config.writer.CastorWriter;
import ru.roman.pammcontr.service.config.writer.XmlWriter;
import ru.roman.pammcontr.util.Const;

import java.net.URL;

/** @author Roman 26.01.13 0:28 */
public class CastorConfigServiceImpl implements ConfigService {
    private static final Log log = LogFactory.getLog(CastorConfigServiceImpl.class);

    public static final String SETTINGS_FILE_NAME;
    static {
        if (Const.DEV_MODE) {
            SETTINGS_FILE_NAME = "settings.dev";
        } else {
            SETTINGS_FILE_NAME = "settings";
        }
    }

    private XmlConfigService xmlConfigService = ServiceFactory.getXmlConfigService();
    private static final XmlWriter WRITER = new CastorWriter();

    @Override
    public <T> T loadConfig(String fileName, Class<T> clazz) {
        return (T)xmlConfigService.loadConfig(fileName, new CastorReader(clazz));
    }

    @Override
    public <T> T loadEncryptedConfig(String fileName, Class<T> clazz) {
        return (T)xmlConfigService.loadEncryptedConfig(fileName, new CastorReader(clazz));
    }

    @Override
    public <T> void saveConfig(T model, String fileName) {
        xmlConfigService.saveConfig(model, fileName, WRITER);
    }

    @Override
    public <T> void saveEncryptedConfig(T model, String fileName) {
        xmlConfigService.saveEncryptedConfig(model, fileName, WRITER);
    }


    public static SettingsViewModel settingsModel;
    @Override
    public synchronized SettingsViewModel loadSettingsConfig() {
        if (settingsModel == null) {
            settingsModel = loadEncryptedConfig(SETTINGS_FILE_NAME, SettingsViewModel.class);
            if (settingsModel != null) {
                log.info("Settings loaded from config file");
            }
        }
        return settingsModel;
    }

    @Override
    public synchronized void saveSettingsConfig(SettingsViewModel model) {
        saveEncryptedConfig(model, SETTINGS_FILE_NAME);
        settingsModel = model;
        log.info("Settings saved to config file");
    }

    public static Mapping mapping;
    public static synchronized Mapping getMapping() {
        try {
            if (mapping == null) {
                final URL url = CastorWriter.class.getResource("/CastorMappings.xml");
                mapping = new Mapping();
                mapping.loadMapping(url);
            }
            return mapping;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
