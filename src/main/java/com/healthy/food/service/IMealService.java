package com.healthy.food.service;

import com.healthy.food.model.Category;
import com.healthy.food.model.Meal;

import java.util.List;

public interface IMealService {
    List<Meal> getAllMealsFiltered(Long ingredientID, String firstLetter, String category, String area);

    //facade endpoint based on a request to the free api www.themealdb.com
    Meal getRandomMeal();

    List<Category> getAllCategoriesMeal();
}
