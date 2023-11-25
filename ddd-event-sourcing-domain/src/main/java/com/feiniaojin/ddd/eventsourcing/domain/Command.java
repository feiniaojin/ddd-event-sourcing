package com.feiniaojin.ddd.eventsourcing.domain;

import lombok.Data;

@Data
public abstract class Command {

    /**
     * 唯一请求号，用于生成EventId
     */
    private String requestId;

    /**
     * 聚合根唯一标识
     */
    private String entityId;
}
