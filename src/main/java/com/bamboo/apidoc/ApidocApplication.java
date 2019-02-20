package com.bamboo.apidoc;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/test")
public class ApidocApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApidocApplication.class, args);
  }
}

