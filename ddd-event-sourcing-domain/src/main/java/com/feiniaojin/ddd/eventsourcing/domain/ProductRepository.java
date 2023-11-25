package com.feiniaojin.ddd.eventsourcing.domain;

public interface ProductRepository {

    public void save(Product product);

    public Product load(ProductId productId);
}
