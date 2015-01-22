package ru.roman.pammcontr.gui.common.validator;

import ru.roman.pammcontr.util.BimException;

/** @author Roman 26.01.13 14:28 */
public class BimValidationException extends BimException {


    public BimValidationException(String message) {
        super(message);
    }

    public BimValidationException(String message, Object... par) {
        super(message, par);
    }
}
