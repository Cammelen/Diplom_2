import api.client.OrdersClient;
import api.client.UserClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import org.junit.After;
import org.junit.Before;
import java.util.ArrayList;
import java.util.Random;

public class BaseTest {

    Random random = new Random();

    protected String bearerToken = "";
    private String email = "user" + random.nextInt(100000) + "@yandex.ru";
    private String password = "111";
    private String name = "Hero";
    protected String firstIngredient = "61c0c5a71d1f82001bdaaa6f";
    protected String secondIngredient = "61c0c5a71d1f82001bdaaa73";
    protected String notCorrectIngredient = "not correct";

    UserClient userClient = new UserClient();
    OrdersClient ordersClient = new OrdersClient();
    User user = new User(email, password, name);
    User existUser = new User(email, password);
    User existNotCorrectUser = new User("test" + email, "test" + password);
    User userWithoutEmail = new User("", password, name);
    ArrayList<String> ingredients = new ArrayList<>();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
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