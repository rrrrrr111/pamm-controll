package ru.roman.pammcontr.gui.pane.settings;

import ru.roman.pammcontr.gui.common.validator.CommonValidator;

import java.util.List;

/** @author Roman 26.01.13 14:25 */
public class SettingsViewValidator extends CommonValidator {


    public static final int PASSWORD_LENGTH = 8;
    public static final int LOGIN_LENGTH = 9;


    public void validateLogin(String login) {
        checkEmpty("Login", login);
        //checkContainsOnlyReqExp("Login", login, "^[a-zA-Z0-9@\\.]*$", "a-Z 0-9 _");
        checkFieldIsValidMail("Login", login);
        checkLength("Login", login, LOGIN_LENGTH);
    }

    public void validatePassword(String password) {
        checkEmpty("Password", password);
        checkSpaces("Password", password);
        checkLength("Password", password, PASSWORD_LENGTH);
    }

    public void validateRatings(List<Integer> ratings) {
        checkEmpty("Ratings", ratings);
    }

}
