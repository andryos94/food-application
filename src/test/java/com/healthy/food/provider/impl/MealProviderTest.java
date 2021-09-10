package com.healthy.food.provider.impl;

import com.healthy.food.api.IMealDbApi;
import com.healthy.food.api.impl.MealDbApi;
import com.healthy.food.model.Ingredient;
import com.healthy.food.model.Meal;
import com.healthy.food.repository.IIngredientRepository;
import com.healthy.food.repository.IMealRepository;
import com.healthy.food.util.InvalidFilteringRequestException;
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

    private List<Ingredient> ingredients;
    private List<Meal> meals;

    @BeforeEach
    void setUp() {
        ingredients = List.of(
                createIngredient(1111, "piper"),
                createIngredient(2222, "curry"),
                createIngredient(3333, "salt")
        );

        meals = List.of(
                createMeal(1111, "potatoes", "Italia", "pasta", ingredients),
                createMeal(2222, "polenta", "Romania", "pizza", ingredients),
                createMeal(3333, "paella", "Italia", "Pasta", List.of())
        );

        this.mealProvider = new MealProvider(mealRepository, ingredientRepository, mealDbApi);
    }

    @SneakyThrows
    @Test
    @DisplayName("Given an ingredient based sorting, must return meals containing that ingredient.")
    void getAllMealsFilteredByIngredient() {
        when(ingredientRepository.findById(any())).thenReturn(Optional.of(ingredients.get(2)));
        when(mealRepository.findAll()).thenReturn(meals);

        final var result = mealProvider.getAllMealsFiltered(2222L, null, null, null);

        assertEquals(2, result.size());
        for (Meal meal : result) {
            final var ingredients = meal.getIngredients();

            assertTrue(ingredients.stream().anyMatch(ing -> ing.getName().equals(ingredients.get(2).getName())));
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Given no filtering parameters in the request, must throw exception.")
    void getAllMealsFiltered_withNullFilterParameters() {
        when(mealRepository.findAll()).thenReturn(List.of());

        final var exception =
                assertThrows(
                        InvalidFilteringRequestException.class, () -> mealProvider.getAllMealsFiltered(null, null, null, null));
        assertNotNull(exception);
    }

    //TODO: replace meals
    @Test
    @SneakyThrows
    @DisplayName("Given a letter or a text, must return meals which names contain that letter or text.")
    void getAllMealsFilteredAlphabetical() {
        when(mealRepository.findAll()).thenReturn(meals);

        final var result = mealProvider.getAllMealsFiltered(null, "p", null, null);

        //two test cases
        assertEquals(3, result.size());
        for (Meal meal : result) {
            assertTrue(meal.getName().startsWith("p"));
        }

        final var result1 = mealProvider.getAllMealsFiltered(null, "po", null, null);

        assertEquals(2, result1.size());
        for (Meal meal : result1) {
            assertTrue(meal.getName().startsWith("po"));
        }

        //check if they are alphabetically sorted
        assertTrue(result1.get(0).getName().equals("polenta"));
        assertTrue(result1.get(1).getName().equals("potatoes"));
    }

    //TODO: replace meals
    @Test
    @SneakyThrows
    @DisplayName("Given a category, must return meals containing that category.")
    void getAllMealsFilteredByCategory() {
        when(mealRepository.findAll()).thenReturn(meals);

        final var result = mealProvider.getAllMealsFiltered(null, null, "pasta", null);

        //two test cases
        assertEquals(1, result.size());
        for (Meal meal : result) {
            assertTrue(meal.getCategory().equals("pasta"));
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Given an area, must return meals containing that area.")
    void getAllMealsFilteredByArea() {
        when(mealRepository.findAll()).thenReturn(meals);

        final var result = mealProvider.getAllMealsFiltered(null, null, null, "Italia");

        //two test cases
        assertEquals(2, result.size());
        for (Meal meal : result) {
            assertTrue(meal.getArea().equals("Italia"));
        }
    }

    //TODO: de testat ca nu aduce null
    @Test
    @SneakyThrows
    @DisplayName("Given an api, must return a random meal.")
    void getRandomMeal() {
        final var result = new MealDbApi().getRandomMeal();
        assertTrue(result != null);
    }

    private Ingredient createIngredient(final Integer id, final String name) {
        var ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName(name);

        return ingredient;
    }

    private Meal createMeal(final Integer id, final String name, final String area, final String category, final List<Ingredient> ingredients) {
        var meal = new Meal();
        meal.setId(id);
        meal.setName(name);
        meal.setArea(area);
        meal.setCategory(category);
        meal.setIngredients(ingredients);

        return meal;
    }
}
