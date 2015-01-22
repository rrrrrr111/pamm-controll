package ru.roman.pammcontr.gui.common.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/** @author Roman 26.01.13 14:56 */
public class CommonValidator {


    public void checkEmpty(String name, String str) {
        if (StringUtils.isBlank(str)) {
            throw new BimValidationException("%s can't be empty", name);
        }
    }

    public void checkEmpty(String name, List list) {
        if (list.isEmpty()) {
            throw new BimValidationException("%s can't be empty", name);
        }
    }

    public void checkSpaces(String name, String str) {
        if (StringUtils.contains(str, " ")) {
            throw new BimValidationException("%s can't contain spaces", name);
        }
    }

    public void checkLength(String name, String str, int length) {
        if (StringUtils.length(str) < length) {
            throw new BimValidationException("%s should contain at least %s chars", name, length);
        }
    }

    public void checkContainsOnly(String name, String str, String chars, String charsForUser) {
        if (!StringUtils.containsOnly(str, chars)) {
            throw new BimValidationException("%s can contain only %s", name, charsForUser);
        }
    }

    public void checkContainsOnlyReqExp(String name, String str, String regExp, String charsForUser) {
        if (!str.matches(regExp)) {
            throw new BimValidationException("%s can contain only %s", name, charsForUser);
        }
    }

    public void checkFieldIsValidMail(String name, String str) {
        if (!str.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$")) {
            throw new BimValidationException("%s should be valid mail address", name);
        }
    }
}
