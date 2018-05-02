package com.jhuarniz.ifood.service.cache.model;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class Schedule implements Serializable {

	private static final long serialVersionUID = -5350789537473512833L;
	private static final AtomicLong ID_GEN = new AtomicLong();
	
	@QuerySqlField(index = true)
	private Long id;
	@QuerySqlField(index = true)
	private Date begin;
	@QuerySqlField(index = true)
	private Date end;

	private String reason;

	@QuerySqlField(index = true)
	private Long restaurantId;

	public void init() {
		this.id = ID_GEN.incrementAndGet();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
