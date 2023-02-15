package com.cse403.getitdone;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
                .queryParam("tid", "f7cceca9-764d-4c74-b0d0-bdc67cffd7d7")
                .encode()
                .toUriString();

        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("tid", "f7cceca9-764d-4c74-b0d0-bdc67cffd7d7");
        responseObj.addProperty("title", "do not remove test");
        responseObj.addProperty("dueDate", "2023-02-15T14:53");
        responseObj.addProperty("hoursToComplete", 2);
        responseObj.addProperty("isCompleted", false);

        assertThat(this.restTemplate.getForObject(urlTemplate,
                String.class))
                .contains(responseObj.toString());
    }

    @Test
    public void getTaskListSuccess() throws Exception {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getTaskList")
                .queryParam("uid", "aidan")
                .encode()
                .toUriString();

        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("tid", "f7cceca9-764d-4c74-b0d0-bdc67cffd7d7");
        responseObj.addProperty("title", "do not remove test");
        responseObj.addProperty("dueDate", "2023-02-15T14:53");
        responseObj.addProperty("hoursToComplete", 2);
        responseObj.addProperty("isCompleted", false);

        JsonObject responseObj2 = new JsonObject();
        responseObj2.addProperty("tid", "c3ec1646-274f-424f-942d-fe9bf553650d");
        responseObj2.addProperty("title", "do not remove test 2");
        responseObj2.addProperty("dueDate", "2023-02-15T19:06");
        responseObj2.addProperty("hoursToComplete", 5);
        responseObj2.addProperty("isCompleted", true);

        JsonObject responseObj3 = new JsonObject();
        responseObj3.addProperty("tid", "663e0db5-44ff-4bd7-9f6c-1c0d566fb09c");
        responseObj3.addProperty("title", "do not remove test 3");
        responseObj3.addProperty("dueDate", "2023-02-20T08:22");
        responseObj3.addProperty("hoursToComplete", 10);
        responseObj3.addProperty("isCompleted", false);

        assertThat(this.restTemplate.getForObject(urlTemplate,
                String.class))
                .contains(responseObj.toString())
                .contains(responseObj2.toString())
                .contains(responseObj3.toString());
    }

    @Test
    public void creatTaskSuccess() throws Exception {
//        final String baseUrl = "http://localhost:8080/createTask";
//        URI uri = new URI(baseUrl);
//        Task task = new Task("test add endpoint", "2024-01-01T10:30", 5);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-COM-PERSIST", "true");
//
//        HttpEntity<Task> request = new HttpEntity<>(task, headers);
//
//        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
//
////        Verify request succeed
//        assertEquals(201, result.getStatusCodeValue());
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