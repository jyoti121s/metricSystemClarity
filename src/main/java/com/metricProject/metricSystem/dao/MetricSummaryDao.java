package com.metricProject.metricSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.metricProject.metricSystem.model.MetricSummary;

public interface MetricSummaryDao extends JpaRepository<MetricSummary, Long> {

}
