package com.jhuarniz.ifood.service.cache.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Log implements Serializable {

	private static final long serialVersionUID = -5350789537473512833L;
	private static final AtomicLong ID_GEN = new AtomicLong();
	
	@QuerySqlField(index = true)
	private Long id;
	@QuerySqlField(index = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date hitDate;
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

	public Date getHitDate() {
		return hitDate;
	}

	public void setHitDate(Date hitDate) {
		this.hitDate = hitDate;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

}
