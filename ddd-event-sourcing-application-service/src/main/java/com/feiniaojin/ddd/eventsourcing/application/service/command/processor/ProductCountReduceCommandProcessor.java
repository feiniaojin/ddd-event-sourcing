package com.feiniaojin.ddd.eventsourcing.application.service.command.processor;

import com.feiniaojin.ddd.eventsourcing.domain.*;
import com.feiniaojin.ddd.eventsourcing.domain.commands.ProductCountReduceCommand;
import com.feiniaojin.ddd.eventsourcing.domain.commands.ProductCreateCommand;
import com.feiniaojin.ddd.eventsourcing.domain.events.ProductCountReduced;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class ProductCountReduceCommandProcessor implements CommandProcessor, ApplicationContextAware {
    @Override
    public List<DomainEvent> process(Product product, Command command) {

        ProductCountReduceCommand countChangeCommand = (ProductCountReduceCommand) command;
        //1.参数校验
        Integer commandCount = countChangeCommand.getCount();
        if (commandCount == null) {
            throw new IllegalArgumentException();
        }
        //2.业务校验
        Integer productCount = product.getCount();
        if (commandCount > productCount) {
            //假如产品还剩8个，但是要减去10个，会导致产品数量不足，因此不合法不能进行业务操作
            throw new IllegalArgumentException();
        }
        //3.创建领域事件
        Integer resultCount = productCount - commandCount;
        ProductCountReduced countReduced = new ProductCountReduced();
        countReduced.setEventId(command.getRequestId() + "-" + countReduced.getClass().getSimpleName());
        countReduced.setEntityId(product.getProductId().getValue());
        countReduced.setEventTime(new Date());
        countReduced.setEventType(countReduced.getClass().getSimpleName());

        countReduced.setCount(resultCount);
        return Collections.singletonList(countReduced);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CommandProcessorRegister.registerCommandProcessor(ProductCountReduceCommand.class, this);
    }
}
