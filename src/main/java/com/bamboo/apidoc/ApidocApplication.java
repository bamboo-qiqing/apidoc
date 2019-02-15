package com.bamboo.apidoc;


import com.bamboo.apidoc.annotation.Apidoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApidocApplication {
  /**
   * 你好
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(ApidocApplication.class, args);
  }

  @Apidoc
  @GetMapping
  public void as(){

  }
}

