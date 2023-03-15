import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Создание пользователя")
public class CreateUserTests extends BaseTest {

    @Test
    @DisplayName("Успешное создание пользователя")
    public void createUser() {

        Response response = userClient.checkCreateUserApi(user);
        response.then().statusCode(200);
        response.then().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых пользователей")
    public void createExistUser() {

        userClient.checkCreateUserApi(user);
        Response response = userClient.checkCreateUserApi(user);
        response.then().statusCode(403);
        response.then().assertThat().body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Нельзя создать пользователя без электронной почты")
    public void createUserWithoutEmail() {

        Response response = userClient.checkCreateUserApi(userWithoutEmail);
        response.then().statusCode(403);
        response.then().assertThat().body("message", equalTo("Email, password and name are required fields"));
    }
}

