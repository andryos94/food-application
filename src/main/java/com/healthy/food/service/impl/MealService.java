package com.healthy.food.service.impl;

import com.healthy.food.model.Meal;
import com.healthy.food.provider.IMealProvider;
import com.healthy.food.service.IMealService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService implements IMealService {

  private final IMealProvider mealProvider;

  public MealService(IMealProvider mealProvider) {
    this.mealProvider = mealProvider;
  }

  @Override
  public List<Meal> getAllMealsFiltered(
      Long ingredientID, String firstLetter, String category, String area) {
    return mealProvider.getAllMealsFiltered(ingredientID, firstLetter, category, area);
  }

  // facade endpoint based on a request to the free api www.themealdb.com
  @Override
  public Meal getRandomMeal() {
    return mealProvider.getMeal();
  }
}
