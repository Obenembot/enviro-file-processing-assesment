package com.enviro.assessment.grad001.bridgettetambe.web.rest;

import com.enviro.assessment.grad001.bridgettetambe.models.EnvironmentalData;
import com.enviro.assessment.grad001.bridgettetambe.services.EnvironmentalDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/environmental-data")
public class EnvironmentalDataResource {
    private static final Logger log = LoggerFactory.getLogger(EnvironmentalDataResource.class);

    final EnvironmentalDataService environmentalDataService;

    public EnvironmentalDataResource(EnvironmentalDataService environmentalDataService) {
        this.environmentalDataService = environmentalDataService;
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadEnvironmentData(@RequestPart("file") MultipartFile file) throws Exception {
        log.info("Uploading environment data: {}", file.getSize());
        String message = "Uploaded the file successfully : " + file.getOriginalFilename() + " processing in progressed";
        environmentalDataService.upload(file);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Page<EnvironmentalData>> uploadEnvironmentData(Pageable pageable) {
        log.info("Request to get EnvironmentalData by page: {}", pageable);
        return ResponseEntity.status(HttpStatus.OK).body(environmentalDataService.findAll(pageable));
    }
}
