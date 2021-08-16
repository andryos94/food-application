package com.healthy.food.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
  @Id
  @GeneratedValue
  @Column(name = "id")
  @SerializedName("idCategory")
  private Integer id;

  @SerializedName("strCategory")
  private String category;

  @SerializedName("strCategoryThumb")
  private String categoryThumb;

  @Column(length = 1000)
  @SerializedName("strCategoryDescription")
  private String categoryDescription;
}
