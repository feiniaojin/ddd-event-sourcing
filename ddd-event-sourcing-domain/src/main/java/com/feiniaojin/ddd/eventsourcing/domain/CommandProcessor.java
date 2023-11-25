package com.feiniaojin.ddd.eventsourcing.domain;

import java.util.List;

/**
 * 规范：只能读取聚合根的值，不能修改聚合根
 */
public interface CommandProcessor {
    List<DomainEvent> process(Product product, Command command);
}