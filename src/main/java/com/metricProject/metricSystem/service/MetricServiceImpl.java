package com.metricProject.metricSystem.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metricProject.metricSystem.dao.MetricDao;
import com.metricProject.metricSystem.dao.MetricSummaryDao;
import com.metricProject.metricSystem.model.Metric;
import com.metricProject.metricSystem.model.MetricSummary;

@Service
public class MetricServiceImpl implements MetricService {


	@Autowired
	private MetricDao metricDao;
	@Autowired
	private MetricSummaryDao metricSummaryDao;
	
	@Override
	public List<Metric> getMetrics() {
		
		return metricDao.findAll();
	}
	
	public Metric getMetric(Long id) {
		Optional<Metric> m1 = metricDao.findById(id);
		
		if(m1.isPresent())
		{
			return m1.get();
		}
		return null;
	}
	
	@Override
	public Metric saveMetric(Metric metric) {
	
		metricDao.save(metric);
		return metric;
	}
	
	
	@Override
	public Metric updateMetric(Long id, Metric newMetric) {
		
		Optional<Metric> oldMetric = metricDao.findById(id);
		if(oldMetric.isPresent())
		{
			//increment value by 1 if not supplied
			Metric updatedMetric = oldMetric.get();
			
			updatedMetric.setName(newMetric.getName());
			updatedMetric.setSystem(newMetric.getSystem());
			updatedMetric.setFromDate(newMetric.getFromDate());
			updatedMetric.setVal(newMetric.getVal());
			
			Metric metric1 = metricDao.save(updatedMetric);
			return metric1;
		}
		return null;
	}
	
	
	@Override
	public Metric deleteMetric(Long id) {
		
		Optional<Metric> m1 = metricDao.findById(id);
		if(m1.isPresent())
		{
			metricDao.deleteById(id);			
			return m1.get();
		}
		return null;
	}
	
	
	@Override
	public List<MetricSummary> getMetricSummary() {
		
		return metricSummaryDao.findAll();
	}
	

	@Override
	public MetricSummary saveMetricSummary(MetricSummary newMetricSummary) {
		//save the record in summary table if fresh record
		
		MetricSummary ms1 = metricSummaryDao.save(newMetricSummary);
		return ms1;
	}

	@Override
	public Long isValidId(Long id) {
		
		return id;
	}

	@Override
	public String isValidSystem(String system) {

		return system;
	}
	
}
