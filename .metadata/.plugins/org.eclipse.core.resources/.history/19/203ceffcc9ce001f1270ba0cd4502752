package TestCases;
    import io.restassured.RestAssured;
	import io.restassured.response.Response;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.concurrent.Callable;
	import java.util.concurrent.ExecutorService;
	import java.util.concurrent.Executors;
	import java.util.concurrent.Future;
public class TestCaseOne {
	//public class ApiTestWithMultithreading {

	    public static void main(String[] args) {
	        // Base URI for API
	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

	        // Create a thread pool
	        ExecutorService executorService = Executors.newFixedThreadPool(5);

	        // List to hold tasks
	        List<Callable<Response>> tasks = new ArrayList<>();

	        // Create tasks for concurrent API requests
	        for (int i = 1; i <= 10; i++) {
	            int userId = i;
	            tasks.add(() -> RestAssured
	                .given()
	                .pathParam("userId", userId)
	                .get("/posts/{userId}")
	                .then()
	                .extract()
	                .response());
	        }

	        try {
	            // Execute tasks and collect results
	            List<Future<Response>> results = executorService.invokeAll(tasks);

	            // Process the responses
	            for (Future<Response> result : results) {
	                Response response = result.get();
	                System.out.println("Response Code: " + response.getStatusCode());
	                System.out.println("Response Body: " + response.asString());
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Shutdown the executor service
	            executorService.shutdown();
	        }
	    }
	}


}
