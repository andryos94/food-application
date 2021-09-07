package com.healthy.food.model.apiModels;

import com.google.gson.annotations.SerializedName;
import com.healthy.food.model.Meal;
import lombok.Data;

import java.util.List;

@Data
public class MealApiRandom {
    @SerializedName("meals")
    private List<Meal> meals;
}
