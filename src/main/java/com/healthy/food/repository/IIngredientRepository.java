package com.healthy.food.repository;

import com.healthy.food.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIngredientRepository extends CrudRepository<Ingredient, Long> {
}
