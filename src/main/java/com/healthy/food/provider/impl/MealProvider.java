package com.healthy.food.provider.impl;

import com.healthy.food.model.Ingredient;
import com.healthy.food.model.Meal;
import com.healthy.food.provider.IMealProvider;
import com.healthy.food.repository.IIngredientRepository;
import com.healthy.food.repository.IMealRepository;
import com.healthy.food.util.HttpClientConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ComponentScan
public class MealProvider implements IMealProvider {
    private static final String API = "https://www.themealdb.com/api";

    @Autowired
    private IMealRepository mealRepository;
    @Autowired
    private IIngredientRepository ingredientRepository;

    @Override
    public Meal getMeal(String endpoint) {
        return HttpClientConnection.getModel(Meal.class, API, endpoint);
    }

    @Override
    public List<Meal> getAllMealsFilteredByIngredientID(Long ingredientID) {
        Ingredient ingredientFound = ingredientRepository.findById(ingredientID).get();
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getIngredients().contains(ingredientFound))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllMealsFilteredByFirstLetterPartialOrFullName(String firstLetter) {
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getName().startsWith(firstLetter))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllMealsFilteredByCategory(String category) {
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllMealsFilteredByArea(String area) {
        List<Meal> listOfMeals = (List<Meal>) mealRepository.findAll();
        return listOfMeals.stream()
                .filter(e -> e.getArea().equals(area))
                .collect(Collectors.toList());
    }
}
