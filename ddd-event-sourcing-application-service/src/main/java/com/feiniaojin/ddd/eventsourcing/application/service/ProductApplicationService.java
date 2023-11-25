package com.feiniaojin.ddd.eventsourcing.application.service;

import com.feiniaojin.ddd.eventsourcing.domain.*;
import com.feiniaojin.ddd.eventsourcing.domain.commands.ProductCreateCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductApplicationService {

    private ProductFactory productFactory = new ProductFactory();

    @Resource
    private ProductRepository productRepository;

    public void create(ProductCreateCommand command) {

        //1.创建一个空聚合
        Product product = productFactory.newInstance();

        //2.找到Command对应的处理器
        CommandProcessor commandProcessor = CommandProcessorRegister.mappingCommandProcessor(command);

        //3.使用Command处理器，计算将要得到的属性，生成领域事件
        List<DomainEvent> domainEvents = commandProcessor.process(product, command);

        //4.应用事件到聚合根中
        for (DomainEvent domainEvent : domainEvents) {
            product.apply(domainEvent);
        }

        //5.保存聚合根
        productRepository.save(product);
    }

    public void modify(Command command) {

        //1.加载聚合根
        Product product = productRepository.load(new ProductId(command.getEntityId()));

        //2.找到Command对应的处理器
        CommandProcessor commandProcessor = CommandProcessorRegister.mappingCommandProcessor(command);

        //3.使用Command处理器，计算将要得到的属性，生成领域事件
        List<DomainEvent> domainEvents = commandProcessor.process(product, command);

        //4.应用领域事件
        for (DomainEvent domainEvent : domainEvents) {
            product.apply(domainEvent);
        }

        //5.保存聚合根
        productRepository.save(product);
    }
}
