package com.hua.cloud.controller;

import com.hua.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("user")
public class UserController {

  /**
   * 只用于 HTTP RestFul 协议的 请求类
   */
  @Autowired
  private RestTemplate restTemplate;

  private String httpRestfulUrl="http://localhost:8180";

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("selectOne")
  public ResponseEntity<User> selectOne(String id) {
    String url=httpRestfulUrl+"/user/selectOne?id="+id;
    return restTemplate.getForEntity(url,User.class);
  }
}
