package com.metricProject.metricSystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	Logger logger = LoggerFactory.getLogger(MetricsController.class);
	
	@GetMapping("/metrics")
	public ResponseEntity<List<Metric>> getMetric() {	
		try {
			if(metricService.getMetrics() != null)
			{
				logger.info("Metric details were fetched");
				return new ResponseEntity<>(metricService.getMetrics(),HttpStatus.OK);
			}
			else
			{
				logger.warn("Please add metrics before fetching the data.");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		}
		catch (Exception e) {
			logger.error("An error was encountered while fetching metrics!");
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
					logger.info("Metric details were fetched");
					return new ResponseEntity<>(metricService.getMetric(id),HttpStatus.OK);	
				}
				else
				{
					logger.warn("Metric was not fetched! Metric was not found");
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}		
			}
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			logger.error("An error was encountered while fetching metric!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/metrics")
	public ResponseEntity<Metric> saveMetric(@RequestBody Metric metric) {
		try {
			
			//conditions for checking system and name
			if (metric.getSystem() != null && metric.getName() != null)
			{
			Metric m1 = metricService.saveMetric(metric);
				if (m1 != null)
				{
					logger.info("Metric "+ m1.getName() + " has been added");
					return new ResponseEntity<>(m1,HttpStatus.OK);
				}
				else
				{
					logger.warn("There was an issue while adding metric. Please try again");
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}
		catch (Exception e) {
			logger.error("An error was encountered! Metric was not saved");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/metrics/{id}")
	public ResponseEntity<String> updateMetric(@PathVariable Long id, @RequestBody Metric newMetric) {

		try {
			if(id != null && newMetric.getSystem() != null && newMetric.getName() != null && newMetric.getFromDate() != null)
			{
				//check for system,name and date 
				Metric m1 = metricService.updateMetric(id, newMetric);
				
				if(m1 != null)
				{
					logger.info("Metric " + m1.getName() + " was updated!");
					return new ResponseEntity<>("The metric has been updated",HttpStatus.OK);	
				}
				else
				{
					logger.warn("Metric was not updated! Metric was not found");
					return new ResponseEntity<>("The metric was not found",HttpStatus.NOT_FOUND);
				}
					
			}
			else
			{
				logger.warn("Metric was not updated! System, Name and From Date is mandatory input");
				return new ResponseEntity<>("A required parameter was not supplied or is invalid, or system or name does not match \r\n"
						+ "the existing metric",HttpStatus.BAD_REQUEST);
			}
				
		}
		catch (Exception e) {
			logger.error("An error was encountered! Metric was not updated");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@DeleteMapping("/metrics/{id}") //change on the basis of metric name
	public ResponseEntity<String> deleteMetric(@PathVariable Long id) {
		
		try {
				Metric m1 = metricService.deleteMetric(id);
				
				if(m1 != null)
				{
					logger.info("Metric " + m1.getName() + " was deleted!");
					return new ResponseEntity<>("The metric has been deleted",HttpStatus.OK);	
				}
				else
				{
					logger.warn("Metric was not deleted! Metric was not found");
					return new ResponseEntity<>("The metric was not found",HttpStatus.NOT_FOUND);
				}
					
			}
		catch (Exception e) {
			logger.error("An error was encountered! Metric was not deleted");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/metricsSummary")
	public ResponseEntity<List<MetricSummary>> getMetricSummary() {
		
		try {
			if(metricService.getMetricSummary().isEmpty())
			{
				logger.warn("Metric Summary is empty. Please add data before fetching");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			else
			{
				logger.info("Fetched Metric Summary data.");
				return new ResponseEntity<>(metricService.getMetricSummary(),HttpStatus.OK);
			}
			
		}
		catch (Exception e) {
			logger.error("An error was encountered! Metric was not deleted");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/metricsSummary")
	public ResponseEntity<MetricSummary> addMetricSummary(@RequestBody MetricSummary newMetricSummary) {
		
		try {
			MetricSummary ms1 = metricService.saveMetricSummary(newMetricSummary);
			if (ms1 != null)
			{
				logger.info("Metric " + ms1.getMsName() + " Summary data was added.");
				return new ResponseEntity<>(ms1,HttpStatus.OK);
			}
			else
			{
				logger.warn("Metric Summary could not be added in the system.");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		}
		catch (Exception e) {
			logger.error("An error was encountered! Metric was not deleted");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
