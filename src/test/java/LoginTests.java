import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Логин пользователя")
public class LoginTests extends BaseTest {

    @Test
    @DisplayName("Успешная авторизация под существующим пользователем")
    public void authorizationExistUser() {

        userClient.checkCreateUserApi(user);
        Response response = userClient.checkLoginUserApi(existUser);
        response.then().statusCode(200);
        response.then().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Нельзя авторизоваться с некорректными логином и паролем")
    public void authorizationNotCorrectUser() {

        userClient.checkCreateUserApi(user);
        Response response = userClient.checkLoginUserApi(existNotCorrectUser);
        response.then().statusCode(401);
        response.then().body("message", equalTo("email or password are incorrect"));
    }

    @After
    public void cleanUp() {

        Response response = userClient.checkLoginUserApi(user);
        bearerToken = response.then().extract().jsonPath().getString("accessToken");

        if (bearerToken != null) {
            userClient.checkDeleteUserWithAuthApi(bearerToken);
        }
    }
}







