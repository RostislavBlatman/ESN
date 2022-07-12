package rest.login;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import rest.BaseTest;
import rest.enums.Users;
import rest.pojos.login.LoginRequest;
import rest.pojos.login.LoginResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Login")
public class PositiveLoginTests extends BaseTest {

    @Test
    @Story("Check login with correct fields")
    public void checkLoginWithCorrectFields(){

        Response response = api.login
                .loginAs(new LoginRequest(Users.GOOD_USER.getLogin(), Users.GOOD_USER.getPassword()));

        assertThat(response)
                .isNotNull()
                .extracting(ResponseOptions::statusCode).isEqualTo(200);

        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertThat(loginResponse.getLogin()).isEqualTo(Users.GOOD_USER.getLogin());
        assertThat(loginResponse.getToken()).isNotEmpty();
        assertThat(loginResponse.getRefreshToken()).isNotEmpty();
        assertThat(loginResponse.getExpiredAt())
                .isAfter(LocalDateTime.now(ZoneId.of("UTC")))
                .isBefore(LocalDateTime.now(ZoneId.of("UTC")).plusHours(3));
    }

}
