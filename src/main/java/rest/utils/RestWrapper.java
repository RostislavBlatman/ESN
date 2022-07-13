package rest.utils;

import rest.services.LoginService;

public class RestWrapper {

    public LoginService login;

    private RestWrapper() {
        login = new LoginService();
    }

    public static RestWrapper getApi() {
        return new RestWrapper();
    }
}
