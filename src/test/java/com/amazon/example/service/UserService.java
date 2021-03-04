/*
 * Copyright 2010-2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package com.amazon.example.service;

import com.amazon.example.domain.User;
import java.util.Collections;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserService extends AbstractService {

  @Inject
  DynamoDbClient dynamoDB;

  private final User user = new User("ID: xxxx", "apo", "Andrius", "Popliauskas", 41);

  public List<User> findAll() {
    return Collections.singletonList(user);
  }

  public String add(User user) {
    return "ID: xxxx";
  }

  public User get(String userId) {
    return user;
  }

  public String delete(String userId) {
    return "ID: xxxx";
  }
}
