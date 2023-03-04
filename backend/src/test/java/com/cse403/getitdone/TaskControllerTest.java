package com.cse403.getitdone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cse403.getitdone.task.Task;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.OffsetDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String tid;


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
        responseObj.addProperty("description", "description here");
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
        responseObj.addProperty("description", "description here");
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
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/createTask")
                .queryParam("uid", "aidan")
                .encode()
                .toUriString();

        Task task = new Task("test add endpoint", "2024-01-01T10:30", "description here", 5);
        this.tid = task.getTid();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Task> request = new HttpEntity<>(task, headers);
        String response = restTemplate.postForObject(urlTemplate, task, String.class);

        String timestamp = OffsetDateTime.now().toString();

        // Response should return a timestamp with today's date and time.
        assertEquals(timestamp.substring(0, 10),  response.substring(0, 10));
    }

    @Test
    public void deleteTaskSuccess() throws Exception {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/deleteTask")
                .queryParam("uid", "aidan")
                .queryParam("tid", this.tid)
                .encode()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.DELETE, entity, String.class);

        assertEquals("200 OK" , response.getStatusCode());
        assertEquals("uid aidan: task with tid "+ tid +" has been deleted" , response.getBody());
    }

}