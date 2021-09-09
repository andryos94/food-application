package com.healthy.food.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meal")
@Getter
@Setter
public class Meal implements Comparable{
  @Id
  @GeneratedValue
  @Column(name = "id")
  @SerializedName("idMeal")
  private Integer id;

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

  @Override
  public int compareTo(Object o) {
    return this.name.compareTo(((Meal) o).getName());
  }
}
