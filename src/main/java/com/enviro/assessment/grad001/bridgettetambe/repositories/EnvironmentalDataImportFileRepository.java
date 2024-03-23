package com.enviro.assessment.grad001.bridgettetambe.repositories;

import com.enviro.assessment.grad001.bridgettetambe.models.EnvironmentalDataImportFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvironmentalDataImportFileRepository extends JpaRepository<EnvironmentalDataImportFile, Long> {


    List<EnvironmentalDataImportFile> findAllByProcessedFalse();
    long  countEnvironmentalDataImportFileByFileHash(String fileHash);
}
