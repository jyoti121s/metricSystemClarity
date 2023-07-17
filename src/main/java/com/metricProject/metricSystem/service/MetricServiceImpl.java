package com.metricProject.metricSystem.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

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
			//Date fromDate = new Date(m1.getFromDate*1000L); //to convert date from unix time
			return m1.get();
		}
		return null;
	}
	
	@Override
	public Metric saveMetric(Metric metric) {
		
		//long unixTimestamp = new Date().getTime()/1000L; //to save date into unix time
		metric.setFromDate(new Date());
		metricDao.save(metric);
		return metric;
	}
	
	
	@Override
	public Metric updateMetric(Long id, Metric newMetric) {
		
		Optional<Metric> oldMetric = metricDao.findById(id);
		if(oldMetric.isPresent())
		{
			int value,metricValue = newMetric.getVal();
			//increment value by 1 if not supplied
			Metric updatedMetric = oldMetric.get();
			try {
				value = newMetric.getVal();
				System.out.println("Metric value is "+newMetric.getVal());
				if (value == 1 )
				{
					metricValue = oldMetric.get().getVal() + 1;
				}
			}
			catch(Exception e)
			{
				throw e;
			}
			
			updatedMetric.setName(newMetric.getName());
			updatedMetric.setSystem(newMetric.getSystem());
			updatedMetric.setFromDate(newMetric.getFromDate());
			updatedMetric.setVal(metricValue);
			
			Metric metric1 = metricDao.save(updatedMetric);
			return metric1;
		}
		return null;
	}
	
	
	@Override
	public Metric deleteMetric(Long id) {
		
		Optional<Metric> metric = metricDao.findById(id);
		if(metric.isPresent())
		{
			metricDao.deleteById(id);			
			return metric.get();
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
		MetricSummary metricSummary = metricSummaryDao.save(newMetricSummary);
		return metricSummary;
	}
	
}
