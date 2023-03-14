package api.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    private String JSON = "application/json";
    private String bearerToken;

    protected Response doPostRequest(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .post(uri);
    }

    protected Response doPatchRequest(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .patch(uri);
    }

    protected Response doPatchRequestWithAuth(String uri, String bearerToken, Object body) {
        return given()
                .header("Content-Type", JSON)
                .auth().oauth2(bearerToken)
                .body(body)
                .patch(uri);
    }

    protected Response doDeleteRequestWithAuth(String uri, String bearerToken) {
        return given()
                .header("Content-Type", JSON)
                .auth().oauth2(bearerToken)
                .delete(uri);
    }

    protected Response doGetRequestWithAuth(String uri, String bearerToken) {
        return given()
                .header("Content-Type", JSON)
                .auth().oauth2(bearerToken)
                .get(uri);
    }

    protected Response doGetRequest(String uri) {
        return given()
                .header("Content-Type", JSON)
                .get(uri);
    }



}
