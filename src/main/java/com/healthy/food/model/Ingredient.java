package com.healthy.food.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ingredient")
public class Ingredient {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;
}
