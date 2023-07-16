package com.metricProject.metricSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.metricProject.metricSystem.model.Metric;
import com.metricProject.metricSystem.model.MetricSummary;
import com.metricProject.metricSystem.service.MetricService;

@RestController
public class MetricsController {

	@Autowired
	private MetricService metricService;	
	
	
	@GetMapping("/metrics")
	public ResponseEntity<List<Metric>> getMetric() {	
		try {
			if(metricService.getMetrics() != null)
			{
				return new ResponseEntity<>(metricService.getMetrics(),HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/metrics/{id}")
	public ResponseEntity<Metric> getMetrics(@PathVariable Long id) {
		
		try {
			if(id != null)
			{
				if(metricService.getMetric(id) != null)
				{
					return new ResponseEntity<>(metricService.getMetric(id),HttpStatus.OK);	
				}
				else
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/metrics")
	public ResponseEntity<Metric> saveMetric(@RequestBody Metric metric) {
		try {
			
			//add conditions for checking system and name
			
			Metric m1 = metricService.saveMetric(metric);
			
			if (m1 != null)
			{
				return new ResponseEntity<>(m1,HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/metrics/{id}")
	public ResponseEntity<String> updateMetric(@PathVariable Long id, @RequestBody Metric newMetric) {
		
		try {
			if(id != null)
			{
				//check for system,name,date and 
				Metric m1 = metricService.updateMetric(id, newMetric);
				
				if(m1 != null)
				{
					return new ResponseEntity<>("The metric has been updated",HttpStatus.OK);	
				}
				else
					return new ResponseEntity<>("The metric was not found",HttpStatus.NOT_FOUND);
			}
			else
				return new ResponseEntity<>("A required parameter was not supplied or is invalid, or system or name does not match \r\n"
						+ "the existing metric",HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@DeleteMapping("/metrics/{id}") //change on the basis of metric name
	public ResponseEntity<String> deleteMetric(@PathVariable Long id) {
		
		try {
				Metric m1 = metricService.deleteMetric(id);
				
				if(m1 != null)
				{
					return new ResponseEntity<>("The metric has been deleted",HttpStatus.OK);	
				}
				else
					return new ResponseEntity<>("The metric was not found",HttpStatus.NOT_FOUND);
			}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/metricsSummary")
	public ResponseEntity<List<MetricSummary>> getMetricSummary() {
		
		try {
			if(metricService.getMetricSummary().isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			else
			return new ResponseEntity<>(metricService.getMetricSummary(),HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/metricsSummary")
	public ResponseEntity<MetricSummary> addMetricSummary(@RequestBody MetricSummary newMetricSummary) {
		
		try {
			MetricSummary ms1 = metricService.saveMetricSummary(newMetricSummary);
			if (ms1 != null)
			{
				return new ResponseEntity<>(ms1,HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
