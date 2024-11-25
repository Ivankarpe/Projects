package com.karpenko.laba8.controlers;

import com.karpenko.laba8.models.Product;
import com.karpenko.laba8.services.*;
import com.karpenko.laba8.services.product.ProductServiceDB;
import com.karpenko.laba8.services.product.ProductServiceInterface;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductServiceInterface productService;

    public ProductController(ProductServiceDB productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/api/products")
    public ResponseEntity<List<Product>> readAll() {
        final List<Product> products = productService.readAll();

        if (products == null || products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/api/products/{id}")
    public ResponseEntity<Product> read(@PathVariable(name = "id") String id) {
        final Product product = productService.read(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PostMapping(value = "/api/products")
    public ResponseEntity<?> create(@RequestBody Product client) {
        productService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/products/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") String id, @RequestBody Product client) {
        final boolean updated = productService.update(client, id);

        return new ResponseEntity<>( updated ? HttpStatus.OK : HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/products/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id) {
        final boolean deleted = productService.delete(id);

        return new ResponseEntity<>( deleted ? HttpStatus.OK : HttpStatus.NOT_MODIFIED);

    }
}
