package com.healthy.food.controller;

import com.healthy.food.model.Meal;
import com.healthy.food.service.IMealService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealController {

  private final IMealService mealService;

  public MealController(IMealService mealService) {
    this.mealService = mealService;
  }

  // TODO: add categories controller

  @GetMapping("/random")
  public Meal getRandomMeal() {
    return mealService.getRandomMeal();
  }

  @GetMapping("/search")
  public List<Meal> getAllMealsFiltered(
      @RequestParam(value = "ingredient", required = false) Long ingredientID,
      @RequestParam(value = "firstLetter", required = false) String firstLetter,
      @RequestParam(value = "category", required = false) String category,
      @RequestParam(value = "area", required = false) String area) {
    return mealService.getAllMealsFiltered(ingredientID, firstLetter, category, area);
  }
}
