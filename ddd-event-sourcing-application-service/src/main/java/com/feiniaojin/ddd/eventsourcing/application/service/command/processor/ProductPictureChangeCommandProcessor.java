package com.feiniaojin.ddd.eventsourcing.application.service.command.processor;

import com.feiniaojin.ddd.eventsourcing.domain.*;
import com.feiniaojin.ddd.eventsourcing.domain.commands.ProductCreateCommand;
import com.feiniaojin.ddd.eventsourcing.domain.commands.ProductPictureChangeCommand;
import com.feiniaojin.ddd.eventsourcing.domain.events.ProductPictureChanged;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class ProductPictureChangeCommandProcessor implements CommandProcessor, ApplicationContextAware {
    @Override
    public List<DomainEvent> process(Product product, Command command) {
        ProductPictureChangeCommand pictureChangeCommand = (ProductPictureChangeCommand) command;
        //1.校验命令的合法性
        if (StringUtils.isBlank(pictureChangeCommand.getPicture())) {
            //参数校验未通过异常
            throw new IllegalArgumentException();
        }
        //2.校验通过，根据现有的聚合以及命令生成领域事件
        ProductPictureChanged pictureChanged = new ProductPictureChanged();
        pictureChanged.setEntityId(product.getProductId().getValue());
        pictureChanged.setEventId(command.getRequestId() + "-" + pictureChanged.getClass().getSimpleName());
        pictureChanged.setEventType(pictureChanged.getClass().getSimpleName());
        pictureChanged.setEventTime(new Date());

        pictureChanged.setPicture(pictureChangeCommand.getPicture());

       return Collections.singletonList(pictureChanged);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CommandProcessorRegister.registerCommandProcessor(ProductPictureChangeCommand.class, this);
    }
}
