package com.jhuarniz.ifood.service.cache.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.ignite.cache.query.annotations.QueryGroupIndex;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.data.annotation.Transient;

@QueryGroupIndex.List(
		@QueryGroupIndex(name="idx1")
) 
public class Restaurant implements Serializable {

	private static final long serialVersionUID = -1271194616130404625L;
	private static final AtomicLong ID_GEN = new AtomicLong(); //Todo: mudar outra estrategia de Identificador

	@QuerySqlField(index = true)
	private Long id;
	@QuerySqlField(index = true)
	private String name;

	private String cnpj;
	private RestaurantStatus status;

	@Transient
	private List<Schedule> schedules = new ArrayList<>();

	public void init() {
		this.id = ID_GEN.incrementAndGet();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public RestaurantStatus getStatus() {
		return status;
	}

	public void setStatus(RestaurantStatus status) {
		this.status = status;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public void addContact(Schedule schedule) {
		this.schedules.add(schedule);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
