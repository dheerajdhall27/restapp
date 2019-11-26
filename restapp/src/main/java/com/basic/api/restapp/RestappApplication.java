package com.basic.api.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.basic.api.restapp"})
public class RestappApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestappApplication.class, args);
  }

}
