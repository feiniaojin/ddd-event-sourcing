package com.feiniaojin.ddd.eventsourcing.application.service.command.processor;

import com.feiniaojin.ddd.eventsourcing.domain.*;
import com.feiniaojin.ddd.eventsourcing.domain.commands.ProductCreateCommand;
import com.feiniaojin.ddd.eventsourcing.domain.events.ProductCreated;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class ProductCreateCommandProcessor implements CommandProcessor, ApplicationContextAware {

    @Override
    public List<DomainEvent> process(Product product, Command command) {

        ProductCreateCommand createCommand = (ProductCreateCommand) command;
        ProductCreated productCreated = new ProductCreated();
        //生成事件ID
        productCreated.setEventId(command.getRequestId() + "-" + productCreated.getClass().getSimpleName());
        productCreated.setEntityId("P01");
        productCreated.setEventTime(new Date());
        productCreated.setEventType(productCreated.getClass().getSimpleName());

        productCreated.setProductName(createCommand.getProductName());
        productCreated.setCount(createCommand.getCount());
        productCreated.setPicture(createCommand.getPicture());
        return Collections.singletonList(productCreated);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CommandProcessorRegister.registerCommandProcessor(ProductCreateCommand.class, this);
    }
}
