package com.feiniaojin.ddd.eventsourcing.infrastructure.persistence;

import com.feiniaojin.ddd.eventsourcing.domain.*;
import com.feiniaojin.ddd.eventsourcing.infrastructure.persistence.jdbc.data.Entity;
import com.feiniaojin.ddd.eventsourcing.infrastructure.persistence.jdbc.data.Event;
import com.feiniaojin.ddd.eventsourcing.infrastructure.persistence.jdbc.repository.EntityJdbcRepository;
import com.feiniaojin.ddd.eventsourcing.infrastructure.persistence.jdbc.repository.EventJdbcRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    ProductFactory productFactory = new ProductFactory();

    @Resource
    private EventJdbcRepository eventJdbcRepository;

    @Resource
    private EntityJdbcRepository entityJdbcRepository;

    @Override
    @Transactional
    public void save(Product product) {

        //1.存事件
        List<DomainEvent> domainEvents = product.getDomainEvents();
        List<Event> eventList = domainEvents.stream().map(de -> {
            Event event = new Event();
            event.setEventTime(de.getEventTime());
            event.setEventType(de.getEventType());
            event.setEventData(JSON.toJsonString(de));
            event.setEntityId(de.getEntityId());
            event.setDeleted(0);
            event.setEventId(de.getEventId());
            return event;
        }).collect(Collectors.toList());
        eventJdbcRepository.saveAll(eventList);

        //2.存实体
        Entity entity = new Entity();
        entity.setId(product.getId());
        entity.setDeleted(product.getDeleted());
        entity.setEntityId(product.getProductId().getValue());
        entity.setDeleted(0);
        entity.setVersion(product.getVersion());

        entityJdbcRepository.save(entity);
    }

    @Override
    public Product load(ProductId productId) {

        //1.创建空的聚合根
        Product product = productFactory.newInstance();
        //2.加载entity获得乐观锁
        Entity entity = entityJdbcRepository.queryOneByEntityId(productId.getValue());
        product.setVersion(entity.getVersion());
        product.setDeleted(entity.getDeleted());
        product.setId(entity.getId());
        //省略其他属性

        //3.取出历史事件列表
        List<Event> events = eventJdbcRepository.loadHistoryEvents(productId.getValue());
        List<DomainEvent> domainEvents = this.toDomainEvent(events);
        //4.回放重建聚合
        product.rebuild(domainEvents);
        return product;
    }

    private List<DomainEvent> toDomainEvent(List<Event> events) {
        List<DomainEvent> domainEvents = events.stream().map(e -> {
            String eventType = e.getEventType();
            Class<? extends DomainEvent> typeClass = EventTypeMapping.getEventTypeClass(eventType);
            DomainEvent domainEvent = JSON.toObject(e.getEventData(), typeClass);
            return domainEvent;
        }).collect(Collectors.toList());
        return domainEvents;
    }
}
