package com.jhuarniz.ifood.service.schedule.controller;

import com.jhuarniz.ifood.service.cache.repository.ScheduleRepository;
import com.jhuarniz.ifood.service.schedule.service.CreateScheduleRequest;
import com.jhuarniz.ifood.service.schedule.service.ScheduleResponse;
import com.jhuarniz.ifood.service.schedule.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);
	
	@Autowired
	StoreService storeService;

	@PostMapping
	public String add(@RequestBody CreateScheduleRequest createScheduleRequest) {
		return storeService.createSchedule(createScheduleRequest);
	}

	@GetMapping("/{id}")
	public ScheduleResponse get(@PathVariable("id") Long id) {
		return storeService.getSchedule(id);
	}

	@GetMapping("/{id}")
	public ScheduleResponse get(@PathVariable("id") Long id) {
		return storeService.getSchedule(id);
	}



}
