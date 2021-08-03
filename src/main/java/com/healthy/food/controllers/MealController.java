package com.healthy.food.controllers;

import com.healthy.food.entities.Meal;
import com.healthy.food.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping("/random")
    public Meal getRandomMeal() {
        return mealService.getRandomMeal();
    }

    @GetMapping("/search")
    public List<Meal> getAllMealsFiltered(@RequestParam(value="ingredient", required = false) Long ingredientID,
                                          @RequestParam(value = "firstLetter", required = false) String firstLetter,
                                          @RequestParam(value = "category", required = false) String category,
                                          @RequestParam(value = "area", required = false) String area) {
        if (ingredientID != null) {
            return mealService.getAllMealsFilteredByIngredientID(ingredientID);
        }

        if (firstLetter != null) {
            return mealService.getAllMealsFilteredByFirstLetterPartialOrFullName(firstLetter);
        }

        if (category != null) {
            return mealService.getAllMealsFilteredByCategory(category);
        }

        if (area != null) {
            return mealService.getAllMealsFilteredByArea(area);
        }
        return null;
    }

}
