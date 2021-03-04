package com.amazon.example.service;

import com.amazon.example.domain.User;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService extends AbstractService {

  @Inject
  DynamoDbClient dynamoDB;

  public List<User> findAll() {
    return dynamoDB.scanPaginator(scanRequest()).items().stream()
        .map(User::from)
        .collect(Collectors.toList());
  }

  public String add(User user) {
    dynamoDB.putItem(putRequest(user));

    return user.getUserId();
  }

  public User get(String userId) {
    return User.from(dynamoDB.getItem(getRequest(userId)).item());
  }

  public String delete(String userId) {
    dynamoDB.deleteItem(deleteRequest(userId));

    return userId;
  }
}
