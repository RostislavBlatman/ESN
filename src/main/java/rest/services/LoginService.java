package rest.services;

import io.qameta.allure.Step;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import rest.pojos.login.LoginRequest;

import static io.restassured.RestAssured.given;

public class LoginService extends RestService{

    @Override
    protected String getBasePath() {
        return "/login";
    }

    @Step("Login as {rq.login}")
    public Response loginAs(LoginRequest rq){
        return RestAssured.given()
                .basePath("/login")
                .body(rq)
                .when()
                .post();
    }

}
