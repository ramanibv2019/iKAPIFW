package TestUtil;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ApiTestSetup.ApiTestTask;

public class ThreadManager {
	public static List<Response> executeInThreads(List<ApiTestTask> tasks, int threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Response> responses = new ArrayList<>();

        try {
            List<Future<Response>> futures = executorService.invokeAll(tasks);

            for (Future<Response> future : futures) {
                responses.add(future.get()); // Blocking until result is available
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        return responses;
    }
}
