package com.healthy.food.api;

import com.healthy.food.model.Meal;

public interface IMealDbApi {
  Meal getRandomMeal();
  // TODO: get all categories
}
