package com.healthy.food.services;

import com.healthy.food.entities.Ingredient;
import com.healthy.food.entities.Meal;
import com.healthy.food.providers.impl.MealProvider;
import com.healthy.food.repositories.IngredientRepository;
import com.healthy.food.repositories.MealRepository;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {

    private MealRepository mealRepository;
    private IngredientRepository ingredientRepository;

    private static HttpURLConnection connection;

    public List<Meal> getAllMealsFilteredByIngredientID(Long ingredientID) {
        Ingredient ingredientFound = ingredientRepository.findById(ingredientID).get();
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getIngredients().contains(ingredientFound))
                .collect(Collectors.toList());
    }

    public List<Meal> getAllMealsFilteredByFirstLetterPartialOrFullName(String firstLetter) {
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getName().contains(firstLetter))
                .collect(Collectors.toList());
    }

    public List<Meal> getAllMealsFilteredByCategory(String category) {
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<Meal> getAllMealsFilteredByArea(String area) {
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getArea().equals(area))
                .collect(Collectors.toList());
    }


    //facade endpoint based on a request to the free api www.themealdb.com
    public Meal getRandomMeal() {
        MealProvider mealProvider = new MealProvider();
        return mealProvider.getMeal("/json/v1/1/random.php");
    }

}
