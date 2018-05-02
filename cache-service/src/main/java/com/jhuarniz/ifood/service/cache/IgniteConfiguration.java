package com.jhuarniz.ifood.service.cache;


import com.jhuarniz.ifood.service.cache.model.Log;
import com.jhuarniz.ifood.service.cache.model.Restaurant;
import com.jhuarniz.ifood.service.cache.model.RestaurantStatus;
import com.jhuarniz.ifood.service.cache.model.Schedule;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.store.jdbc.CacheJdbcPojoStoreFactory;
import org.apache.ignite.cache.store.jdbc.JdbcType;
import org.apache.ignite.cache.store.jdbc.JdbcTypeField;
import org.apache.ignite.cache.store.jdbc.dialect.MySQLDialect;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Date;
import java.util.List;

@Configuration
public class IgniteConfiguration {


    @Autowired
    DataSource datasource;

    @Bean
    public Ignite igniteInstance() {
        org.apache.ignite.configuration.IgniteConfiguration cfg = new org.apache.ignite.configuration.IgniteConfiguration();
        cfg.setIgniteInstanceName("ignite-1");
        cfg.setPeerClassLoadingEnabled(true);

        CacheConfiguration<Long, Schedule> scheduleCache = createScheduleCache();
        CacheConfiguration<Long, Log> logCache = createLogCache();
        CacheConfiguration<Long, Restaurant> restaurantCache = createRestaurantCache();

//        CacheConfiguration restaurantCache = createRestaurantCache2();

        cfg.setCacheConfiguration(restaurantCache, scheduleCache, logCache);
//        cfg.setCacheConfiguration(restaurantCache);

        Ignite ignite = Ignition.start(cfg);

//        try (IgniteCache<Long, Restaurant> cache = ignite.getOrCreateCache("RestaurantCache")) {
//            // Load cache with data from the database.
//            cache.loadCache(null);
//            // Execute query on cache.
//            QueryCursor<List<?>> cursor = cache.query(new SqlFieldsQuery(
//                    "select id from Restaurant"));
//
//        }

        return ignite;
    }

    @SuppressWarnings("deprecation")
    private CacheConfiguration<Long,Schedule> createScheduleCache() {

        CacheConfiguration<Long, Schedule> scheduleCache = new CacheConfiguration<>("ScheduleCache");
        scheduleCache.setIndexedTypes(Long.class, Schedule.class);
        scheduleCache.setWriteBehindEnabled(true);
        scheduleCache.setReadThrough(true);
        scheduleCache.setWriteThrough(true);

        CacheJdbcPojoStoreFactory<Long, Schedule> factory = new CacheJdbcPojoStoreFactory<>();
        factory.setDataSource(datasource);
        factory.setDialect(new MySQLDialect());
        JdbcType jdbcContactType = new JdbcType();
        jdbcContactType.setCacheName("ScheduleCache");
        jdbcContactType.setKeyType(Long.class);
        jdbcContactType.setValueType(Schedule.class);
        jdbcContactType.setDatabaseTable("restaurant_schedule");
        jdbcContactType.setDatabaseSchema("ignite");
        jdbcContactType.setKeyFields(new JdbcTypeField(Types.INTEGER, "id", Long.class, "id"));
        jdbcContactType.setValueFields(
                new JdbcTypeField(Types.DATE, "begin", Date.class, "begin"),
                new JdbcTypeField(Types.DATE, "end", Date.class, "end"),
                new JdbcTypeField(Types.VARCHAR, "reason", String.class, "reason"),
                new JdbcTypeField(Types.INTEGER, "restaurant_id", Long.class, "restaurantId")
        );
        factory.setTypes(jdbcContactType);
        scheduleCache.setCacheStoreFactory(factory);
        return scheduleCache;
    }

