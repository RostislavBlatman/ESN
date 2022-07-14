package rest.enums;

public enum Users {
    //positive
    GOOD_USER("login", "password"),
    JUST_CHANGED_PASSWORD_USER_WITH_NEW_PASSWORD("login3", "new_password"),


    //negative
    NON_EXISTENT_USER("login1", "password"),
    JUST_DELETED_USER("login2", "password"),
    JUST_CHANGED_PASSWORD_USER_WITH_OLD_PASSWORD("login3", "old_password"),
    USER_WITH_EMPTY_LOGIN("", "password"),
    USER_WITH_EMPTY_PASSWORD("login", ""),
    EXISTENT_USER_LOGIN_IN_UPPERCASE("LOGIN", "password"),
    EXISTENT_USER_LOGIN_BUT_PASSWORD_IN_UPPERCASE("login", "PASSWORD"),
    LOGIN_WITH_BAD_SYMBOLS("lo\uD83D\uDE0D﷽gin", "PASSWORD"),
    PASSWORD_WITH_BAD_SYMBOLS("login", "passॣ◕｡\uD843\uDC96\uD843\uDCCFword"),
    LOGIN_WITH_SPACE("log in", "password"),
    ADMIN("admin", "admin"),
    SQL_INJECTION("'SELECT * FROM table'", "password"),

    ;

    // etc...

    final String login;
    final String password;

    Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
