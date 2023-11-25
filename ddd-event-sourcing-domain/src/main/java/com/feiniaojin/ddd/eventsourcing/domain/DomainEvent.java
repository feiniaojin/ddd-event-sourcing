package com.feiniaojin.ddd.eventsourcing.domain;

import lombok.Data;

import java.util.Date;

@Data
public abstract class DomainEvent {

    private String eventId;

    private String entityId;

    private String eventType;

    private Date eventTime;

}
