package com.enviro.assessment.grad001.bridgettetambe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "environmental_data")
public class EnvironmentalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private Double latitude;
    private Double longitude;

    @OneToOne(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private Temperature temperature;
    @OneToOne(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private Humidity humidity;
    @OneToOne(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private AirQuality airQuality;

    private ZonedDateTime timestamp;
}
