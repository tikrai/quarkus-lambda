package com.amazon.example;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.quarkus.amazon.lambda.test.LambdaClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProcessingLambdaTest {

  @Test
  public void test() {
    APIGatewayProxyRequestEvent request =
        new APIGatewayProxyRequestEvent()
            .withHttpMethod("GET");

    APIGatewayProxyResponseEvent response =
        LambdaClient.invoke(APIGatewayProxyResponseEvent.class, request);
    Assertions.assertEquals("Test response", response.getBody());
  }

  @Test
  public void testOther() {
    APIGatewayProxyRequestEvent request =
        new APIGatewayProxyRequestEvent()
            .withHttpMethod("GET");

    APIGatewayProxyResponseEvent response =
        LambdaClient.invoke(APIGatewayProxyResponseEvent.class, request);
    Assertions.assertEquals("Test response", response.getBody());
  }
}
