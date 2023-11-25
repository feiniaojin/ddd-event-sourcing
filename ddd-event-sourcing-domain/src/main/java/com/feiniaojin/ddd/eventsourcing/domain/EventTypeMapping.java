package com.feiniaojin.ddd.eventsourcing.domain;

import com.feiniaojin.ddd.eventsourcing.domain.events.ProductCountReduced;
import com.feiniaojin.ddd.eventsourcing.domain.events.ProductCreated;
import com.feiniaojin.ddd.eventsourcing.domain.events.ProductPictureChanged;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventTypeMapping {
    private static final Map<String, Class<? extends DomainEvent>> map = new ConcurrentHashMap<>();

    static {
        map.put("ProductCreated", ProductCreated.class);
        map.put("ProductCountReduced", ProductCountReduced.class);
        map.put("ProductPictureChanged", ProductPictureChanged.class);
    }

    public static final Class<? extends DomainEvent> getEventTypeClass(String eventType) {
        return map.get(eventType);
    }
}
