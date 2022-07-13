package rest.services;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import rest.pojos.login.LoginRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginService extends RestService {

    @Override
    protected String getBasePath() {
        return "/login";
    }

    @Step("Login as {rq.login}")
    public Response loginAs(LoginRequest rq) {
        return RestAssured.given()
                .basePath("/login")
                .body(rq)
                .when()
                .post();
    }

    @Step("Login as {login}")
    public Response loginAs(String login, String password) {
        Map<String, String> body = new HashMap<>();
        if (login != null) body.put("login", login);
        if (password != null) body.put("password", password);
        return RestAssured.given()
                .basePath("/login")
                .body(body)
                .when()
                .post();
    }

}
