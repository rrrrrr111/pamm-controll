package ru.roman.pammcontr.service.config;

import ru.roman.pammcontr.service.config.reader.XmlReader;
import ru.roman.pammcontr.service.config.writer.XmlWriter;

/** @author Roman 25.01.13 23:28 */
public interface XmlConfigService {

    public <T> T loadConfig(String fileName, XmlReader<T> reader);

    public <T> T loadEncryptedConfig(String fileName, XmlReader<T> reader);

    public <T> void saveConfig(T model, String fileName, XmlWriter<T> writer);

    public <T> void saveEncryptedConfig(T model, String fileName, XmlWriter<T> writer);
}
