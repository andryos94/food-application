package com.healthy.food.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Configuration
@ComponentScan
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("idMeal")
    private Long id;

    @SerializedName("strMeal")
    private String name;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strDrinkAlternate")
    private String drinkAlternate;
    @SerializedName("strArea")
    private String area;

    @Column(length = 1000)
    @SerializedName("strInstructions")
    private String instructions;

    @SerializedName("strMealThumb")
    private String mealThumb;
    @SerializedName("strTags")
    private String tags;
    @SerializedName("strYoutube")
    private String urlYoutube;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    @SerializedName("strIngredients")
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "measure_id")
    @SerializedName("strMeasures")
    private List<Measure> measures = new ArrayList<>();

    @SerializedName("strSource")
    private String source;
    @SerializedName("strImageSource")
    private String imageSource;
    @SerializedName("dateModified")
    private Date dateModified;
}
