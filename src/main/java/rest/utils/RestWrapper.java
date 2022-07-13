package rest.utils;

import rest.services.LoginService;

public class RestWrapper {
    private static final String BASE_URL = "https://reqres.in/api";


    public LoginService login;


    private RestWrapper() {
        login = new LoginService();
    }

    public static RestWrapper getApi() {
        return new RestWrapper();
    }
}
