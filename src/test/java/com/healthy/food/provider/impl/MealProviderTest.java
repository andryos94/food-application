package com.healthy.food.provider.impl;

import com.healthy.food.api.IMealDbApi;
import com.healthy.food.model.Ingredient;
import com.healthy.food.model.Meal;
import com.healthy.food.repository.IIngredientRepository;
import com.healthy.food.repository.IMealRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MealProviderTest {
  private MealProvider mealProvider;

  private final IMealRepository mealRepository = mock(IMealRepository.class);
  private final IIngredientRepository ingredientRepository = mock(IIngredientRepository.class);
  private final IMealDbApi mealDbApi = mock(IMealDbApi.class);

  @BeforeEach
  void setUp() {
    this.mealProvider = new MealProvider(mealRepository, ingredientRepository, mealDbApi);
  }

  @SneakyThrows
  @Test
  @DisplayName("Given an ingredient based sorting, must return meals containing that ingredient.")
  void getAllMealsFiltered() {
    Ingredient ingr1 = new Ingredient();
    ingr1.setId(1111);
    ingr1.setName("piper");
    Ingredient ingr2 = new Ingredient();
    ingr2.setId(2222);
    ingr2.setName("curry");
    Ingredient ingr3 = new Ingredient();
    ingr3.setId(3333);
    ingr3.setName("salt");

    var allIngredients = List.of(ingr2, ingr1);

    Meal meal1 = new Meal();
    meal1.setIngredients(allIngredients);

    Meal meal2 = new Meal();
    meal2.setIngredients(allIngredients);

    Meal meal3 = new Meal();
    meal3.setIngredients(List.of(ingr3));

    List<Meal> listOfMeals = List.of(meal1, meal2, meal3);

    when(ingredientRepository.findById(any())).thenReturn(Optional.of(ingr2));
    when(mealRepository.findAll()).thenReturn(listOfMeals);

    final var result = mealProvider.getAllMealsFiltered(2222L, null, null, null);

    assertEquals(2, result.size());
    for (Meal meal : result) {
      final var ingredients = meal.getIngredients();

      assertTrue(ingredients.stream().anyMatch(ing -> ing.getName().equals(ingr2.getName())));
    }
  }

  @Test
  @SneakyThrows
  @DisplayName("Given no filtering parameters in the request, must throw exception.")
  void getAllMealsFiltered_withNullFilterParameters() {
    when(mealRepository.findAll()).thenReturn(List.of());

    final var exception =
        assertThrows(
            Exception.class, () -> mealProvider.getAllMealsFiltered(null, null, null, null));
    assertNotNull(exception);
  }
}
