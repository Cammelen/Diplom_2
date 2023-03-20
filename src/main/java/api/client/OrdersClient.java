package api.client;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class OrdersClient extends BaseApiClient {

    private final String CREATE_ORDER = "/api/orders";

    @Step("Создание заказа")
    public Response checkCreateOrderApi(Object body) {
        return doPostRequest(CREATE_ORDER, body);
    }

    @Step("Получение заказов с авторизацией")
    public Response checkGetOrdersWithAuthApi(String bearerToken) {
        return doGetRequestWithAuth(CREATE_ORDER, bearerToken);
    }

    @Step("Получение заказов без авторизацией")
    public Response checkGetOrdersApi() {
        return doGetRequest(CREATE_ORDER);
    }
}