package com.healthy.food.service.impl;

import com.healthy.food.model.Ingredient;
import com.healthy.food.provider.IDummyDataProvider;
import com.healthy.food.provider.IMealProvider;
import com.healthy.food.repository.IIngredientRepository;
import com.healthy.food.repository.IMealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class MealServiceTest {
  private MealService mealService;

  private final IIngredientRepository ingredientRepository = mock(IIngredientRepository.class);
  private final IMealRepository mealRepository = mock(IMealRepository.class);
  private final IMealProvider mealProvider = mock(IMealProvider.class);
  private final IDummyDataProvider dummyDataProvider = mock(IDummyDataProvider.class);

  private final Ingredient ingredient = mock(Ingredient.class);

  @BeforeEach
  void setUp() {
    this.mealService = new MealService(mealProvider, dummyDataProvider);
  }

  // TODO: getAllMealsFiltered -> succes
  // TODO: getAllMealsFiltered -> should throw exception
  //  @SneakyThrows
  //  @Test
  //  void getAllMealsFilteredByIngredient() {
  //    //    when(ingredient.getId()).thenReturn(2222);
  //    //    when(ingredient.getName()).thenReturn("curry");
  //
  //    Ingredient ingr1 = new Ingredient();
  //    ingr1.setId(1111);
  //    ingr1.setName("piper");
  //    Ingredient ingr2 = new Ingredient();
  //    ingr2.setId(2222);
  //    ingr2.setName("curry");
  //    Ingredient ingr3 = new Ingredient();
  //    ingr3.setId(3333);
  //    ingr3.setName("salt");
  //
  //    var allIngredients = List.of(ingr2, ingr1);
  //
  //    Meal meal1 = new Meal();
  //    meal1.setIngredients(allIngredients);
  //
  //    Meal meal2 = new Meal();
  //    meal2.setIngredients(allIngredients);
  //
  //    Meal meal3 = new Meal();
  //    meal3.setIngredients(List.of(ingr3));
  //
  //    List<Meal> listOfMeals = List.of(meal1, meal2, meal3);
  //
  //    when(ingredientRepository.findById(any())).thenReturn(Optional.of(ingr2));
  //    when(mealRepository.findAll()).thenReturn(listOfMeals);
  //    when(mealProvider.getAllMealsFiltered(2222L, null, null, null)).thenReturn(listOfMeals);
  //
  //    final var result = mealService.getAllMealsFiltered(2222L, null, null, null);
  //
  //    assertEquals(2, result.size());
  //    for (Meal meal : result) {
  //      var ingredients = meal.getIngredients();
  //
  //      assertTrue(ingredients.stream().anyMatch(ing -> ing.getName().equals(ingr1.getName())));
  //    }
  //  }

  // TODO: assertThrows ->  doThrow(new
  // Exception("blabla")).when(mealProvider).getAllMealsFiltered(any()); helper

  @Test
  void getAllMealsFilteredAlphabetical() {}

  @Test
  void getAllMealsFilteredByCategory() {}

  @Test
  void getAllMealsFilteredByArea() {}

  @Test
  void getRandomMeal() {}
}
