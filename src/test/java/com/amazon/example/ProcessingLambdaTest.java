package com.amazon.example;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import com.amazon.example.domain.User;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.amazon.lambda.test.LambdaClient;
import io.quarkus.test.junit.QuarkusTest;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProcessingLambdaTest {

  private final User user = new User("ID: xxxx", "apo", "Andrius", "Popliauskas", 41);
  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  public void shouldGetAllMockedUsers() throws JsonProcessingException {
    APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent().withHttpMethod("GET");

    APIGatewayProxyResponseEvent response =
        LambdaClient.invoke(APIGatewayProxyResponseEvent.class, request);

    assertThat(Arrays.asList(mapper.readValue(response.getBody(), User[].class)), contains(user));
  }

  @Test
  public void shouldGetMockedUserById() throws JsonProcessingException {
    APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent()
            .withHttpMethod("GET")
            .withPathParameters(Map.of("userId", "xx"));

    APIGatewayProxyResponseEvent response =
        LambdaClient.invoke(APIGatewayProxyResponseEvent.class, request);

    assertThat(mapper.readValue(response.getBody(), User.class), equalTo(user));
  }

  @Test
  public void shouldAddMockedUser() throws JsonProcessingException {
    APIGatewayProxyRequestEvent request =
        new APIGatewayProxyRequestEvent()
            .withHttpMethod("POST")
            .withBody(mapper.writeValueAsString(user));

    APIGatewayProxyResponseEvent response =
        LambdaClient.invoke(APIGatewayProxyResponseEvent.class, request);

    assertThat(response.getBody(), equalTo(user.getUserId()));
  }

  @Test
  public void shouldDeleteMockedUser() {
    APIGatewayProxyRequestEvent request =
        new APIGatewayProxyRequestEvent()
            .withHttpMethod("DELETE")
            .withPathParameters(Map.of("userId", "xx"));

    APIGatewayProxyResponseEvent response =
        LambdaClient.invoke(APIGatewayProxyResponseEvent.class, request);

    assertThat(response.getBody(), equalTo(user.getUserId()));
  }
}
