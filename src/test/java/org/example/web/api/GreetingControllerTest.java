package org.example.web.api;

import org.example.Application;
import org.example.model.Greeting;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerTest {
    @LocalServerPort
    private int port;

    private final TestRestTemplate trt = new TestRestTemplate();
    private final HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(final String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void test_GetAllGreetings() throws JSONException{
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = trt.exchange(
                createURLWithPort("/api/greetings"),
                HttpMethod.GET,
                entity,
                String.class);

        String expected = "[{id:1,text:\"Hello Digisoft!!\"},{id:2,text:\"Goodbye Digisoft!!\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void test_GetOneGreeting() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = trt.exchange(
                createURLWithPort("/api/greetings/1"),
                HttpMethod.GET,
                entity,
                String.class);

        String expected = "{id:1,text:\"Hello Digisoft!!\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void test_AddGreeting() throws JSONException {
        Greeting gr = new Greeting();
        gr.setText("What's up?");

        HttpEntity<Greeting> entity = new HttpEntity<Greeting>(gr, headers);

        ResponseEntity<String> response = trt.exchange(
                createURLWithPort("/api/greetings"),
                HttpMethod.POST,
                entity,
                String.class);

        String expected = "{id:3,text:\"What's up?\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);

        //must clean up after test
        response = trt.exchange(
                createURLWithPort("/api/greetings/3"),
                HttpMethod.DELETE,
                entity,
                String.class);
    }
}