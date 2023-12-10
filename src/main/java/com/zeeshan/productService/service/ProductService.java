package com.zeeshan.productService.service;

import com.zeeshan.productService.exception.InvalidCategoryException;
import com.zeeshan.productService.exception.ProductNotFoundException;
import com.zeeshan.productService.exception.ProductNotSavedException;
import com.zeeshan.productService.redis.entity.Product;

import java.util.List;

/**
 * @author zeeshan
 */


public interface ProductService {

    public List<Product> findByCategory(String category) throws InvalidCategoryException;

    public Product saveProduct(Product product) throws ProductNotSavedException;
    public List<Product> findAllProduct();
    public String deleteProductById(int id);

    public Product findByProductId(int id) throws ProductNotFoundException;
    public List<Product>  filterByUnderPrice(Long price) throws ProductNotFoundException;
}
