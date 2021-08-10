package com.healthy.food.api.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.healthy.food.api.IMealDbApi;
import com.healthy.food.model.Ingredient;
import com.healthy.food.model.Meal;
import com.healthy.food.model.Measure;
import com.healthy.food.util.MealDbFixtures;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class MealDbApi implements IMealDbApi {
  private static final String API = "https://www.themealdb.com/api";

  @Override
  public Meal getRandomMeal() {
    try {
      final var client = HttpClient.newHttpClient();
      final var randomMeal = API + MealDbFixtures.RANDOM_MEAL_ENDPOINT;
      final var request =
          HttpRequest.newBuilder(URI.create(randomMeal))
              .method("GET", HttpRequest.BodyPublishers.noBody())
              .build();

      var response = client.send(request, HttpResponse.BodyHandlers.ofString());

      // TODO: move this to another class
      JsonDeserializer<Meal> deserializer =
          (json, typeOfT, context) -> {
            JsonObject jsonObject = json.getAsJsonObject();

            List<Ingredient> ingredients = new ArrayList<>();
            List<Measure> measures = new ArrayList<>();

            // get ingredients 1..20
            // get measures 1..20

            return null;
          };

      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeAdapter(Meal.class, deserializer);
      //      gsonBuilder.registerTypeAdapter(Meal.class, MealApiModel.getDeserializer());

      Gson customGson = gsonBuilder.create();

      return customGson.fromJson(response.body(), Meal.class);
      // parsam json din response
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return null;
  }

  private List<Ingredient> extractIngredientsFromMealJson(JsonObject mealJsonObject) {
    // loop 1..20 -> non null && non empty
    // list.add(extractIngredientFromMealJson(i))
    return List.of();
  }

  // Ingredient extractIngredientFromMealJson(int index)

  private List<Measure> extractMeasuresFromMealJson(JsonObject mealJsonObject) {
    // loop 1..20 -> non null && non empty
    return List.of();
  }

  // TODO: implement method for List all meal categories
  // www.themealdb.com/api/json/v1/1/categories.php

}
