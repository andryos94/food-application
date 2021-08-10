package com.healthy.food.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "measure")
@Getter
@Setter
public class Measure {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "value")
    private String value;
}