    @SuppressWarnings("deprecation")
    private CacheConfiguration<Long,Restaurant> createRestaurantCache() {

        CacheConfiguration<Long, Restaurant> restaurantCache = new CacheConfiguration<>("RestaurantCache");
        restaurantCache.setIndexedTypes(Long.class, Restaurant.class);
        restaurantCache.setWriteBehindEnabled(true);
        restaurantCache.setReadThrough(true);
        restaurantCache.setWriteThrough(true);
        CacheJdbcPojoStoreFactory<Long, Restaurant> f = new CacheJdbcPojoStoreFactory<>();
        f.setDataSource(datasource);
        f.setDialect(new MySQLDialect());
        JdbcType jdbcType = new JdbcType();
        jdbcType.setCacheName("RestaurantCache");
        jdbcType.setKeyType(Long.class);
        jdbcType.setValueType(Restaurant.class);
        jdbcType.setDatabaseTable("restaurant");
        jdbcType.setDatabaseSchema("ignite");
        jdbcType.setKeyFields(new JdbcTypeField(Types.INTEGER, "id", Long.class, "id"));
        jdbcType.setValueFields(
                new JdbcTypeField(Types.VARCHAR, "name", String.class, "name"),
                new JdbcTypeField(Types.VARCHAR, "cnpj", String.class, "cnpj"),
                new JdbcTypeField(Types.VARCHAR, "status", RestaurantStatus.class, "status")
        );
        f.setTypes(jdbcType);
        restaurantCache.setCacheStoreFactory(f);

        return restaurantCache;
    }

    @SuppressWarnings("deprecation")
    private CacheConfiguration createRestaurantCache2() {

        CacheConfiguration restaurantCache = new CacheConfiguration("RestaurantCache");
        restaurantCache.setIndexedTypes(Long.class, Restaurant.class);
        restaurantCache.setWriteBehindEnabled(true);
        restaurantCache.setReadThrough(true);
        restaurantCache.setWriteThrough(true);
        CacheJdbcPojoStoreFactory<Long, Restaurant> f = new CacheJdbcPojoStoreFactory<>();
        f.setDataSource(datasource);
        f.setDialect(new MySQLDialect());
        JdbcType jdbcType = new JdbcType();
        jdbcType.setCacheName("RestaurantCache");
        jdbcType.setKeyType(Long.class);
        jdbcType.setValueType(Restaurant.class);
        jdbcType.setDatabaseTable("restaurant");
        jdbcType.setDatabaseSchema("ignite");
        jdbcType.setKeyFields(new JdbcTypeField(Types.INTEGER, "id", Long.class, "id"));
        jdbcType.setValueFields(
                new JdbcTypeField(Types.VARCHAR, "name", String.class, "name"),
                new JdbcTypeField(Types.VARCHAR, "cnpj", String.class, "cnpj"),
                new JdbcTypeField(Types.VARCHAR, "status", RestaurantStatus.class, "status")
        );
        f.setTypes(jdbcType);
        restaurantCache.setCacheStoreFactory(f);

        return restaurantCache;
    }

    @SuppressWarnings("deprecation")
    private CacheConfiguration<Long,Log> createLogCache() {

        CacheConfiguration<Long, Log> scheduleCache = new CacheConfiguration<>("LogCache");
        scheduleCache.setIndexedTypes(Long.class, Log.class);
        scheduleCache.setWriteBehindEnabled(true);
        scheduleCache.setReadThrough(true);
        scheduleCache.setWriteThrough(true);

        CacheJdbcPojoStoreFactory<Long, Log> factory = new CacheJdbcPojoStoreFactory<>();
        factory.setDataSource(datasource);
        factory.setDialect(new MySQLDialect());
        JdbcType jdbcContactType = new JdbcType();
        jdbcContactType.setCacheName("LogCache");
        jdbcContactType.setKeyType(Long.class);
        jdbcContactType.setValueType(Log.class);
        jdbcContactType.setDatabaseTable("restaurant_log");
        jdbcContactType.setDatabaseSchema("ignite");
        jdbcContactType.setKeyFields(new JdbcTypeField(Types.INTEGER, "id", Long.class, "id"));
        jdbcContactType.setValueFields(
                new JdbcTypeField(Types.DATE, "hit_date", Date.class, "hitDate"),
                new JdbcTypeField(Types.INTEGER, "restaurant_id", Long.class, "restaurantId")
        );
        factory.setTypes(jdbcContactType);
        scheduleCache.setCacheStoreFactory(factory);
        return scheduleCache;
    }
}
