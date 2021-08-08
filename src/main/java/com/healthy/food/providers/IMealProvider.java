package com.healthy.food.providers;

import com.healthy.food.entities.Meal;

public interface IMealProvider {
    Meal getMeal(String endpoint);
}
