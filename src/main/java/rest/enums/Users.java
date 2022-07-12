package rest.enums;

public enum Users {
    //positive
    GOOD_USER("login", "Password");

    //negative


    // etc...

    String login;
    String password;

    Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin(){
        return login;
    }
    public String getPassword(){
        return password;
    }

    @Override
    public String toString(){
        return String.format("{Login=%s | Password=%s}", getLogin(),
                getPassword());
    }
}
