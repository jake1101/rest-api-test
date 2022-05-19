package com.restapitest.myrestapi.mapper;

import java.util.List;

import com.restapitest.myrestapi.model.UserProfile;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserProfileMapper {

  @Select("SELECT * FROM UserProfile WHERE id=#{id}")
  UserProfile getUserProfile(@Param("id") Integer id);

  @Select("SELECT * FROM UserProfile")
  List<UserProfile> getUserProfileList();

  @Insert("INSERT INTO UserProfile VALUES(NULL, #{name}, #{phone}, #{address})")
  int insertUserProfile(@Param("name") String name, @Param("phone") String phone,
      @Param("address") String address);

  @Update("UPDATE UserProfile SET #{key}=#{value} WHERE id=#{id}")
  int updateUserProfile(@Param("id") Integer id, @Param("key") String key, @Param("value") String value);

  @Delete("DELETE FROM UserProfile WHERE id=#{id}")
  int deleteUserProfile(@Param("id") Integer id);
}
