package com.healthy.food.repository;

import com.healthy.food.model.Measure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasureRepository extends CrudRepository<Measure, Long> {
  //List<Measure> saveAll(List<Measure> measures);
}
