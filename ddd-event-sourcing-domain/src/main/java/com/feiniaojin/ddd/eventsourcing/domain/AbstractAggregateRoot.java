package com.feiniaojin.ddd.eventsourcing.domain;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractAggregateRoot extends AbstractDomainMask {

    private static final Map<Class<?>, Method> applyMethodMap = new ConcurrentHashMap<>();
    private List<DomainEvent> domainEvents = new ArrayList<>();

    {
        if (applyMethodMap.isEmpty()) {
            Method[] methods = getClass().getMethods();
            for (Method method : methods) {
                //跳过不包含apply的方法
                if (!method.getName().equals("apply")) {
                    continue;
                }
                Class<?>[] parameterTypes = method.getParameterTypes();
                applyMethodMap.put(parameterTypes[0], method);
            }
        }
    }

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }

    protected final void registerDomainEvents(DomainEvent event) {
        this.domainEvents.add(event);
    }

    protected final void registerDomainEvents(List<DomainEvent> events) {
        this.domainEvents.addAll(events);
    }

    protected final void clearDomainEvents() {
        this.domainEvents = new ArrayList<>();
    }

    public void rebuild(List<DomainEvent> events) {
        try {
            for (DomainEvent domainEvent : events) {
                Method method = applyMethodMap.get(domainEvent.getClass());
                method.invoke(this, domainEvent);
            }
            //清空领域事件，因为重建领域事件不需要记录下来存库
            this.clearDomainEvents();
        } catch (Exception e) {
            throw new RuntimeException("找不到对应的apply方法");
        }
    }

    public void apply(DomainEvent domainEvent) {
        try {
            Method method = applyMethodMap.get(domainEvent.getClass());
            method.invoke(this, domainEvent);
        } catch (Exception e) {
            throw new RuntimeException("找不到对应的apply方法");
        }
    }
}