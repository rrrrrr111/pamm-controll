package ru.roman.pammcontr.service.config;

import org.exolab.castor.mapping.FieldHandler;
import org.exolab.castor.mapping.ValidityException;
import ru.roman.pammcontr.model.UserSettingsModel;
import ru.roman.pammcontr.util.WsUtil;

import java.util.Date;

/** @author Roman 25.08.13 21:40 */
public class XmlGregorianCalendarHandler implements FieldHandler {


    @Override
    public Object getValue(Object object) throws IllegalStateException {
        UserSettingsModel model = (UserSettingsModel)object;
        return WsUtil.asDate(model.getEditDate());
    }

    @Override
    public void setValue(Object object, Object value) throws IllegalStateException, IllegalArgumentException {
        UserSettingsModel model = (UserSettingsModel)object;
        model.setEditDate(WsUtil.asXMLGregorianCalendar((Date) value));
    }

    @Override
    public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
        UserSettingsModel model = (UserSettingsModel)object;
        model.setEditDate(null);
    }

    @Override
    public void checkValidity(Object object) throws ValidityException, IllegalStateException {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Object newInstance(Object parent) throws IllegalStateException {
        return null;
    }
}
