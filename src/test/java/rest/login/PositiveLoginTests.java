package rest.login;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rest.BaseTest;
import rest.enums.Users;
import rest.pojos.login.LoginRequest;
import rest.pojos.login.LoginResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Login")
@Story("Check login with correct fields")
public class PositiveLoginTests extends BaseTest {


    @DataProvider
    public Object[][] getUsers() {
        return new Object[][]{
                {Users.GOOD_USER},
                {Users.JUST_CHANGED_PASSWORD_USER_WITH_NEW_PASSWORD},
        };
    }

    @Test(dataProvider = "getUsers")
    public void checkLoginWithCorrectFields(Users user) {

        Response response = api.login
                .loginAs(new LoginRequest(user.getLogin(), user.getPassword()));

        assertThat(response)
                .isNotNull()
                .extracting(ResponseOptions::statusCode).isEqualTo(200);

        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertThat(loginResponse.getLogin()).isEqualTo(user.getLogin());
        assertThat(loginResponse.getToken()).isNotEmpty();
        assertThat(loginResponse.getRefreshToken()).isNotEmpty();
        assertThat(loginResponse.getExpiredAt())
                .isAfter(LocalDateTime.now(ZoneId.of("UTC")))
                .isBefore(LocalDateTime.now(ZoneId.of("UTC")).plusHours(3));
    }

}
