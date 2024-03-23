package com.enviro.assessment.grad001.bridgettetambe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "environmental_data_import_file")
public class EnvironmentalDataImportFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "processed")
    private Boolean processed;

    @Column(name = "failed_to_process")
    private Boolean failedToProcess;

    @Column(name = "fail_reason")
    private String failReason;

    @Column(name = "processed_ate")
    private ZonedDateTime processedDate;

    private String location;
    private Double latitude;
    private Double longitude;

    //Temperature
    @Column(name = "temperature_value")
    private Double temperatureValue;
    @Column(name = "temperature_unit")
    private String temperatureUnit;


    //Humidity
    @Column(name = "humidity_value")
    private Double humidityValue;
    @Column(name = "humidity_unit")
    private String humidityUnit;

    //AirQuality Pollution
    @Column(name = "pm25_value")
    private Double pm25Value;
    @Column(name = "pm25_unit")
    private String pm25Unit;

    @Column(name = "pm10_value")
    private Double pm10Value;
    @Column(name = "pm10_unit")
    private String pm10Unit;

    @Column(name = "o3_value")
    private Double o3Value;
    @Column(name = "o3_unit")
    private String o3Unit;

    @Column(name = "file_hash")
    private String fileHash;

    public EnvironmentalDataImportFile() {

    }

    public EnvironmentalDataImportFile(String location, double latitude, double longitude,
                                       double temperatureValue, String temperatureUnit,
                                       double humidityValue, String humidityUnit,
                                       double pm25Value, String pm25Unit,
                                       double pm10Value, String pm10Unit,
                                       double o3Value, String o3Unit) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperatureValue = temperatureValue;
        this.temperatureUnit = temperatureUnit;
        this.humidityValue = humidityValue;
        this.humidityUnit = humidityUnit;
        this.pm25Value = pm25Value;
        this.pm25Unit = pm25Unit;
        this.pm10Value = pm10Value;
        this.pm10Unit = pm10Unit;
        this.o3Value = o3Value;
        this.o3Unit = o3Unit;
    }


}
