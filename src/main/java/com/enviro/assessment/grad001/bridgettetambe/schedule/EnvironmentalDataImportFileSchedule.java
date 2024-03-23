package com.enviro.assessment.grad001.bridgettetambe.schedule;

import com.enviro.assessment.grad001.bridgettetambe.models.*;
import com.enviro.assessment.grad001.bridgettetambe.repositories.EnvironmentalDataImportFileRepository;
import com.enviro.assessment.grad001.bridgettetambe.services.EnvironmentalDataService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EnvironmentalDataImportFileSchedule {
    private static final Logger log = LoggerFactory.getLogger(EnvironmentalDataImportFileSchedule.class);

    public EnvironmentalDataImportFileRepository environmentalDataImportFileRepository;
    public EnvironmentalDataService environmentalDataService;


    public EnvironmentalDataImportFileSchedule(EnvironmentalDataService environmentalDataService,
                                               EnvironmentalDataImportFileRepository environmentalDataImportFileRepository) {

        this.environmentalDataImportFileRepository = environmentalDataImportFileRepository;
        this.environmentalDataService = environmentalDataService;
    }


    @SchedulerLock(name = "findAllUnprocessedCategorisationTypesUpdates", lockAtMostFor = "50s", lockAtLeastFor = "30s")
    @Scheduled(cron = "*/1 * * * * *")
    public void findAllUnprocessedCategorisationTypesUpdates() {
        List<EnvironmentalDataImportFile> allByProcessedFalse = environmentalDataImportFileRepository.findAllByProcessedFalse();
        List<EnvironmentalDataImportFile> allByProcessedTrue = new ArrayList<>();
        List<EnvironmentalData> environmentalDataList = new ArrayList<>();
        if (!allByProcessedFalse.isEmpty()) {
            log.info("Finding all unprocessed EnvironmentalDataImportFile: {}", allByProcessedFalse.size());
            allByProcessedFalse.forEach(environmentalDataImportFile -> {
                environmentalDataList.add(buildEnvironmentalData(environmentalDataImportFile));
                environmentalDataImportFile.setProcessed(true);
                environmentalDataImportFile.setFailedToProcess(false);
                environmentalDataImportFile.setProcessedDate(ZonedDateTime.now());
                environmentalDataImportFile.setFailReason(null);
                allByProcessedTrue.add(environmentalDataImportFile);
            });
            // SAVE PROCESSED EnvironmentalData FROM IMPORT FILE.
            environmentalDataImportFileRepository.saveAll(allByProcessedTrue);
            environmentalDataService.saveAll(environmentalDataList);
        }
    }

    private EnvironmentalData buildEnvironmentalData(EnvironmentalDataImportFile environmentalDataImportFile) {

        EnvironmentalData environmental = new EnvironmentalData();

        environmental.setLocation(environmentalDataImportFile.getLocation());
        environmental.setLatitude(environmentalDataImportFile.getLatitude());
        environmental.setLongitude(environmentalDataImportFile.getLongitude());

        if (environmentalDataImportFile.getTemperatureUnit() != null && environmentalDataImportFile.getTemperatureValue() != null) {
            Temperature temperature = new Temperature();
            temperature.setUnit(environmentalDataImportFile.getTemperatureUnit());
            temperature.setValue(environmentalDataImportFile.getTemperatureValue());
            environmental.setTemperature(temperature);
        }

        if (environmentalDataImportFile.getHumidityUnit() != null && environmentalDataImportFile.getHumidityValue() != null) {
            Humidity humidity = new Humidity();
            humidity.setUnit(environmentalDataImportFile.getHumidityUnit());
            humidity.setValue(environmentalDataImportFile.getHumidityValue());
            environmental.setHumidity(humidity);
        }

        AirQuality airQuality = new AirQuality();

        if (environmentalDataImportFile.getO3Unit() != null && environmentalDataImportFile.getO3Value() != null) {
            Pollution pollution = new Pollution();
            pollution.setUnit(environmentalDataImportFile.getO3Unit());
            pollution.setValue(environmentalDataImportFile.getO3Value());
            airQuality.setO3(pollution);
        }

        if (environmentalDataImportFile.getPm10Unit() != null && environmentalDataImportFile.getPm10Value() != null) {
            Pollution pollution = new Pollution();
            pollution.setUnit(environmentalDataImportFile.getO3Unit());
            pollution.setValue(environmentalDataImportFile.getO3Value());
            airQuality.setPm10(pollution);
        }

        if (environmentalDataImportFile.getPm25Unit() != null && environmentalDataImportFile.getPm25Value() != null) {
            Pollution pollution = new Pollution();
            pollution.setUnit(environmentalDataImportFile.getO3Unit());
            pollution.setValue(environmentalDataImportFile.getO3Value());
            airQuality.setPm25(pollution);
        }

        environmental.setAirQuality(airQuality);
        environmental.setTimestamp(ZonedDateTime.now());

        return environmental;
    }

}
