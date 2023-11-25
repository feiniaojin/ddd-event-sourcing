package com.feiniaojin.ddd.eventsourcing.domain.events;

import com.feiniaojin.ddd.eventsourcing.domain.DomainEvent;
import lombok.Data;

/**
 * 产品已创建事件
 */
@Data
public class ProductCreated extends DomainEvent {

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品图片
     */
    private String picture;

    /**
     * 产品数量
     */
    private Integer count;
}
