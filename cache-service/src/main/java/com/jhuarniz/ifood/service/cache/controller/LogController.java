package com.jhuarniz.ifood.service.cache.controller;

import com.jhuarniz.ifood.service.cache.model.Log;
import com.jhuarniz.ifood.service.cache.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log")
public class LogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);
	
	@Autowired
	LogRepository repository;
	
	@PostMapping
	public Log add(@RequestBody Log Log) {
		Log.init();
		return repository.save(Log.getId(), Log);
	}
	
	@PutMapping
	public Log update(@RequestBody Log Log) {
		return repository.save(Log.getId(), Log);
	}
	
	@DeleteMapping("/{id}")
	public void delete(Long id) {
		repository.delete(id);
	}
	
	@GetMapping("/{id}")
	public Log findById(@PathVariable("id") Long id) {
		return repository.findOne(id);
	}



	
}
