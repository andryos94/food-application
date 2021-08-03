package com.healthy.food.repositories;

import com.healthy.food.entities.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {

}
