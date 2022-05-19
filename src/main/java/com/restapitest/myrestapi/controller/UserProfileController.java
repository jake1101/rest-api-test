package com.restapitest.myrestapi.controller;

import java.util.List;

import com.restapitest.myrestapi.mapper.UserProfileMapper;
import com.restapitest.myrestapi.model.UserProfile;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

  private UserProfileMapper mapper;

  public UserProfileController(UserProfileMapper mapper) {
    this.mapper = mapper;
  }

  @GetMapping("/user")
  public List<UserProfile> getUserProfileList() {
    return mapper.getUserProfileList();
  }

  @GetMapping("/user/{id}")
  public UserProfile getUserProfile(@PathVariable("id") String id) {
    return mapper.getUserProfile(id);
  }

  @PutMapping("/user/{id}")
  public void insertUserProfile(@PathVariable("id") String id, @RequestParam("name") String name,
      @RequestParam("phone") String phone, @RequestParam("address") String address) {
    mapper.insertUserProfile(id, name, phone, address);
  }

  @PostMapping("/user/{id}")
  public void updateUserProfile(@PathVariable("id") String id, @RequestParam("name") String name,
      @RequestParam("phone") String phone, @RequestParam("address") String address) {
    mapper.updateUserProfile(id, name, phone, address);
  }

  @DeleteMapping("/user/{id}")
  public void deleteUserProfile(@PathVariable("id") String id) {
    mapper.deleteUserProfile(id);
  }

}
