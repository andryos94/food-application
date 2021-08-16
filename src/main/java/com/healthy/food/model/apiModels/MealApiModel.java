package com.healthy.food.model.apiModels;

import com.google.gson.*;
import com.healthy.food.model.Ingredient;
import com.healthy.food.model.Meal;
import com.healthy.food.model.Measure;
import com.mysql.cj.util.StringUtils;
import lombok.Data;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Data
public class MealApiModel {
  // json properties

  // custom deserializer (copy from MealProvider)
  public static JsonDeserializer<Meal> getDeserializer() {
    JsonDeserializer<Meal> deserializer =
            new JsonDeserializer<Meal>() {
              @Override
              public Meal deserialize(JsonElement json, Type Meal, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();

                List<Ingredient> ingredients = new ArrayList<>();
                List<Measure> measures = new ArrayList<>();

                // get ingredients 1..20
                ingredients.addAll(MealApiModel.extractIngredientsFromMealJson(jsonObject));
                // get measures 1..20
                measures.addAll(MealApiModel.extractMeasuresFromMealJson(jsonObject));

                Meal meal = new Gson().fromJson(jsonObject, Meal.class);
                meal.setMeasures(measures);
                meal.setIngredients(ingredients);
                return meal;
              }
            };
    return deserializer;
  }

  private static List<Ingredient> extractIngredientsFromMealJson(JsonObject mealJsonObject) {
    if (mealJsonObject != null) {
      List<Ingredient> ingredients = new ArrayList<>();
      for (int i = 1; i <= 20; i++) {
        Ingredient ingredient = extractIngredientFromMealJson(i, mealJsonObject);
        if (ingredient != null){
          ingredients.add(ingredient);
        }
      }
      return ingredients;
    }
    return List.of();
  }

  private static Ingredient extractIngredientFromMealJson(int index, JsonObject jsonObject) {
    Random random = new Random();
    if (!StringUtils.isNullOrEmpty(String.valueOf(jsonObject.get("strIngredient" + index)))) {
      Ingredient ingredient = new Ingredient();
      ingredient.setId(random.nextInt());
      ingredient.setName(String.valueOf(jsonObject.get("strIngredient" + index)));
      return ingredient;
    }
    return null;
  }

  private static List<Measure> extractMeasuresFromMealJson(JsonObject mealJsonObject) {
    // loop 1..20 -> non null && non empty
    if (mealJsonObject != null) {
      List<Measure> measures = new ArrayList<>();
      for (int i = 1; i <= 20; i++) {
        Measure measure = extractMeasureFromMealJson(i, mealJsonObject);
        if (measure != null){
          measures.add(measure);
        }
      }
      return measures;
    }
    return List.of();
  }

  private static Measure extractMeasureFromMealJson(int index, JsonObject jsonObject) {
    Random random = new Random();
    if (!StringUtils.isNullOrEmpty(String.valueOf(jsonObject.get("strMeasure" + index)))) {
      Measure measure = new Measure();
      measure.setId(random.nextInt());
      measure.setValue(String.valueOf(jsonObject.get("strMeasure" + index)));
      return measure;
    }
    return null;
  }
}
