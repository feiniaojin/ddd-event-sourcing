package com.feiniaojin.ddd.eventsourcing.domain.events;

import com.feiniaojin.ddd.eventsourcing.domain.DomainEvent;
import lombok.Data;

/**
 * 产品图片已改变
 */
@Data
public class ProductPictureChanged extends DomainEvent {
    /**
     * 产品图片
     */
    private String picture;
}
