package com.healthy.food.api.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.healthy.food.api.IMealDbApi;
import com.healthy.food.model.Category;
import com.healthy.food.model.Meal;
import com.healthy.food.model.apiModels.MealApiCategories;
import com.healthy.food.model.apiModels.MealApiModel;
import com.healthy.food.model.apiModels.MealApiRandom;
import com.healthy.food.util.MealDbFixtures;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeAdapter(Meal.class, MealApiModel.getDeserializer());

      Gson customGson = gsonBuilder.create();

      MealApiRandom mealApiRandom = customGson.fromJson(response.body(), MealApiRandom.class);
      return mealApiRandom.getMeals().get(0);
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return null;
  }

  @Override
  public List<Category> getAllCategoriesMeal() {
    try {
      final var client = HttpClient.newHttpClient();
      final var allCategoriesMeal = API + MealDbFixtures.ALL_CATEGORIES_MEAL_ENDPOINT;
      final var request =
              HttpRequest.newBuilder(URI.create(allCategoriesMeal))
                      .method("GET", HttpRequest.BodyPublishers.noBody())
                      .build();

      var response = client.send(request, HttpResponse.BodyHandlers.ofString());

      MealApiCategories categories = new Gson().fromJson(response.body(), MealApiCategories.class);
      /*JSONObject jsonObject = new JSONObject(response.body());
      JSONArray jsonCategories = jsonObject.getJSONArray("categories");
      List<Category> categoryList = new ArrayList<>();
      for (Object jsonObj : jsonCategories) {
          categoryList.add(new Gson().fromJson((JsonElement) jsonObj, Category.class));
      }*/
      return categories.getAllCategories();
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return null;
  }

}
