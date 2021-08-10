package com.healthy.food.model.apiModels;

import com.google.gson.JsonDeserializer;
import com.healthy.food.model.Meal;
import lombok.Data;

@Data
public class MealApiModel {
  // json properties

  // custom deserializer (copy from MealProvider)
  public static JsonDeserializer<Meal> getDeserializer() {
    return null;
  }
}
