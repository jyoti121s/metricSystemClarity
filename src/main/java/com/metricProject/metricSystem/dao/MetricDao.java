package com.metricProject.metricSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metricProject.metricSystem.model.Metric;

public interface MetricDao extends JpaRepository<Metric, Long> {

}
