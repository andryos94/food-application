package com.healthy.food;

import com.healthy.food.entities.Meal;
import com.healthy.food.repositories.MealRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class FoodApplicationTests {

    @Autowired
    private MealRepository mealRepository;

    @Test
    void testCreateMeal() {
        Meal meal = new Meal();
        meal.setName("brunch");
        meal.setArea("France");
        meal.addIngredient(null);
        meal.addMeasure(null);
        mealRepository.save(meal);
    }

    /*private Ingredient createIngredient(String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        return ingredientRepository.save(ingredient);
    }

    private Measure createMeasure(String value) {
        Measure measure = new Measure();
        measure.setValue(value);
        return measureRepository.save(measure);
    }*/

}
