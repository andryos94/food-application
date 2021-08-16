package com.healthy.food.api;

import com.healthy.food.model.Category;
import com.healthy.food.model.Meal;

import java.util.List;

public interface IMealDbApi {
  Meal getRandomMeal();
  List<Category> getAllCategoriesMeal();
}
