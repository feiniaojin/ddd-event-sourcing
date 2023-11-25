package com.feiniaojin.ddd.eventsourcing.infrastructure.persistence.jdbc.repository;

import com.feiniaojin.ddd.eventsourcing.infrastructure.persistence.jdbc.data.Event;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 表名称：t_event自动生成的Repository
 * 表注释：领域事件表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/code-generator
 */
public interface EventJdbcRepository extends CrudRepository<Event, Long> {

    @Query("select * from t_event where entity_id =:entityId")
    List<Event> loadHistoryEvents(String entityId);
}
