package com.healthy.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.healthy.food.repository")
@ComponentScan({
        "com.healthy.food.api",
        "com.healthy.food.controller",
        "com.healthy.food.service",
        "com.healthy.food.provider"
})
public class FoodApplication {

  public static void main(String[] args) {
    SpringApplication.run(FoodApplication.class, args);
  }
}
