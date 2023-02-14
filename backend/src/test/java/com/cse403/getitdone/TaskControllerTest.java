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
                .queryParam("tid", "a7755165-c8f9-44e2-bd83-fdf4b075e214")
                .encode()
                .toUriString();

        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("tid", "a7755165-c8f9-44e2-bd83-fdf4b075e214");
        responseObj.addProperty("title", "first Task");
        responseObj.add("dueDate", JsonNull.INSTANCE);
        responseObj.addProperty("hoursToComplete", 1);
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
        responseObj.addProperty("tid", "a7755165-c8f9-44e2-bd83-fdf4b075e214");
        responseObj.addProperty("title", "first Task");
        responseObj.add("dueDate", JsonNull.INSTANCE);
        responseObj.addProperty("hoursToComplete", 1);
        responseObj.addProperty("isCompleted", false);

        JsonObject responseObj2 = new JsonObject();
        responseObj2.addProperty("tid", "fe42245d-603a-4743-a44b-9b8824c05c1d");
        responseObj2.addProperty("title", "not target two");
        responseObj2.add("dueDate", JsonNull.INSTANCE);
        responseObj2.addProperty("hoursToComplete", 223);
        responseObj2.addProperty("isCompleted", false);

        JsonObject responseObj3 = new JsonObject();
        responseObj3.addProperty("tid", "33de9358-d276-456a-a5ec-66f0beba2c1f");
        responseObj3.addProperty("title", "sixth");
        responseObj3.add("dueDate", JsonNull.INSTANCE);
        responseObj3.addProperty("hoursToComplete", 6);
        responseObj3.addProperty("isCompleted", true);

        assertThat(this.restTemplate.getForObject(urlTemplate,
                String.class))
                .contains(responseObj.toString())
                .contains(responseObj2.toString())
                .contains(responseObj3.toString());
    }

}