package com.jhuarniz.ifood.service.cache.repository;

import com.jhuarniz.ifood.service.cache.model.Log;
import com.jhuarniz.ifood.service.cache.model.Restaurant;
import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;

import java.util.List;


@RepositoryConfig(cacheName = "LogCache")
public interface LogRepository extends IgniteRepository<Log, Long> {

}
