package rest.login;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rest.BaseTest;
import rest.enums.Users;
import rest.pojos.login.LoginErrorResponse;
import rest.pojos.login.LoginRequest;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Login")
@Story("Check login with incorrect fields")
public class NegativeLoginTests extends BaseTest {

    @DataProvider
    public Object[][] getUsers() {
        return new Object[][]{
                {Users.NON_EXISTENT_USER, 401, "Incorrect username or password",
                        "Ensure that the username and password included in the request are correct"},
                {Users.JUST_DELETED_USER, 401, "Incorrect username or password",
                        "Ensure that the username and password included in the request are correct"},
                {Users.JUST_CHANGED_PASSWORD_USER_WITH_OLD_PASSWORD, 401, "Incorrect username or password",
                        "Ensure that the username and password included in the request are correct"},
                {Users.USER_WITH_EMPTY_LOGIN, 400, "Bad request",
                        "Login should not be empty"},
                {Users.USER_WITH_EMPTY_PASSWORD, 400, "Bad request",
                        "Password should not be empty"},
                {Users.EXISTENT_USER_LOGIN_IN_UPPERCASE, 401, "Incorrect username or password",
                        "Ensure that the username and password included in the request are correct"},
                {Users.EXISTENT_USER_LOGIN_BUT_PASSWORD_IN_UPPERCASE, 401, "Incorrect username or password",
                        "Ensure that the username and password included in the request are correct"},
                {Users.LOGIN_WITH_BAD_SYMBOLS, 401, "Incorrect username or password",
                        "Ensure that the username and password included in the request are correct"},
                {Users.PASSWORD_WITH_BAD_SYMBOLS, 401, "Incorrect username or password",
                        "Ensure that the username and password included in the request are correct"},
                {Users.LOGIN_WITH_SPACE, 401, "Incorrect username or password",
                        "Ensure that the username and password included in the request are correct"},
                {Users.ADMIN, 401, "Incorrect username or password",
                        "Ensure that the username and password included in the request are correct"},
                {Users.SQL_INJECTION, 401, "Incorrect username or password",
                        "Ensure that the username and password included in the request are correct"},

        };
    }

    @Test(dataProvider = "getUsers")
    public void checkLoginWithInCorrectFields(Users user, int statusCode, String error, String message) {

        Response response = api.login
                .loginAs(new LoginRequest(user.getLogin(), user.getPassword()));

        assertThat(response)
                .isNotNull()
                .extracting(ResponseOptions::statusCode).isEqualTo(statusCode);

        LoginErrorResponse loginResponse = response.as(LoginErrorResponse.class);
        assertThat(loginResponse.getError()).isEqualTo(error);
        assertThat(loginResponse.getMessage()).isEqualTo(message);

        assertThat(loginResponse.getTimestamp())
                .isAfter(LocalDateTime.now(ZoneId.of("UTC")))
                .isBefore(LocalDateTime.now(ZoneId.of("UTC")).plusSeconds(1));
    }

    @Test
    public void checkLoginWithoutLoginField() {

        Response response = api.login
                .loginAs(null, "password");

        assertThat(response)
                .isNotNull()
                .extracting(ResponseOptions::statusCode).isEqualTo(400);

        LoginErrorResponse loginResponse = response.as(LoginErrorResponse.class);

        assertThat(loginResponse.getError()).isEqualTo("Bad request");
        assertThat(loginResponse.getMessage()).isEqualTo("Login should not be empty");

        assertThat(loginResponse.getTimestamp())
                .isAfter(LocalDateTime.now(ZoneId.of("UTC")))
                .isBefore(LocalDateTime.now(ZoneId.of("UTC")).plusSeconds(1));
    }

    @Test
    public void checkLoginWithoutPasswordField() {

        Response response = api.login
                .loginAs("login", null);

        assertThat(response)
                .isNotNull()
                .extracting(ResponseOptions::statusCode).isEqualTo(400);

        LoginErrorResponse loginResponse = response.as(LoginErrorResponse.class);

        assertThat(loginResponse.getError()).isEqualTo("Bad request");
        assertThat(loginResponse.getMessage()).isEqualTo("Password should not be empty");

        assertThat(loginResponse.getTimestamp())
                .isAfter(LocalDateTime.now(ZoneId.of("UTC")))
                .isBefore(LocalDateTime.now(ZoneId.of("UTC")).plusSeconds(1));
    }

}
