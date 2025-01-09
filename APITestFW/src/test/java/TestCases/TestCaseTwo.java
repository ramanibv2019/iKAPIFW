package TestCases;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.concurrent.CompletableFuture;
public class TestCaseTwo {
	
	
	 public static void main(String[] args) {
	        // Base URI for API
	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

	        // Simulate 10 concurrent API calls
	        for (int i = 1; i <= 10; i++) {
	            int userId = i;
	            CompletableFuture
	                .supplyAsync(() -> RestAssured
	                    .given()
	                    .pathParam("userId", userId)
	                    .get("/posts/{userId}")
	                    .then()
	                    .extract()
	                    .response())
	                .thenAccept(response -> {
	                    System.out.println("Response Code: " + response.getStatusCode());
	                    System.out.println("Response Body: " + response.asString());
	                });
	        }
	    }

}
