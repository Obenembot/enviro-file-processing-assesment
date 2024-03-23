package com.enviro.assessment.grad001.bridgettetambe.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "air_quality")
public class AirQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private Pollution pm25;
    @OneToOne(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private Pollution pm10;
    @OneToOne(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private Pollution o3;

}
