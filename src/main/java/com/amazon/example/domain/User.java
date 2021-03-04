package com.amazon.example.domain;

import com.amazon.example.service.AbstractService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.Objects;

@RegisterForReflection
public class User {

  private final String userId;
  private final String userName;
  private final String firstName;
  private final String lastName;
  private final int age;

  @JsonCreator
  public User(
      @JsonProperty("userId")    String userId,
      @JsonProperty("userName")  String userName,
      @JsonProperty("firstName") String firstName,
      @JsonProperty("lastName")  String lastName,
      @JsonProperty("age")       int    age
  ) {
    this.userId = userId;
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public User withUserId(String userId) {
    return new User(userId, userName, firstName, lastName, age);
  }

  public static User from(Map<String, AttributeValue> item) {
    if (item == null || item.isEmpty()) {
      throw new NullPointerException("No request body or body is empty");
    }

    return new User(
        item.get(AbstractService.USER_ID_COL).s(),
        item.get(AbstractService.USER_USERNAME_COL).s(),
        item.get(AbstractService.USER_FIRSTNAME_COL).s(),
        item.get(AbstractService.USER_LASTNAME_COL).s(),
        Integer.parseInt(item.get(AbstractService.USER_AGE_COL).n())
    );
  }

  public String getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return age == user.age
        && Objects.equals(userId, user.userId)
        && Objects.equals(userName, user.userName)
        && Objects.equals(firstName, user.firstName)
        && Objects.equals(lastName, user.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, userName, firstName, lastName, age);
  }

  @Override
  public String toString() {
    return "User{"
        + "userId='" + userId + '\''
        + ", userName='" + userName + '\''
        + ", firstName='" + firstName + '\''
        + ", lastName='" + lastName + '\''
        + ", age=" + age
        + '}';
  }
}
