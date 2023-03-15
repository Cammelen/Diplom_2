import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Ingredients;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Получение заказов")
public class GetOrdersTests extends BaseTest {

    @Test
    @DisplayName("Получение заказов с авторизацией")
    public void getOrderWithAuthorization() {

        ingredients.add(firstIngredient);
        ingredients.add(secondIngredient);
        Response tokens = userClient.checkCreateUserApi(user);
        bearerToken = tokens.then().extract().jsonPath().getString("accessToken").substring(7);
        ordersClient.checkCreateOrderApi(new Ingredients(ingredients));
        ordersClient.checkCreateOrderApi(new Ingredients(ingredients));
        Response response = ordersClient.checkGetOrdersWithAuthApi(bearerToken);
        response.then().statusCode(200);
    }

    @Test
    @DisplayName("Получение заказов без авторизации")
    public void getOrderWithoutAuthorization() {

        ingredients.add(firstIngredient);
        ingredients.add(secondIngredient);
        ordersClient.checkCreateOrderApi(new Ingredients(ingredients));
        ordersClient.checkCreateOrderApi(new Ingredients(ingredients));
        Response response = ordersClient.checkGetOrdersApi();
        response.then().statusCode(401);
        response.then().body("message", equalTo("You should be authorised"));
    }
}
