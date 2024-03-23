package com.enviro.assessment.grad001.bridgettetambe.services.impl;

import com.enviro.assessment.grad001.bridgettetambe.models.EnvironmentalData;
import com.enviro.assessment.grad001.bridgettetambe.models.EnvironmentalDataImportFile;
import com.enviro.assessment.grad001.bridgettetambe.repositories.EnvironmentalDataImportFileRepository;
import com.enviro.assessment.grad001.bridgettetambe.repositories.EnvironmentalDataRepository;
import com.enviro.assessment.grad001.bridgettetambe.services.EnvironmentalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnvironmentalDataServiceImpl implements EnvironmentalDataService {


    public EnvironmentalDataRepository environmentalDataRepository;
    public EnvironmentalDataImportFileRepository environmentalDataImportFileRepository;

    @Autowired
    public EnvironmentalDataServiceImpl(EnvironmentalDataRepository environmentalDataRepository,
                                        EnvironmentalDataImportFileRepository environmentalDataImportFileRepository) {
        this.environmentalDataRepository = environmentalDataRepository;
        this.environmentalDataImportFileRepository = environmentalDataImportFileRepository;
    }

    @Override
    public List<EnvironmentalDataImportFile> upload(MultipartFile multipartFile) throws Exception {
        String hashString = "";
        try {
            byte[] uploadBytes = multipartFile.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            hashString = new BigInteger(1, digest).toString(16);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new Exception(e.getMessage());
        }

        if (environmentalDataImportFileRepository.countEnvironmentalDataImportFileByFileHash(hashString) > 0) {
            throw new Exception("A file with this name and contents was uploaded before:");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String line;
            List<EnvironmentalDataImportFile> environmentalDataImportFileList = new ArrayList<>();

            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String location = parts[0].replace("\"", "");
                double latitude = Double.parseDouble(parts[1]);
                double longitude = Double.parseDouble(parts[2]);
                double temperatureValue = Double.parseDouble(parts[3]);
                String temperatureUnit = parts[4];
                double humidityValue = Integer.parseInt(parts[5]);
                String humidityUnit = parts[6];
                double pm25Value = Integer.parseInt(parts[7]);
                String pm25Unit = parts[8];
                double pm10Value = Integer.parseInt(parts[9]);
                String pm10Unit = parts[10];
                double o3Value = Integer.parseInt(parts[11]);
                String o3Unit = parts[12];

                EnvironmentalDataImportFile data = new EnvironmentalDataImportFile(
                        location, latitude, longitude,
                        temperatureValue, temperatureUnit,
                        humidityValue, humidityUnit,
                        pm25Value, pm25Unit,
                        pm10Value, pm10Unit,
                        o3Value, o3Unit
                );
                data.setFileHash(hashString);
                data.setProcessed(false);
                environmentalDataImportFileList.add(data);
            }

            return this.environmentalDataImportFileRepository.saveAll(environmentalDataImportFileList);
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<EnvironmentalData> saveAll(List<EnvironmentalData> environmentalDataList) {
        return environmentalDataRepository.saveAll(environmentalDataList);
    }

    @Override
    public Page<EnvironmentalData> findAll(Pageable pageable) {
        return environmentalDataRepository.findAll(pageable);
    }
}
