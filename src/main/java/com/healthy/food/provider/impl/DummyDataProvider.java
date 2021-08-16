package com.healthy.food.provider.impl;

import com.healthy.food.api.IMealDbApi;
import com.healthy.food.model.Category;
import com.healthy.food.provider.IDummyDataProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyDataProvider implements IDummyDataProvider {
    private final IMealDbApi mealDbApi;

    public DummyDataProvider(IMealDbApi mealDbApi) {
        this.mealDbApi = mealDbApi;
    }

    @Override
    public List<Category> getAllCategoriesMeal() {
        return mealDbApi.getAllCategoriesMeal();
    }
}
