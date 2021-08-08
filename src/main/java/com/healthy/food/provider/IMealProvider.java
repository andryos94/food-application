package com.healthy.food.provider;

import com.healthy.food.model.Meal;

import java.util.List;

public interface IMealProvider {
    Meal getMeal(String endpoint);

    List<Meal> getAllMealsFilteredByIngredientID(Long ingredientID);

    List<Meal> getAllMealsFilteredByFirstLetterPartialOrFullName(String firstLetter);

    List<Meal> getAllMealsFilteredByCategory(String category);

    List<Meal> getAllMealsFilteredByArea(String area);
}
