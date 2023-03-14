package api.client;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserClient extends BaseApiClient {

    final String REGISTER_USER = "/api/auth/register";
    final String LOGIN_USER = "/api/auth/login";
    final String CHANGE_USER = "/api/auth/user";

    @Step("Создание пользователя")
    public Response checkCreateUserApi(Object body) {
        return doPostRequest(REGISTER_USER, body);
    }

    @Step("Авторизация пользователя")
    public Response checkLoginUserApi(Object body) {
        return doPostRequest(LOGIN_USER, body);
    }

    @Step("Изменение пользователя")
    public Response checkChangeUserApi(Object body) {
        return doPatchRequest(CHANGE_USER, body);
    }

    @Step("Изменение пользователя с авторизацией")
    public Response checkChangeUserWithAuthApi(String bearerToken, Object body) {
        return doPatchRequestWithAuth(CHANGE_USER, bearerToken, body);
    }

    @Step("Изменение пользователя с авторизацией")
    public Response checkDeleteUserWithAuthApi(String bearerToken) {
        return doDeleteRequestWithAuth(CHANGE_USER, bearerToken);
    }
}
