package TestCases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import ApiTestSetup.ApiTestTask;
import TestUtil.ThreadManager;

import java.util.ArrayList;
import java.util.List;
public class TestCaseThree {
	@Test
    public void testApiInParallel() {
        String baseUri = "https://jsonplaceholder.typicode.com";
        List<ApiTestTask> tasks = new ArrayList<>();

        // Create tasks for different endpoints
        for (int i = 1; i <= 10; i++) {
            tasks.add(new ApiTestSetup.ApiTestTask(baseUri + "/posts/" + i));
        }

        // Execute tasks in 5 threads
        List<Response> responses = ThreadManager.executeInThreads(tasks, 5);

        // Assert and process responses
        for (Response response : responses) {
            Assert.assertEquals(response.getStatusCode(), 200, "API returned unexpected status code!");
            System.out.println("Response: " + response.asString());
        }
    }

}
