package com.restapitest.myrestapi.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import org.springframework.hateoas.RepresentationModel;

public class UserProfileModel extends RepresentationModel<UserProfileModel> {

  @JsonUnwrapped
  private final UserProfile user;

  public UserProfileModel(UserProfile user) {
    this.user = user;
  }

}
