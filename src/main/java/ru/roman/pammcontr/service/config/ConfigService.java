package ru.roman.pammcontr.service.config;

import ru.roman.pammcontr.model.UserSettingsModel;

/** @author Roman 26.01.13 0:28
 *
 * ������ � ������ ������������
 *
 */
public interface ConfigService {

    <T> T loadConfig(String fileName, Class<T> clazz);

    <T> T loadEncryptedConfig(String fileName, Class<T> clazz);

    <T> void saveConfig(T model, String fileName);

    <T> void saveEncryptedConfig(T model, String fileName);

    /**
     * ��������� ��������� �� �����
     *
     * @return
     */
    UserSettingsModel loadSettingsConfig();

    /**
     * ��������� ��������� � ����
     *
     * @param model
     */
    void saveSettingsConfig(UserSettingsModel model);
}
