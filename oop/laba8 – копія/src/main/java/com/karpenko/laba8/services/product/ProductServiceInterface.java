package com.karpenko.laba8.services.product;

import java.util.List;

import com.karpenko.laba8.models.Product;

public interface ProductServiceInterface {

    void create(Product product);

    List<Product> readAll();

    Product read(String uuid);

    boolean update(Product product, String uuid);

    boolean delete(String uuid);

}