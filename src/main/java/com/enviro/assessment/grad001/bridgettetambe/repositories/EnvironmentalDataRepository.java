package com.enviro.assessment.grad001.bridgettetambe.repositories;

import com.enviro.assessment.grad001.bridgettetambe.models.EnvironmentalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentalDataRepository  extends JpaRepository<EnvironmentalData, Long> {
}
