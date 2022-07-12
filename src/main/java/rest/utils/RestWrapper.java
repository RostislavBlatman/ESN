package rest.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import rest.services.LoginService;

import static io.restassured.RestAssured.given;

public class RestWrapper {
    private static final String BASE_URL = "https://reqres.in/api";


    public LoginService login;


    private RestWrapper(){
        login = new LoginService();
    }

    public static RestWrapper getApi(){
        return new RestWrapper();
    }
}
