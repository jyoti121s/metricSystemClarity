package com.metricProject.metricSystem.model;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import lombok.*;
import javax.persistence.Id;

@Entity
@Table(name="Metrics")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Metric {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique=true)
	private String system;
	
	@Column(unique=true)
	private String name;
	
	@Column(name = "fromDate", unique=true)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date fromDate;
	
	
	private int val = 1;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date unixTimestamp) {
		this.fromDate = unixTimestamp;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}



}
