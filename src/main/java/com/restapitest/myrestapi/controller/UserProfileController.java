package com.restapitest.myrestapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restapitest.myrestapi.mapper.UserProfileMapper;
import com.restapitest.myrestapi.model.UserProfile;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

  private UserProfile user;

  private UserProfileMapper mapper;

  public UserProfileController(UserProfileMapper mapper) {
    this.mapper = mapper;
  }

  @GetMapping("/users")
  public List<UserProfile> getUserProfileList() {
    return mapper.getUserProfileList();
  }

  @GetMapping("/user/{id}")
  public UserProfile getUserProfile(@PathVariable("id") Integer id) {
    return mapper.getUserProfile(id);
  }

  @PostMapping("/user")
  public List<UserProfile> insertUserProfile(
      @RequestBody UserProfile user) {
    mapper.insertUserProfile(user.getName(), user.getPhone(), user.getAddress());
    return mapper.getUserProfileList();
  }

  @PutMapping("/user/{id}")
  public void updateUserProfile(@PathVariable("id") Integer id,
      @RequestBody HashMap<String, String> param) {
    String key = param.get("key");
    String value = param.get("value");

    mapper.updateUserProfile(id, key, value);
  }

  @DeleteMapping("/user/{id}")
  public void deleteUserProfile(@PathVariable("id") Integer id) {
    mapper.deleteUserProfile(id);
  }

}
