package com.healthy.food.service.impl;

import com.healthy.food.api.IMealDbApi;
import com.healthy.food.model.Ingredient;
import com.healthy.food.model.Meal;
import com.healthy.food.provider.impl.MealProvider;
import com.healthy.food.repository.IIngredientRepository;
import com.healthy.food.repository.IMealRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MealServiceTest {

  @Test
  void getAllMealsFilteredByIngredient() {
    IIngredientRepository ingredientRepository = mock(IIngredientRepository.class);
    IMealRepository mealRepository = mock(IMealRepository.class);
    IMealDbApi mealDbApi = mock(IMealDbApi.class);

    Ingredient ingredientToFind = new Ingredient();
    ingredientToFind.setId(2222);
    ingredientToFind.setName("curry");

    Ingredient ingr1 = new Ingredient();
    ingr1.setId(1111);
    ingr1.setName("piper");
    Ingredient ingr2 = new Ingredient();
    ingr2.setId(2222);
    ingr2.setName("curry2");

    List<Ingredient> allIngredientsMeal1 = new ArrayList<>();
    allIngredientsMeal1.add(ingredientToFind);
    allIngredientsMeal1.add(ingr1);

    List<Ingredient> allIngredientsMeal2 = new ArrayList<>();
    allIngredientsMeal2.add(ingr2);
    allIngredientsMeal2.add(ingr1);

    Meal meal1 = new Meal();
    meal1.setIngredients(allIngredientsMeal1);

    Meal meal2 = new Meal();
    meal2.setIngredients(allIngredientsMeal2);

    List<Meal> listOfMeals = new ArrayList<>();
    listOfMeals.add(meal1);
    listOfMeals.add(meal2);

    when(ingredientRepository.findById(any())).thenReturn(java.util.Optional.of(ingredientToFind));
    when(mealRepository.findAll()).thenReturn(listOfMeals);

    MealProvider mealProvider = new MealProvider(mealRepository, ingredientRepository, mealDbApi);
    MealService mealService = new MealService(mealProvider);

    assertEquals(List.of(meal1), mealService.getAllMealsFiltered(2222L, null, null, null));
  }

  @Test
  void getAllMealsFilteredAlphabetical() {}

  @Test
  void getAllMealsFilteredByCategory() {}

  @Test
  void getAllMealsFilteredByArea() {}

  @Test
  void getRandomMeal() {}
}
