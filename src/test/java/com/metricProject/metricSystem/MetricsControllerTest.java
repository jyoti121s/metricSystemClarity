package com.metricProject.metricSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.metricProject.metricSystem.controller.MetricsController;
import com.metricProject.metricSystem.model.Metric;
import com.metricProject.metricSystem.service.MetricService;

import java.util.*;
import net.minidev.json.JSONValue;

import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@WebMvcTest(value=MetricsController.class)
public class MetricsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MetricService metricService;
	
	@Test
	public void testGetMetrics() throws Exception{
		List<Metric> mockMetric = new ArrayList<>();		
		Metric e = new Metric();
		
		e.setId(1);
		e.setSystem("system");
		e.setName("name1");
		e.setFromDate(new Timestamp(0));
		e.setVal(5);
		mockMetric.add(e);

		String URI = "/metrics";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//String expectedJson = JSONValue.toJSONString(mockMetric);  //this.mapToJson(mockMetric);
		String outputInJson = result.getResponse().getContentAsString();
		assertFalse(outputInJson.isEmpty());
		//assertEquals(expectedJson, outputInJson);
		
	}
	
	
	public void testGetMetric() throws Exception{
		
		Date date = new Date();  
		Date d = new Date(System.currentTimeMillis());
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    String strDate = formatter.format(date); 
	    Date fromDate = formatter.parse(strDate);
		
	    
		Metric mockMetric = new Metric();
		mockMetric.setId(1);
		mockMetric.setSystem("system");
		mockMetric.setName("name1");
		mockMetric.setFromDate(fromDate);
		mockMetric.setVal(5);

		Mockito.when(metricService.getMetric(Mockito.anyLong())).thenReturn(mockMetric);
		
		String URI = "/metrics/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson =
				JSONValue.toJSONString(mockMetric);  //this.mapToJson(mockMetric);
		String outputInJson = result.getResponse().getContentAsString();
		assertEquals(expectedJson, outputInJson);
	}
	

}
