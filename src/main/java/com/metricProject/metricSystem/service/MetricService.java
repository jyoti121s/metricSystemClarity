package com.metricProject.metricSystem.service;

import java.util.List;

import com.metricProject.metricSystem.model.Metric;
import com.metricProject.metricSystem.model.MetricSummary;

public interface MetricService {
	
	//add list of methods to perform the action of get, update and delete, etc
	public List<Metric> getMetrics();
	
	public Metric getMetric(Long id);
	
	public Metric saveMetric(Metric metric);
	
	public Metric updateMetric(Long id, Metric newMetric);
	
	public Metric deleteMetric(Long id);
	
	public List<MetricSummary> getMetricSummary();
	
	public MetricSummary saveMetricSummary(MetricSummary newMetricSummary);
	
	public Long isValidId(Long id);
	
	public String isValidSystem(String system);

}
