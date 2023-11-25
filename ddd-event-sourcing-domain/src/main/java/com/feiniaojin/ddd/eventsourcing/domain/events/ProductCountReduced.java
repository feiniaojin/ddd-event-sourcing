package com.feiniaojin.ddd.eventsourcing.domain.events;

import com.feiniaojin.ddd.eventsourcing.domain.DomainEvent;
import lombok.Data;

/**
 * 产品数量已减少
 */
@Data
public class ProductCountReduced extends DomainEvent {
    /**
     * 产品数量减少后产品数量
     */
    private Integer count;
}
