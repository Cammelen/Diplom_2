import api.client.OrdersClient;
import api.client.UserClient;
import io.restassured.RestAssured;
import model.User;
import org.junit.Before;
import java.util.ArrayList;
import java.util.Random;

public class BaseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    UserClient userClient = new UserClient();
    OrdersClient ordersClient = new OrdersClient();

    String bearerToken = "";

    Random random = new Random();

    String email = "user" + random.nextInt(100000) + "@yandex.ru";
    String password = "111";
    String name = "Hero";

    User user = new User(email, password, name);
    User existUser = new User(email, password);
    User existNotCorrectUser = new User("test" + email, "test" + password);
    User userWithoutEmail = new User("", password, name);

    ArrayList<String> ingredients = new ArrayList<>();
    String firstIngredient = "61c0c5a71d1f82001bdaaa6f";
    String secondIngredient = "61c0c5a71d1f82001bdaaa73";
    String notCorrectIngredient = "not correct";
}
