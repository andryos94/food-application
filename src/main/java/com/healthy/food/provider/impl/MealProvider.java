package com.healthy.food.provider.impl;

import com.healthy.food.api.IMealDbApi;
import com.healthy.food.model.Meal;
import com.healthy.food.provider.IMealProvider;
import com.healthy.food.repository.IIngredientRepository;
import com.healthy.food.repository.IMealRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealProvider implements IMealProvider {
  private final IMealRepository mealRepository;
  private final IIngredientRepository ingredientRepository;
  private final IMealDbApi mealDbApi;

  public MealProvider(
      IMealRepository mealRepository,
      IIngredientRepository ingredientRepository,
      IMealDbApi mealDbApi) {
    this.mealRepository = mealRepository;
    this.ingredientRepository = ingredientRepository;
    this.mealDbApi = mealDbApi;
  }

  @Override
  public Meal getMeal() {
    return mealDbApi.getRandomMeal();
  }

  private List<Meal> getAllMealsFilteredByIngredientID(Long ingredientID, List<Meal> meals) {
    final var ingredientFound = ingredientRepository.findById(ingredientID);
    return ingredientFound
        .map(
            ingredient ->
                meals.stream()
                    .filter(e -> e.getIngredients().contains(ingredient))
                    .collect(Collectors.toList()))
        .orElseGet(List::of);
  }

  private List<Meal> getAllMealsFilteredByFirstLetterPartialOrFullName(
      String firstLetter, List<Meal> meals) {
    return meals.stream()
        .filter(e -> e.getName().startsWith(firstLetter))
        .sorted()
        .collect(Collectors.toList());
  }

  private List<Meal> getAllMealsFilteredByCategory(String category, List<Meal> meals) {
    return meals.stream()
        .filter(e -> e.getCategory().equals(category))
        .collect(Collectors.toList());
  }

  private List<Meal> getAllMealsFilteredByArea(String area, List<Meal> meals) {
    return meals.stream().filter(e -> e.getArea().equals(area)).collect(Collectors.toList());
  }

  @Override
  public List<Meal> getAllMealsFiltered(
      Long ingredientID, String firstLetter, String category, String area) throws Exception {
    return filterMeals(ingredientID, firstLetter, category, area);
  }

  private List<Meal> filterMeals(
      Long ingredientID, String firstLetter, String category, String area) throws Exception {
    List<Meal> filteredMeals = new ArrayList<>();
    final var listOfMeals = mealRepository.findAll();

    validateParameters(ingredientID, firstLetter, category, area);

    if (ingredientID != null)
      filteredMeals.addAll(getAllMealsFilteredByIngredientID(ingredientID, listOfMeals));

    if (firstLetter != null)
      filteredMeals.addAll(
          getAllMealsFilteredByFirstLetterPartialOrFullName(firstLetter, listOfMeals));

    if (category != null)
      filteredMeals.addAll(getAllMealsFilteredByCategory(category, listOfMeals));

    if (area != null) filteredMeals.addAll(getAllMealsFilteredByArea(area, listOfMeals));

    return filteredMeals;
  }

  private void validateParameters(
      Long ingredientID, String firstLetter, String category, String area) throws Exception {
    if (ingredientID == null && firstLetter == null && category == null && area == null)
      throw new Exception(
          "At least one filtering parameter must be provided."); // TODO: create
                                                                 // InvalidFilteringRequestException.class
  }
}
