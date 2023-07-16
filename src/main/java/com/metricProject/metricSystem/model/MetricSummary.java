package com.metricProject.metricSystem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Entity
@Table(name="MetricsSummary")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MetricSummary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long msId;
	private String msSystem;
	private String msName;
	private Date msFromDate;
	private Date msToDate;
	private Long msValue;
	
	public Long getMsId() {
		return msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}
	public String getMsSystem() {
		return msSystem;
	}
	public void setMsSystem(String msSystem) {
		this.msSystem = msSystem;
	}
	public String getMsName() {
		return msName;
	}
	public void setMsName(String msName) {
		this.msName = msName;
	}
	public Date getMsFromDate() {
		return msFromDate;
	}
	public void setMsFromDate(Date msFromDate) {
		this.msFromDate = msFromDate;
	}
	public Date getMsToDate() {
		return msToDate;
	}
	public void setMsToDate(Date msToDate) {
		this.msToDate = msToDate;
	}
	public Long getMsValue() {
		return msValue;
	}
	public void setMsValue(Long msValue) {
		this.msValue = msValue;
	}

	
	
}
