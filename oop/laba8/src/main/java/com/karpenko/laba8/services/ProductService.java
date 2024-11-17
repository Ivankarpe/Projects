package com.karpenko.laba8.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.karpenko.laba8.models.Product;


@Service
public class ProductService{
    
    private final static Map<String, Product> dataProd = new HashMap<>();

    public void create(Product product){
        String uuid = UUID.randomUUID().toString();
        product.setId(uuid);
        dataProd.put(uuid, product);
    }

    public List<Product> readAll(){
        return new ArrayList<>(dataProd.values());
    }

    public Product read(String uuid){
        return dataProd.get(uuid);
    }


    public boolean update(Product product, String uuid){

        if(dataProd.containsKey(uuid)){
            product.setId(uuid);
            dataProd.put(uuid, product);
            return true;
        }

        return false;
    }

    public boolean delete(String uuid){
        return dataProd.remove(uuid) != null;
    }
}