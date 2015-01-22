package ru.roman.pammcontr.util;

/**
 *
 * @author Roman 01.09.12 2:17
 */
public class AppException extends RuntimeException {

    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Object... par) {
        super(String.format(message, par));
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}
