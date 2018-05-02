package com.jhuarniz.ifood.service.cache.repository;

import com.jhuarniz.ifood.service.cache.model.Restaurant;
import com.jhuarniz.ifood.service.cache.model.Schedule;
import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;

import java.util.List;


@RepositoryConfig(cacheName = "ScheduleCache")
public interface ScheduleRepository extends IgniteRepository<Schedule, Long> {

}
