package com.cse403.getitdone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cse403.getitdone.task.Task;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getTaskDetailsSuccess() throws Exception {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getTaskDetails")
                .queryParam("uid", "aidan")
                .queryParam("tid", "866ac858-0ec8-4d5e-9a0f-58262cc8302b")
                .encode()
                .toUriString();

        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("tid", "866ac858-0ec8-4d5e-9a0f-58262cc8302b");
        responseObj.addProperty("title", "do not remove");
        responseObj.add("dueDate", JsonNull.INSTANCE);
        responseObj.addProperty("hoursToComplete", 0);
        responseObj.addProperty("isCompleted", true);

        assertThat(this.restTemplate.getForObject(urlTemplate,
                String.class))
                .contains(responseObj.toString());
    }

    @Test
    public void createTaskSuccess() throws Exception {
        final String baseUrl = "http://localhost:8080/createTask?uid=aidan";
        URI uri = new URI(baseUrl);

        Task task = new Task("aidan", "title", 2024, 1, 1, 10, 30, 5);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Task> request = new HttpEntity<>(task, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        System.out.println(result);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void getTaskListSuccess() throws Exception {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getTaskList")
                .queryParam("uid", "aidan")
                .encode()
                .toUriString();

        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("tid", "866ac858-0ec8-4d5e-9a0f-58262cc8302b");
        responseObj.addProperty("title", "do not remove");
        responseObj.add("dueDate", JsonNull.INSTANCE);
        responseObj.addProperty("hoursToComplete", 0);
        responseObj.addProperty("isCompleted", true);

        JsonObject responseObj2 = new JsonObject();
        responseObj2.addProperty("tid", "e623885f-2f80-4284-894a-0f1234671cd6");
        responseObj2.addProperty("title", "do not remove 2");
        responseObj2.add("dueDate", JsonNull.INSTANCE);
        responseObj2.addProperty("hoursToComplete", 1);
        responseObj2.addProperty("isCompleted", false);

        JsonObject responseObj3 = new JsonObject();
        responseObj3.addProperty("tid", "9593d218-5fbe-4e35-b7bd-59eb0bbcfa8b");
        responseObj3.addProperty("title", "do not remove 3");
        responseObj3.add("dueDate", JsonNull.INSTANCE);
        responseObj3.addProperty("hoursToComplete", 43);
        responseObj3.addProperty("isCompleted", false);

        assertThat(this.restTemplate.getForObject(urlTemplate,
                String.class))
                .contains(responseObj.toString())
                .contains(responseObj2.toString())
                .contains(responseObj3.toString());
    }

    @Test
    public void deleteTaskSuccess() throws Exception {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/deleteTask")
                .queryParam("uid", "aidan")
                .queryParam("tid", "07828dfb-9593-4037-addf-6e9a6e962cb9")
                .encode()
                .toUriString();

        assertThat(this.restTemplate.getForObject(urlTemplate,
                String.class))
                .contains("uid aidan: task with tid 07828dfb-9593-4037-addf-6e9a6e962cb9 has been deleted");
    }

}