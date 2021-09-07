package com.healthy.food.model.apiModels;

import com.google.gson.annotations.SerializedName;
import com.healthy.food.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class MealApiCategories {
    @SerializedName("categories")
    private List<Category> allCategories;
}
