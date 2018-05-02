package com.jhuarniz.ifood.service.cache.repository;

import java.util.List;

import com.jhuarniz.ifood.service.cache.model.Restaurant;
import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;


@RepositoryConfig(cacheName = "RestaurantCache")
public interface RestaurantRepository extends IgniteRepository<Restaurant, Long> {
	
	List<Restaurant> findByName(String name);
}
