package com.healthy.food.service.impl;

import com.healthy.food.model.Meal;
import com.healthy.food.provider.IMealProvider;
import com.healthy.food.service.IMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService implements IMealService {

    private IMealProvider mealProvider;

    @Autowired
    public MealService(IMealProvider mealProvider) {
        this.mealProvider = mealProvider;
    }

    @Override
    public List<Meal> getAllMealsFiltered(Long ingredientID, String firstLetter, String category, String area) {
        if (ingredientID != null) {
            return mealProvider.getAllMealsFilteredByIngredientID(ingredientID);
        }

        if (firstLetter != null) {
            return mealProvider.getAllMealsFilteredByFirstLetterPartialOrFullName(firstLetter);
        }

        if (category != null) {
            return mealProvider.getAllMealsFilteredByCategory(category);
        }

        if (area != null) {
            return mealProvider.getAllMealsFilteredByArea(area);
        }
        return null;
    }

    //facade endpoint based on a request to the free api www.themealdb.com
    @Override
    public Meal getRandomMeal() {
        return mealProvider.getMeal("/json/v1/1/random.php");
    }

}
