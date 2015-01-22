package ru.roman.pammcontr.util;

/**
 *
 * @author Roman 01.09.12 2:17
 */
public class BimException extends RuntimeException {

    public BimException() {
    }

    public BimException(String message) {
        super(message);
    }

    public BimException(String message, Object... par) {
        super(String.format(message, par));
    }

    public BimException(String message, Throwable cause) {
        super(message, cause);
    }

    public BimException(Throwable cause) {
        super(cause);
    }
}
