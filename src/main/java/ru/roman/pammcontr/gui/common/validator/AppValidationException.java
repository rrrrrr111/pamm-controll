package ru.roman.pammcontr.gui.common.validator;

import ru.roman.pammcontr.util.AppException;

/** @author Roman 26.01.13 14:28 */
public class AppValidationException extends AppException {


    public AppValidationException(String message) {
        super(message);
    }

    public AppValidationException(String message, Object... par) {
        super(message, par);
    }
}
