package com.healthy.food.provider.impl;

import com.healthy.food.provider.IDummyDataProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class DummyDataProvider implements IDummyDataProvider {
  // TODO: fetch all categories from API -> return List<Category>
}
