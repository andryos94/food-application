package com.healthy.food.providers.impl;

import com.healthy.food.entities.Meal;
import com.healthy.food.providers.IMealProvider;
import com.healthy.food.utils.HttpClientConnection;

public class MealProvider implements IMealProvider {
    private static final String API = "https://www.themealdb.com/api";

    @Override
    public Meal getMeal(String endpoint) {
        return HttpClientConnection.getModel(Meal.class, API, endpoint);
    }
}
