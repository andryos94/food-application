package com.healthy.food.provider;

import com.healthy.food.model.Meal;

import java.util.List;

public interface IMealProvider {
  Meal getMeal();

  List<Meal> getAllMealsFiltered(
      Long ingredientID, String firstLetter, String category, String area);
}
