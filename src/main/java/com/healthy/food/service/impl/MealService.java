package com.healthy.food.service.impl;

import com.healthy.food.model.Category;
import com.healthy.food.model.Meal;
import com.healthy.food.provider.IDummyDataProvider;
import com.healthy.food.provider.IMealProvider;
import com.healthy.food.service.IMealService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService implements IMealService {
  private final IDummyDataProvider dummyDataProvider;
  private final IMealProvider mealProvider;

  public MealService(IMealProvider mealProvider, IDummyDataProvider dummyDataProvider) {
    this.mealProvider = mealProvider;
    this.dummyDataProvider = dummyDataProvider;
  }

  @Override
  public List<Meal> getAllMealsFiltered(
      Long ingredientID, String firstLetter, String category, String area) throws Exception {
    return mealProvider.getAllMealsFiltered(ingredientID, firstLetter, category, area);
  }

  // facade endpoint based on a request to the free api www.themealdb.com
  @Override
  public Meal getRandomMeal() {
    return mealProvider.getMeal();
  }

  @Override
  public List<Category> getAllCategoriesMeal() {
    return dummyDataProvider.getAllCategoriesMeal();
  }
}
