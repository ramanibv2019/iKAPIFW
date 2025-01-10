package ApiTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.concurrent.Callable;

public class ApiTestTask implements Callable<Response> {

	private final String endpoint;

    public ApiTestTask(String endpoint) {
        this.endpoint = endpoint;
    }

    public Response call() throws Exception {
        return RestAssured
                .given()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
}
