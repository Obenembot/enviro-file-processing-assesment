package com.enviro.assessment.grad001.bridgettetambe.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "temperature")
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Value1")
    private Double value;
    private String unit;
}
