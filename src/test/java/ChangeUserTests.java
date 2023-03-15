import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Изменение данных пользователя")
public class ChangeUserTests extends BaseTest {

    @Test
    @DisplayName("Изменение данных пользователя с авторизацией")
    public void changeUserWithAuthorisation() {

        Response tokens = userClient.checkCreateUserApi(user);
        bearerToken = tokens.then().extract().jsonPath().getString("accessToken").substring(7);
        user.setEmail("change" + user.getEmail());
        user.setPassword("change" + user.getPassword());
        userClient.checkLoginUserApi(user);
        Response response= userClient.checkChangeUserWithAuthApi(bearerToken, user);
        response.then().statusCode(200);
        response.then().body("success", equalTo(true));
    }

        @Test
        @DisplayName("Изменение данных пользователя без авторизации с возвратом ошибки")
        public void changeUserWithoutAuthorization() {

            Response tokens = userClient.checkCreateUserApi(user);
            bearerToken = tokens.then().extract().jsonPath().getString("accessToken");
            user.setName("change" + user.getName());
            Response response = userClient.checkChangeUserApi(user);
            response.then().statusCode(401);
            response.then().body("message", equalTo("You should be authorised"));
    }
}
