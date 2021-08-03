package com.healthy.food.repositories;

import com.healthy.food.entities.Measure;
import org.springframework.data.repository.CrudRepository;

public interface MeasureRepository extends CrudRepository<Measure, Long> {
}
