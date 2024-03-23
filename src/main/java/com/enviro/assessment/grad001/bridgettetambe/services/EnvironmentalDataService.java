package com.enviro.assessment.grad001.bridgettetambe.services;

import com.enviro.assessment.grad001.bridgettetambe.models.EnvironmentalData;
import com.enviro.assessment.grad001.bridgettetambe.models.EnvironmentalDataImportFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EnvironmentalDataService {


    List<EnvironmentalDataImportFile> upload(MultipartFile multipartFile) throws Exception;
    List<EnvironmentalData> saveAll(List<EnvironmentalData>  environmentalDataList);
    Page<EnvironmentalData> findAll(Pageable pageable);
}
