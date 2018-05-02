package com.jhuarniz.ifood.service.cache.controller;

import com.jhuarniz.ifood.service.cache.model.Restaurant;
import com.jhuarniz.ifood.service.cache.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);
	
	@Autowired
	RestaurantRepository repository;
	
	@PostMapping
	public Restaurant add(@RequestBody Restaurant restaurant) {
		restaurant.init();
		return repository.save(restaurant.getId(), restaurant);
	}
	
	@PutMapping
	public Restaurant update(@RequestBody Restaurant restaurant) {
		return repository.save(restaurant.getId(), restaurant);
	}
	
	@DeleteMapping("/{id}")
	public void delete(Long id) {
		repository.delete(id);
	}
	
	@GetMapping("/{id}")
	public Restaurant findById(@PathVariable("id") Long id) {
		return repository.findOne(id);
	}



	
}
