import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Ingredients;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Создание заказов")
public class CreateOrderTests extends BaseTest {

    @Test
    @DisplayName("Создание заказа без авторизации с ингредиентами")
    public void createOrderWithoutAuthorization() {

        ingredients.add(firstIngredient);
        ingredients.add(secondIngredient);
        userClient.checkCreateUserApi(user);
        Response response = ordersClient.checkCreateOrderApi(new Ingredients(ingredients));
        response.then().statusCode(200);
        response.then().body("name", equalTo("Space бессмертный бургер"));
    }

    @Test
    @DisplayName("Создание заказа с авторизацией и ингредиентами")
    public void createOrderWithAuthorization() {

        ingredients.add(firstIngredient);
        ingredients.add(secondIngredient);
        userClient.checkCreateUserApi(user);
        userClient.checkLoginUserApi(existUser);
        Response response = ordersClient.checkCreateOrderApi(new Ingredients(ingredients));
        response.then().statusCode(200);
        response.then().body("name", equalTo("Space бессмертный бургер"));
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredients() {

        userClient.checkCreateUserApi(user);
        userClient.checkLoginUserApi(existUser);
        Response response = ordersClient.checkCreateOrderApi(new Ingredients(ingredients));
        response.then().statusCode(400);
        response.then().body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с невалидным ингредиентом")
    public void createOrderWithNotCorrectIngredients() {

        ingredients.add(notCorrectIngredient);
        userClient.checkCreateUserApi(user);
        userClient.checkLoginUserApi(existUser);
        Response response = ordersClient.checkCreateOrderApi(new Ingredients(ingredients));
        response.then().statusCode(500);
    }
}
