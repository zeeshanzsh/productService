package com.zeeshan.productService.service;

import com.zeeshan.productService.exception.InvalidCategoryException;
import com.zeeshan.productService.exception.ProductNotFoundException;
import com.zeeshan.productService.exception.ProductNotSavedException;
import com.zeeshan.productService.redis.entity.Category;
import com.zeeshan.productService.redis.entity.Product;
import com.zeeshan.productService.redis.repository.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zeeshan
 */


@Service
@Slf4j
@EnableCaching
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    /**
     *
     * @param category
     * @return
     */
    @Override
    public List<Product> findByCategory(String category) throws InvalidCategoryException {
        try {
            Category enumCategory = Category.valueOf(category);
            return productDAO.findAll().stream()
                    .filter(product -> product.getCategory() == enumCategory)
                    .sorted(Comparator.comparing(Product::getName))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            log.error("Invalid category: {}", category);
            throw new InvalidCategoryException("Please enter the valid category");
        }
    }
    /**
     *
     * @param product
     * @return
     */
    @Override
    public Product saveProduct(Product product) throws ProductNotSavedException {
         Product productSaved= productDAO.save(product);
         log.info("Product saving for category: {}",product.getCategory().name());
         if(productSaved !=null){
             return product;
         }else{
             throw new ProductNotSavedException("Unable to save in redis for product "+product.getName());
         }
    }

    /**
     *
     * @return
     */
    @Override
   // @Cacheable(value = "Product", key = "'allProducts'")
    public List<Product> findAllProduct() {
        return productDAO.findAll()
                .stream()
                .sorted(Comparator.comparing(Product::getId).reversed())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public String deleteProductById(int id) {
        return productDAO.deleteProduct(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws ProductNotFoundException
     */
    @Override
   // @Cacheable(value = "Product", key = "'#id'")
    public Product findByProductId(int id) throws ProductNotFoundException {
        Product product= productDAO.findByProductId(id);
        if(product !=null){
            log.info("Product found : {}", product.getName());
            return product;
        }else
            throw new ProductNotFoundException("Product you searched is not available!! [ productId : {} ] :" + id);
    }

    /**
     *
     * @param price
     * @return
     */
    @Override
    public List<Product> filterByUnderPrice(Long price) throws ProductNotFoundException {
        List<Product> productList = productDAO.findAll().stream()
                .filter(product -> product.getPrice() <= price)
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
        if(!productList.isEmpty()){
            return productList;
        }else{
            throw new ProductNotFoundException("No Product found under this price sorry !!!");
        }

    }

}
