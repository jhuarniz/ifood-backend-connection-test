package com.jhuarniz.ifood.service.cache.controller;

import com.jhuarniz.ifood.service.cache.model.Schedule;
import com.jhuarniz.ifood.service.cache.model.Schedule;
import com.jhuarniz.ifood.service.cache.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);
	
	@Autowired
	ScheduleRepository repository;
	
	@PostMapping
	public Schedule add(@RequestBody Schedule Schedule) {
		Schedule.init();
		return repository.save(Schedule.getId(), Schedule);
	}
	
	@PutMapping
	public Schedule update(@RequestBody Schedule Schedule) {
		return repository.save(Schedule.getId(), Schedule);
	}
	
	@DeleteMapping("/{id}")
	public void delete(Long id) {
		repository.delete(id);
	}
	
	@GetMapping("/{id}")
	public Schedule findById(@PathVariable("id") Long id) {
		return repository.findOne(id);
	}



	
}
