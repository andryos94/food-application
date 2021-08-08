package com.healthy.food.repository;

import com.healthy.food.model.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMealRepository extends CrudRepository<Meal, Long> {

}
