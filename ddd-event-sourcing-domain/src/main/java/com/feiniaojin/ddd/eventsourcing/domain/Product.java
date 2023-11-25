package com.feiniaojin.ddd.eventsourcing.domain;

import com.feiniaojin.ddd.eventsourcing.domain.events.ProductCountReduced;
import com.feiniaojin.ddd.eventsourcing.domain.events.ProductCreated;
import com.feiniaojin.ddd.eventsourcing.domain.events.ProductPictureChanged;
import lombok.Getter;

@Getter
public class Product extends AbstractAggregateRoot {

    /**
     * 产品ID
     */
    private ProductId productId;

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

    public void apply(ProductCreated domainEvent) {
        //新创建的聚合根需要赋予唯一标识
        this.productId = new ProductId(domainEvent.getEntityId());
        this.productName = domainEvent.getProductName();
        this.count = domainEvent.getCount();
        this.picture = domainEvent.getPicture();
        //注册领域事件
        super.registerDomainEvents(domainEvent);
    }

    public void apply(ProductCountReduced domainEvent) {
        //将数量扣减结果更新到聚合根
        this.count = domainEvent.getCount();
        //注册领域事件
        super.registerDomainEvents(domainEvent);
    }

    public void apply(ProductPictureChanged domainEvent) {
        //将新的图片更新到聚合根
        this.picture = domainEvent.getPicture();
        //注册领域事件
        super.registerDomainEvents(domainEvent);
    }


    public static void main(String[] args) {
        Product product = new Product();
        ProductCreated productCreated = new ProductCreated();
        productCreated.setEntityId("1");
        product.apply(productCreated);
        ProductCountReduced countReduced = new ProductCountReduced();
    }


}
