package com.restapitest.myrestapi.controller;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.restapitest.myrestapi.mapper.UserProfileMapper;
import com.restapitest.myrestapi.model.UserProfile;
import com.restapitest.myrestapi.model.UserProfileModel;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class UserProfileController {

  private UserProfileMapper mapper;

  public UserProfileController(UserProfileMapper mapper) {
    this.mapper = mapper;
  }

  private Map<Integer, UserProfile> db = new IdentityHashMap<>();
  private Integer id = 1;

  @PostMapping(value = "/api/user", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity createUserProfile(@RequestBody UserProfile user) {
    user.setId(id++);

    /*
     * /api/member
     */
    WebMvcLinkBuilder listLink = linkTo(UserProfileController.class);

    /*
     * /api/member/{id}
     */
    WebMvcLinkBuilder selfLink = listLink.slash(user.getId());

    // hateoas model 객체 생성
    UserProfileModel userProfileModel = new UserProfileModel(user);

    // list link
    userProfileModel.add(listLink.withRel("list"));

    // self link
    userProfileModel.add(selfLink.withSelfRel());

    // update link
    userProfileModel.add(selfLink.withRel("update"));

    return ResponseEntity.created(selfLink.toUri()).body(userProfileModel);
  }

  @GetMapping("/users")
  public Map<String, List<UserProfile>> getUserProfileList(final HttpServletResponse respons) {
    respons.addHeader("Cache-Control", "max-age=300");
    Map<String, List<UserProfile>> map = new HashMap<>();
    map.put("response", mapper.getUserProfileList());

    return map;
  }

  @GetMapping("/user/{id}")
  public UserProfile getUserProfile(@PathVariable("id") Integer id) {
    return mapper.getUserProfile(id);
  }

  @PostMapping("/user")
  public Map<String, List<UserProfile>> insertUserProfile(
      @RequestBody UserProfile user) {
    mapper.insertUserProfile(user.getName(), user.getPhone(), user.getAddress());

    Map<String, List<UserProfile>> map = new HashMap<>();
    map.put("response", mapper.getUserProfileList());
    return map;
  }

  @PutMapping("/user/{id}")
  public Map<String, UserProfile> updateUserProfile(@PathVariable("id") Integer id,
      @RequestBody HashMap<String, String> param) {

    UserProfile data = mapper.getUserProfile(id);
    if (param.get("name") != null)
      data.setName(param.get("name"));
    if (param.get("phone") != null)
      data.setPhone(param.get("phone"));
    if (param.get("address") != null)
      data.setAddress(param.get("address"));

    mapper.updateUserProfile(id, data.getName(), data.getPhone(), data.getAddress());

    Map<String, UserProfile> map = new HashMap<>();
    map.put("response", mapper.getUserProfile(id));
    return map;
  }

  @DeleteMapping("/user/{id}")
  public Map<String, List<UserProfile>> deleteUserProfile(@PathVariable("id") Integer id) {
    mapper.deleteUserProfile(id);

    Map<String, List<UserProfile>> map = new HashMap<>();
    map.put("response", mapper.getUserProfileList());
    return map;
  }

}
